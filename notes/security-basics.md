# Security - Basics

* [Spring Security][]
* [Spring Security Documentation][]
* [Spring Security Guide][]

## Dependencies

**build.gradle**
```
implementation 'org.springframework.boot:spring-boot-starter-security'
```

## Configuration

```
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  // Implement methods here to configure security.

}
```

## Users Details

Your application will need to supply `UserDetails` to determine who has access
to your application, and what their roles are.

To get started quickly you can use an `InMemoryUserDetailsManager`, which does
not require a database.

```
@Bean
@Override
protected UserDetailsService userDetailsService() {
  User.UserBuilder builder = User.withDefaultPasswordEncoder();

  InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
  userDetailsManager.createUser(builder
      .username("admin")
      .password("admin")
      .roles("ADMIN")
      .build());

  return userDetailsManager;
}
```

`User.withDefaultPasswordEncoder()` is marked `deprecated` to warn your that it
is not suitable for production use. It works well for demos and prototypes, but
having a raw password in your source code is not safe. You should hash passwords
externally for production systems.

## HTTP Security

Once you have some users defined, you will need to configure what they can
access.

```
@Override
protected void configure(HttpSecurity http) throws Exception {
  http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/").permitAll()
      .antMatchers(HttpMethod.GET, "/books", "/books/**", "/authors", "/authors/**").permitAll()
      .anyRequest().hasRole("ADMIN")
      .and()
      .httpBasic()
      .and()
      .csrf().disable();
}
```

* `authorizeRequests()` restricts access based on the request.
* `antMatchers()` matches URL patterns, or a combination of HTTP and URL patterns.
* `permitAll()` permits everyone to access a matched URL.
* `anyRequest()` will match any request.
* `hasRole("ADMIN")` requires a user to have the ADMIN role to access the given resource.
* `httpBasic()` enables authentication via [HTTP Basic Authentication][].
* `csrf().disable()` disables [Cross Site Request Forgery][] protection.

[Spring Security]: https://spring.io/projects/spring-security
[Spring Security Documentation]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
[Spring Security Guide]: https://spring.io/guides/gs/securing-web/
[HTTP Basic Authentication]: https://en.wikipedia.org/wiki/Basic_access_authentication
[Cross Site Request Forgery]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-attacks

This configuration results in author and book resources being readable by
anyone, but only writable by an admin user.

Keep in mind that rules apply in the
order they are matched. The first rule that applies to a request wins.
