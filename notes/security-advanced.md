# Security - Advanced

## Stateless Server

Making your server [stateless][stateless] improves scalability and impacts your
security strategy.

* More scalable because server does not have to remember anything between requests. Sharing state between many servers is not required.
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

      // Suppress WWW-Authenticate header to force the use of manually provided Authorization header.
      .httpBasic().authenticationEntryPoint((request, response, authException) -> {
        if (authException != null) {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
      });
```

## Store Users in a Database

* [Security Table Schema][security schema]

## Method Level Security

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

[stateless]: https://stackoverflow.com/questions/34130036/how-to-understand-restful-api-is-stateless
[csrf]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf
[security schema]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema
[enable method security]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#enableglobalmethodsecurity
[method security expressions]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#method-security-expressions
[common expressions]: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#el-common-built-in
