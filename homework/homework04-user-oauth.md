# Add OAuth to User Microservice

Due sometime after spring break?

Your task is to implement the resource owner password credentials workflow in
the User microservice.

Later we will visit the Post microservice and add OAuth there, too. This
assignment will focus only on the User microservice.

* [Read about the password grant type.][password grant type]
* [Study the workflow diagram.][password workflow]
* [Study spring's sample application configuration.][sample]
* Configure an `InMemoryTokenStore`.
* Configure an authorization server
  * Override `configure(ClientDetailsServiceConfigurer clients)`
    * Create a client for api consumption by users.
    * Create a client for the post microservice to access `/oauth/check_token`.
      Give this client a special role using `authorities(...)`.
  * Override `configure(AuthorizationServerSecurityConfigurer security)`
    * Configure the `/oauth/check_token` endpoint to require the post
      microservice's role.
  * Override `configure(AuthorizationServerEndpointsConfigurer configurer)`
      * Configure to use your `TokenStore`
      * Configure to use your `AuthenticationManager`
* Configure a resource server
  * Override `configure(HttpSecurity http)`
    * Focus securing /profiles urls.
    * Use `.access("...")` to write security expressions using
      `#oauth2.isOAuth()`, `hasRole(...)`, and `hasAnyRole(...)`

## Resources

* [Developer Guide](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)
* [Tutorial](https://projects.spring.io/spring-security-oauth/docs/tutorial.html)

[password workflow]: https://tools.ietf.org/html/rfc6749#section-4.3
[password grant type]: https://aaronparecki.com/oauth-2-simplified/#password
[sample]: https://github.com/spring-projects/spring-security-oauth/blob/master/samples/oauth2/sparklr/src/main/java/org/springframework/security/oauth/examples/sparklr/config/OAuth2ServerConfig.java
