# Event Handler Shortcomings

Consider an event handler designed to update the user table's username when an
author username is updated. You might imagine something like this:

```
@HandleBeforeSave
public void handleBeforeSave(Author author) {
  Optional<Author> result = authorRepository.findById(author.getId());

  if (result.isPresent()) {
    Author existingAuthor = result.get();
    if (!existingAuthor.getUsername().equals(author.getUsername())) {
      UserDetails user = userDetailsManager.loadUserByUsername(existingAuthor.getUsername());
      userDetailsManager.updateUser(User.withUserDetails(user)
              .username(author.getUsername())
              .build());
    }
  }
}
```

The problem with this approach is `authorRepository.findById(author.getId())`
does not return the author as it appears in the database. It is already a
"managed entity" by JPA.

[This has been a shortcoming of Spring Data Rest for some time.](DATAREST-373)

It would be nice if we could compare the submitted author updates with what
exists in the database, so we could take action if a specific change was made.

There is a workaround. We can use the `JdbcTemplate` to see what really resides
in the database at the moment.

```
String currentUsername = jdbcTemplate.queryForObject(
        "select username from author where id = ?",
        String.class,
        author.getId());
```

* `select username from author where id = ?` is the sql to execute. `?` acts as a placeholder for parameters.
* `String.class` is the object we expect to return.
* `author.getId()` the values to substitute for each `?` marker.

## Handling Security in Java

In this particular case, the `@PreAuthorize` annotation does not work since we
need to fetch a raw value from the database. In a tough case like this, we can
manually handle security ourselves.

`SecurityContextHolderAwareRequestWrapper` provides a wrapper around the request
with some useful security methods for checking user roles, and getting the
user's name.

```
Logger logger = LoggerFactory.getLogger(AuthorEventHandler.class);

@Autowired
AuthorRepository authorRepository;

@Autowired
JdbcUserDetailsManager userDetailsManager;

@Autowired
JdbcTemplate jdbcTemplate;

@Autowired
HttpServletRequest httpServletRequest;

@HandleBeforeSave
public void handleBeforeSave(Author author) {
  SecurityContextHolderAwareRequestWrapper request = new SecurityContextHolderAwareRequestWrapper(
          httpServletRequest, "ROLE");

  try {
    String currentUsername = jdbcTemplate.queryForObject(
            "select username from author where id = ?",
            String.class,
            author.getId());

    if (request.isUserInRole("ROLE_ADMIN") || currentUsername.equals(request.getUserPrincipal().getName())) {
      UserDetails user = userDetailsManager.loadUserByUsername(currentUsername);
      userDetailsManager.deleteUser(user.getUsername());
      userDetailsManager.createUser(User.withUserDetails(user)
              .username(author.getUsername())
              .build());
    } else {
      throw new BadCredentialsException("Unauthorized");
    }
  } catch (EmptyResultDataAccessException e) {
    logger.info("No username exists for author id {}", author.getId());
  }
}
```

`jdbcTemplate.queryForObject()` will return a single result from the database,
or throw an `EmptyResultDataAccessException` if there were no results. If you
can tolerate the hardcoded SQL statement, this is a fairly pragmatic workaround.

[DATAREST-373]: https://jira.spring.io/browse/DATAREST-373
