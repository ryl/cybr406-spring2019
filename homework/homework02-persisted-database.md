# Homework - Persisted Database

Due on 02/06/19 by 5:00pm

In this homework assignment you will attach a persisted database to your project
in Heroku and switch from JPA migrations to Liquibase migrations.

## Instructions

* Download the starter project [https://github.com/ryl/cybr406-homework2/archive/v1.0.zip](https://github.com/ryl/cybr406-homework2/archive/v1.0.zip)
* Deploy to Heroku and attach a PostgreSQL database before starting work on unit tests.
* Run `HomeworkTestSuite.java` located in the test folder. Work your way through
  each set of tests until they all pass. Work through the problems in the
  general order they appear in `HomeworkTestSuite.java`.
* Invent a new field (or fields) for the Post object (date posted, star rating,
  etc.) and write a suitable Liquibase change set for the new columns. Deploy
  your additions to Heroku.
* You will earn points for each test that passes, and for inventing new post
  fields with corresponding columns.

## Resources

* [Notes - In-Memory Databases](../notes/in-memory-db.md)
* [Notes - Database Migrations](../notes/database-migrations.md)
* [https://github.com/ryl/cybr406-dbdemo](https://github.com/ryl/cybr406-dbdemo)
  Now includes example Liquibase usage demoed in class.
