# Spring Basics

This session will focus on teaching you the basic building blocks of creating a
REST API.

## HTTP Concepts

Knowing the components of URLs, requests, and responses will help you craft
API's that machines and other developers can intuitively understand.

* [The parts of a URL](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/What_is_a_URL)
* [Requests](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview#Requests)
* [Responses](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview#Responses)
* [Methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods)

## Annotations

Annotations are a Java language feature that help you describe your program.
Annotations can accompany classes, methods, and parameters. Most of what
we accomplish early on will be done through annotations.

* `@SpringBootApplication`  
  Identifies the main class of the application.
* `@RestController`  
  Add this annotation to a class to mark it as a `Controller`. Controllers
  handle requests made to the server via their methods.
* `@RequestMapping`  
  Add this annotation to a controller class method to create a new endpoint with
  a given URL.
* `@GetMapping`, `@PostMapping`, `@PutMapping`, etc.  
  These work the same as `@RequestMapping`, but only for one particular HTTP
  method. I recommend using these annotations instead of `@RequestMapping` when
  only one HTTP method is really appropriate.
* `@RequestParam`  
  Marks a method argument as a request parameter.
* `@PathVariable`
  Marks a method argument as a path variable. The name of the method variable
  should match a section of the URL enclosed in curly braces.
  ```
  @GetMapping("/path/to/{name}")
  public String pathGreeting(@PathVariable String name)  {
  ...
  ```
* `@Value`  
  Apply a value from the application's properties to a Java variable.

### Hint

If you want to get an idea of what an annotation can do, add some parenthesis
after it, place your cursor inside, and use the shortcut `Ctrl + P` to see the
various arguments the annotation accepts. You can learn a lot about the
annotations capabilities by doing this.

## Generate a New Project.

* Generate a new project using
  [Spring Boot Initializer](https://start.spring.io/)
    * Choose `Gradle Project` as your project format.
    * Use "com.cybr406" as the group.
    * Use "basics" as the artifact name.
    * Add the "Web" dependency only.
    * Generate and download the project zip file.
* Extract the zip.
* Import the extracted folder using IntelliJ. When prompted, select the Gradle
  format.
* You should now be looking a very minimal Spring Boot application you can
  start building off of.
* Create a new class to act as a controller.
* See if you can create an endpoint that will return a `String` that reads
  "Hello, world."

## Unit Test Challenges.

Add [BasicsApplicationTests.java](../files/BasicsApplicationTests.java) to your project's test folder. See if you can get all the tests passing.
