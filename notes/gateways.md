# Gateways

A gateway can help you make all your individual microservices feel like a
unified whole.

**Examples**

* [Spring Cloud Gateway][]
* [Kong][]
* [Amazon API Gateway][]

## Spring Cloud Gateway

We will use [Spring Cloud Gateway][] because it can be written as a Java app
much like the microservices we have already made. Spring Cloud Gateway provides
a DSL (Domain Specific Language) for describing routes to other applications.
Each route can perform additional modifications and transformations to the
requests and responses it receives.

* **Route** - A rule that routes an incoming request elsewhere.
* **Predicate** - A condition that must be true for a route to be triggered.
* **Filters** - Transformations that can occur before or after the route is triggered.
* [See the documentation for a complete list of predicates and filters.][Docs]

[Spring Cloud Gateway]: https://spring.io/projects/spring-cloud-gateway
[Docs]: https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.0.RELEASE/single/spring-cloud-gateway.html#gateway-how-it-works
[Kong]: https://konghq.com/solutions/gateway/
[Amazon API Gateway]: https://aws.amazon.com/api-gateway/
