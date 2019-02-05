# Spring Data REST

[Documentation]

Spring Data REST can help you quickly expose repositories over REST.

## Expose a Repository

Annotate your repositories with `@RepositoryRestResource` instead of
`@Repository`.

```
@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {
}
```

## Register Validators

* Extend `RepositoryRestConfigurer`
* Override methods to adjust Spring Data REST behavior.
* Override `configureValidatingRepositoryEventListener` to register validators.
* [Register your validator with an event.](Events)

```
@Configuration
public class BookConfiguration implements RepositoryRestConfigurer {

    @Autowired
    BookValidator bookValidator;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", bookValidator);
        validatingListener.addValidator("beforeSave", bookValidator);
    }
}
```

## Customize Controller Behavior

See [Overriding Spring Data REST Response Handlers].

[Documentation]: https://docs.spring.io/spring-data/rest/docs/current/reference/html/
[Registering Validators]: https://docs.spring.io/spring-data/rest/docs/3.1.4.RELEASE/reference/html/#_assigning_validators_manually
[Events]: https://docs.spring.io/spring-data/rest/docs/3.1.4.RELEASE/reference/html/#events
[Overriding Spring Data REST Response Handlers]: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.overriding-sdr-response-handlers
