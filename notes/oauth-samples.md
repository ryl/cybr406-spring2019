# OAuth Samples

Spring provides 2 example applications demonstrating the authorization code
password grant.
* Install Maven
  * [On Windows][maven-windows]
* Clone [spring-security-oauth][]
* Run sparklr
  ```
  cd spring-security-oauth
  mvn install -P bootstrap
  cd samples/oauth2/sparklr
  mvn tomcat7:run
  ```

[maven-windows]: https://www.youtube.com/watch?v=dlPjiYyVSlc
[spring-security-oauth]: https://github.com/spring-projects/spring-security-oauth
