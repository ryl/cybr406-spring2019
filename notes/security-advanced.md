# Security - Advanced

* [Spring Security Documentation][]

## Stateless Server

Making your server [stateless][stateless] improves scalability and impacts your
security strategy.

* More scalable because server does not have to remember anything between
  requests. Sharing state between many servers is unnecessary.
* Reduced [CSRF][csrf] risk.
* Look for JSESSIONID cookie. If you can find it, your sever is not stateless.
* Watch up for WWW-Authenticate header. Browsers will remember the response and
  potentially open you up to CSRF attacks.

```
  http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/").permitAll()
      .antMatchers(HttpMethod.GET, "/books", "/books/**", "/authors", "/authors/**").permitAll()
      .anyRequest().hasAnyRole("ADMIN", "AUTHOR")
      .and()
      .csrf().disable()

      // Disable sessions.
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()

      // Suppress WWW-Authenticate header to prevent someone using a browser from accidentally logging in.
      .httpBasic().authenticationEntryPoint((request, response, authException) -> {
        if (authException != null) {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
      });
```

## Store Users in a Database

Add the following to one of your `@Configuration` classes:

```
@Autowired
DataSource dataSource;

@Bean
PasswordEncoder passwordEncoder() {
  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  PasswordEncoder passwordEncoder = passwordEncoder();

  // Create a UserBuilder
  User.UserBuilder users = User.builder();
  users.passwordEncoder(passwordEncoder::encode);

  auth
      .jdbcAuthentication()   // Authenticate users using via a jdbc (Java)
      .dataSource(dataSource) // Database connection info (user name, password, url, etc.)
      .withDefaultSchema()    // Use Spring Security's default table schema
      .withUser(users         // Add a pre-configured user to the database.
          .username("admin")
          .password("admin")
          .roles("ADMIN"))
}
```

* `.withDefaultSchema()` creates tables using [Spring Security's default table schema][security schema].
* `PasswordEncoderFactories.createDelegatingPasswordEncoder()` creates a
  [DelegatingPasswordEncoder][] that can encrypt passwords using various different algorithms.

## Creating New Users

`@Configuration`
```
@Bean
JdbcUserDetailsManager jdbcUserDetailsManager() {
  return new JdbcUserDetailsManager(dataSource);
}
```

`@RestController`
```
@Autowired
JdbcUserDetailsManager userDetailsManager;

@PostMapping("...")
public ResponseEntity<...> signUp(@Valid @RequestBody Registration reg) {

    ...

    userDetailsManager.createUser(userBuilder
        .username(reg.getEmail())
        .password(reg.getPassword())
        .roles("AUTHOR")
        .build());

    ...

}
```

## Method Level Security

Method level security offers more flexibility than configuration based solely on
URL paths and HTTP methods.

1. [Enable method security][]
    ```
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        ...
    ```
2. Add [method security expressions][] to methods requiring protection.
    ```
    @HandleBeforeSave
    @PreAuthorize("hasRole('ROLE_ADMIN') or #author.username == authentication.principal.username")
    public void handleBeforeSave(Author author) {
        ...
    }
    ```
    * [Common Expressions][]
    * Notice that you can access method arguments using `#`. In the example
      above `#author` refers to the `Author author` method argument. If you use
      `@Param("a") Author author` you man refer to author using `#a`.
    * When you use `@PostAuthorize` you can refer the object the method returns
      using `returnObject`.

[Spring Security Documentation]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
[DelegatingPasswordEncoder]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#pe-dpe
[jdbc]: https://en.wikipedia.org/wiki/Java_Database_Connectivity
[stateless]: https://stackoverflow.com/questions/34130036/how-to-understand-restful-api-is-stateless
[csrf]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf
[security schema]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema
[enable method security]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#enableglobalmethodsecurity
[method security expressions]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#method-security-expressions
[common expressions]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#el-common-built-in
