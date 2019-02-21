# Security Checklist

**I want to secure URL patterns.**

* Extend `WebSecurityConfigurerAdapter` in an `@Configuration` class.
* Override the method `configure(HttpSecurity http)`.
* Use the `HttpSecurity http` method argument to configure URL patterns and
  required roles.

**I want to disable CSRF protection and make my server stateless.**

* In your `configure(HttpSecurity http)` method, disable csrf protection.
* Change session creation policy to STATELESS.
* As an extra precaution, disable WWW-Authenticate header to protect against
  users with browsers from accidentally logging in.

**URL based security is too broad, I want more detailed control.**

* Use method level security for more granular security.
* Add `@EnableGlobalMethodSecurity(prePostEnabled = true)` to your `@Configuration`
* Use `@PreAuthorize` and `@PostAuthorize` for detailed control.
* Good solution for preventing users from editing other user's content.

**I want my app to supply a pre-configured user, but I don't want a plaintext
password in my source code.**

* Encrypt your passwords with a `PasswordEncoder` beforehand, and include the
  encrypted password in your source.
  ```
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder passwordEncoder = passwordEncoder();

    User.UserBuilder users = User.builder();
    // DO NOT supply a password encoder if you have already encrypted the passwords.
    // users.passwordEncoder(passwordEncoder::encode);

    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .withDefaultSchema()
        .withUser(users
            .username("admin")
            .password("{bcrypt}$2a$10$g6fvJ2r1HRvbbakBNMCr1.zdI6ROJtGum/IBOegzUwh3aiVhYyKA2")
            .roles("ADMIN"));
  }
  ```

**I want anonymous users to register, and I need a way to add them to my database.**

* Create a `JdbcUserDetailsManager` bean to `@Autowire` into your controllers.
* Create a `User.UserBuilder` bean to `@Autowire` into your controllers. This
  class provides a tidy way to build the `UserDetail` that will be saved by
  the `JdbcUserDetailsManager`.

```
@Bean
JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
  return new JdbcUserDetailsManager(dataSource);
}

@Bean
User.UserBuilder userBuilder() {
  PasswordEncoder passwordEncoder = passwordEncoder();
  User.UserBuilder users = User.builder();
  users.passwordEncoder(passwordEncoder::encode);
  return users;
}
```
