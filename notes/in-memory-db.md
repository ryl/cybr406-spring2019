# In-Memory Databases

An in-memory database is a database that, not surprisingly, only lives in
memory. Contrast this with a persisted database, which stores its data in a
permanent fashion on some kind of media, like a hard drive.

In-memory databases are interesting because they only exist while the
application is running. When an in-memory database process is terminated, all
the data entered into it vanishes, never to be recovered again. This leads to
the question, "how is an in-memory database useful?"

* **They are small and embeddable.** Rather than requiring a separate piece of
  database software be installed on the server, the database can be packaged
  into the program itself. This also makes life easier for your coworkers. They
  can run your program without additional install tasks. _Just checkout and go._
* When writing unit tests, it is important for tests to be independent of each
  other. Having a database that can forget all the data entered into it between
  tests helps prevent tests from affecting one another.

###### Real World Examples

|      In-Memory         |      Persisted                      |
|------------------------|-------------------------------------|
| [H2] (Used in class)   | [Mysql]                             |
| [HSQLDB]               | [PostgreSQ] (Used later in class)   |

## Get Started With H2

In this class we will start out with H2. You can add H2 to a new project using
Spring Boot Initializer, or by manually adding a dependency to `build.gradle`.

    dependencies {
        runtime('com.h2database:h2')
    }

Notice how this library was added as a `runtime` dependency. This means the jar
is only needed when the application is running, and is not needed to compile the
program itself. Spring will automatically detect this library when your
application starts and make use of it.

## JPA

Short for Java Persistence API, JPA adds a layer between your Java application
and your database. JPA can be added via Spring Boot Initializer or manually.

    dependencies {
        implementation('org.springframework.boot:spring-boot-starter-data-jpa')
    }

This dependency is added as an `implementation` dependency, meaning it is
required at compile time.



[h2]: http://www.h2database.com/html/main.html
[HSQLDB]: http://hsqldb.org/
[Mysql]: http://www.h2database.com/html/main.html
[PostgreSQ]: http://www.h2database.com/html/main.html
