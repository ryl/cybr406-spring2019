# Database Migrations

When an embedded database is combined with JPA, Spring Boot provides a pretty
good out-of-the-box experience. Since the embedded database only resides in
memory, it is recreated each time the application starts up, allowing it to
easily evolve as your Java objects evolve. In this respect an embedded database
is perfect for early prototyping and unit testing.

Things become more difficult when we switch to a persisted database because we
want our data to survive application restarts, crashes, and deployments. There
are several approaches to managing the lifecycle of the database used by your
application.

## Managing Datasources

A good approach to take when making the switch from an embedded database to a
persisted database is to configure different `datasources` in different Spring
profiles. This approach gives you the power of choice. You can **choose** to
have a flexible, in-memory database for rapid prototyping, or you can **choose**
to have stable, long-lived data. Consider the following setup:

* `application.properties` (default profile used for local development and experimentation)
* `application-prod.properties` (a profile for the production environment)

In the first `application.properties` we can configure our application to connect
to an in-memory database, and in the second `application-prod.properties` we can
connect to a persisted database. Each file can contain a different set of the
the properties listed below:

* `spring.datasource.*`
* See [Common Spring Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html) for the comprehensive list.

The `spring.datasource.*` properties allow you to fine tune how your application
connects to a database. Let's take a look at an example configuration below:

**build.gradle**

    // Embedded database dependency
    runtimeOnly 'com.h2database:h2'

    // New dependency for connecting to a persisted database.
    runtimeOnly 'mysql:mysql-connector-java'

**application.properties**
    # No changes needed. Spring will default to using an embedded database
    # if one can be found on the classpath.

**application-prod.properties**

    # Connect to a mysql database running on the same server as the application
    spring.datasource.url=jdbc:mysql://localhost/dbdemo

    # Connect using the following username
    spring.datasource.username=dbdemo

    # Connect using the following password
    spring.datasource.password=dbdemo

NOTE: In a real application, you should NEVER have a password appear in a file
that gets committed to a repository.

You can control which profile your application
runs with by using the `--spring.profiles.active={your-profile-here}` argument.

## JPA Migrations

## Liquibase Migrations

* [Liquibase Home](https://www.liquibase.org/)
* [Liquibase Changes Documentation](https://www.liquibase.org/documentation/changes/index.html)
