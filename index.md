# Communication

* Ry Lowry (lowryrs@unk.edu)
* [Slack](https://cybr406.slack.com)

# Resources

* [Spring Boot Initializer][] - Generate project skeletons.
* [Common Spring Properties][] - Adjust the many settings provided by Spring.
* [WizTools RESTClient][]  - A Java based REST GUI.
* [Postman][] - A nice REST GUI.
* [Heroku Project Setup][]
* [GitHub + Heroku Project Setup][]
* [Git][]

# Class Log

| Date | Topic | Notes | Demos | Homework | Due |
|------|-------|-------|-------|----------|-----|
| 01/31/19 | Database migrations | [Database Migrations][] | | [Homework 2][] | 02/06/19 |
| 02/05/19 | User microservice, Spring Data REST | [Spring Data REST][] | [Books Demo][] | | |
| 02/07/19 | Deploy user microservice, gateways | [Gateways][] | | | |
| 02/12/19 | Deploy gateway, start security | [Security - Basics][] | [Books Demo][] | | |
| 02/14/19 | Discussed CSRF, Sessions, and HTTP Basic Auth | [Security - Advanced][] | | | |
| 02/19/19 | Method level security. Writing users to DB via code. | | | | |
| 02/21/19 | Method level security. Registration. | [Security - Checklist][] | | [Homework 3][] | 03/01/19 |
| 02/21/19 | Work Day | | | | |
| 02/26/19 | Work Day | | | | |
| 02/28/19 | Work Day | | | | |
| 03/05/19 | [Event Handler Shortcomings][] | | | | |
| 03/07/19 | [Security - OAuth][] | | | | |
| 03/07/19 | [Buckle Open House 6pm - 8pm][open-house] | [Google Maps](https://goo.gl/maps/4VzUbQfjgWk) | | | |
| 03/12/19 | Work Day | | | [Homework 4][] | After spring break |
| 03/14/19 | Class Project Intro | | | | |

# Class Topics

**January**

* **Tuesday, January 8th**  
  Class introduction.
    * What is a monolith?
    * What are microservices?
    * Introduce course tools.
    * [Homework - Setup Accounts (Due on 01/10/19 start of class)](/heroku-setup.md)
* **Thursday, January 10th**  
  [Notes - Spring Basics](/notes/spring-basics.md)  
  Cover the basics of the Spring framework. Deploy to Heroku.
    * Procfile
    * Application profiles
    * HTTP Verbs
    * Request parameters
    * Path variables
    * Posted input
    * Introduce validation.
* **Tuesday, January 15th**  
  [Notes - In-Memory Databases](/notes/in-memory-db.md)  
  Start saving data.
    * Introduce in-memory database.
    * Create `Post`.
    * Create `PostRepository`.
    * Include some dummy data to experiment with.
    * `Pageable`, `Page`, and `PageImpl`
* **Thursday, January 17th**  
  Work Day
    * Create endpoints to manage Posts.
    * Create (POST)
    * Retrieve (GET)
      * Page of posts.
      * Individual items.
    * Update (PUT)
    * Delete (DELETE)
    * Include validation.
    * [Homework - Create Post Endpoints (Due on 01/25/19 by 5:00pm)](/homework/homework01-post-database.md)
* **Tuesday, January 22nd**  
  Hey, I'm sick of my data disappearing after a restart. I want a _real_ database!
    * The trouble with changing a data model backed by a persisted database.
    * Solution: database migrations.
    * Why are they needed?
    * What are the advantages?
* **Thursday, January 24th**
  [Notes - Database Migrations](/notes/database-migrations.md)  
  Work Day.
    * Add PostgreSQL addon to Heroku project.
    * Write migration file with `post` table.
    * Add `date` to model
    * Add new column via migration.
    * [Homework - Migrate project from in-memory database to persisted database (Due 02/06/19 by 5:00)](/homework/homework02-persisted-database.md)
* **Tuesday, January 29th**  
  New microservice: `user`
    * Manage a list of users.
    * Introduce spring-data-rest.
    * Discuss PATCH
    * Deploy new microservice to Heroku.
* **Thursday, January 31st**  
  Introduction to gateways, they really tie the room together.
    * Why is it useful?
    * How can we implement it in spring?

**February**

* **Tuesday, February 5th**  
  Spring Security Discussion
    * In memory user details
    * Roles
    * Securing urls
    * Http basic
* **Thursday, February 7th**  
  Spring Security Discussion...Continued
    * Access security information within a controller.
    * Access security information from arbitrary class.
    * Method level security for extra granularity
    * Managing users in the database.
* **Tuesday, February 12th**  
  Work Day.
  * Add security to the user microservice.
  * Create registration endpoint.
    * Create a `User` and a `Profile` during registration.
    * Only ROLE_ADMIN can manage users.
  * Users can manage their own Profile
  * Homework - Add a basic security layer (Due 02/19/19 by 5:00)
* **Thursday, February 14th**  
  Work Day.
    * Continue working on homework.
* **Tuesday, February 19th**  
  Problem: I want to secure the `post` microservice, but all my users are in the `user` microservice.
    * Discuss OAuth 2.
    * Token based authentication.
    * Choosing a token strategy.
* **Thursday, February 21st**  
  Discuss Authorization Server
    * Clients
* **Tuesday, February 26th**  
  Discuss Resource Server
    * Avoiding overlap with security layer
    * Tying it together with RemoteTokenServices
* **Thursday, February 28th**  
  Work Day.
    * Homework - Secure `user` and `post` microservice with OAuth2.

**March**

* **Tuesday, March 5th**  
  Work Day.
    * Continue working on homework.
* **Thursday, March 7th**  
  Problem: I want to get a `Post` combined with the author's `Profile` in one rest call.
    * Orchestration
    * Aggregation
    * Discovery
* **Tuesday, March 12th**  
  Add a discovery service.
* **Thursday, March 14th**  
  Introduce final project. Start covering Unit Testing
  Work Day.
    * Homework - Final project proposal (Due March 26th start of class).
    * Homework - Add discovery to the system (Due March 28th by 5:00pm)
* **Tuesday, March 19th**  
  No class - Spring Break
* **Thursday, March 21st**  
  No class - Spring Break
* **Tuesday, March 26th**  
  Work Day.
    * Hand in project proposal.
    * Continue discovery service homework.
* **Thursday, March 28th**  
  Testing with Spring
  * Basic setup
  * Assertion basics
  * `@Test`
  * `@Before`
  * `@After`

**April**

* **Tuesday, April 2nd**
  Continue with Unit testing.
    * Mock MVC
    * Converting JSON to Java objects.
    * Mockito
* **Thursday, April 4th**
  Continue with Unit testing.
    * Testing an OAuth authentication server
    * Testing a Resource server independently of a real authentication server.
* **Tuesday, April 9th**
  Work Day.
    * Homework - Unit test user registration
    * Test user registration
      * Valid user
      * Invalid user
* **Tuesday, April 11th**  
  Keep your secrets safe using a secret store
* **Thursday, April 12th**  
  Event driven architecture
    * Discuss RabbitMQ
* **Thursday, April 16th**  
  Event driven architecture continued.
    * Create a listener
* **Tuesday, April 18th**  
  RabbitMQ
    * Send a message
* **Thursday, April 23rd**  
  Final project work day
* **Tuesday, April 25th**  
  Final project work day
* **Thursday, April 30th**  
  Finals Week - Present Final projects

<!-- Resources -->
[Spring Boot Initializer]: https://start.spring.io
[Common Spring Properties]: https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
[WizTools RESTClient]: https://github.com/wiztools/rest-client/releases/download/3.7.1/restclient-ui-fat-3.7.1.jar
[Postman]: https://www.getpostman.com
[Heroku Project Setup]: /notes/heroku-project-setup.md
[GitHub + Heroku Project Setup]: /notes/github-project-setup.md
[Git]: /notes/git.md

<!-- Notes -->
[Database Migrations]: /notes/database-migrations.md
[Spring Data REST]: /notes/spring-data-rest.md
[Gateways]: /notes/gateways.md
[Security - Basics]: /notes/security-basics.md
[Security - Advanced]: /notes/security-advanced.md
[Security - Checklist]: /notes/security-checklist.md
[open-house]: /notes/open-house.md
[Event Handler Shortcomings]: /notes/event-handler-shortcomings.md
[Security - OAuth]: /notes/security-oauth.md

<!-- Demos -->
[Books Demo]: https://github.com/ryl/cybr406-books

<!-- Homework -->
[Homework 2]: /homework/homework02-persisted-database.md
[Homework 3]: /homework/homework03-user-security.md
[Homework 4]: /homework/homework04-user-oauth.md
