# Blog Odds and Ends

Resources:

* [cybr406-user-private](https://github.com/ryl/cybr406-user-private)
* [cybr406-post-user](https://github.com/ryl/cybr406-user-private)
* [Postman Collection](../files/oauth-demo-heroku.postman_collection.json)

## Pull cybr406-blog

This project has updated routes.

* Pull from class repository [https://github.com/ryl/cybr406-blog](https://github.com/ryl/cybr406-blog)
* Push to heroku

## Pull cybr406-post

This project has been updated for OAuth.

* Pull from class repository [https://github.com/ryl/cybr406-post](https://github.com/ryl/cybr406-post)
* Update `application-heroku.properties` to match your site's URL.

  For example: `check-token-endpoint=https://lowryrs-blog.herokuapp.com/oauth/check_token`
* Push to heroku

## Update User

Add support for PostgreSQL.

* Create src/main/resources/db/changelog/users.csv
  * [https://github.com/ryl/cybr406-user-private/blob/master/src/main/resources/db/changelog/users.csv](https://github.com/ryl/cybr406-user-private/blob/master/src/main/resources/db/changelog/users.csv)
* Create src/main/resources/db/changelog/authorities.csv
  * [https://github.com/ryl/cybr406-user-private/blob/master/src/main/resources/db/changelog/authorities.csv](https://github.com/ryl/cybr406-user-private/blob/master/src/main/resources/db/changelog/authorities.csv)
* Add spring-security-tables changeset to src/main/resources/db/changelog/db.changelog-master.xml
  * [https://github.com/ryl/cybr406-user-private/blob/8c0e7e54d26dd4cc57d8e36f75cef6d78da687a2/src/main/resources/db/changelog/db.changelog-master.xml#L25-L58](https://github.com/ryl/cybr406-user-private/blob/8c0e7e54d26dd4cc57d8e36f75cef6d78da687a2/src/main/resources/db/changelog/db.changelog-master.xml#L25-L58)
* Update authentication manager:
  ```
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource);
  }
  ```
* Make sure your client has `post` resource`
```
@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("api")
                    .resourceIds("profile", "post")
```
