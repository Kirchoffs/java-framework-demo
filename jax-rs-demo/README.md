# Notes
## Jersey
Jersey is an open source framework for developing RESTful Web Services. It serves as a reference implementation of JAX-RS. Another implementation is RESTEasy.

## JAX-RS Managed Beans
JAX-RS Managed Beans are beans that are managed by the JAX-RS runtime. They are created by the JAX-RS runtime and their lifecycle is managed by the JAX-RS runtime.

Classes managed by JAX-RS include:
- Resource classes: Annotated with @Path.
- Providers: Annotated with @Provider.
- Sub-resource locators: Classes returned from methods annotated with @Path.
- Classes using constructor injection: If the constructor parameters use @Context.

These classes can have JAX-RS context objects (like UriInfo, HttpHeaders, etc.) injected via the @Context annotation because JAX-RS controls their lifecycle.

## JAX-RS Providers
In JAX-RS (and its reference implementation Jersey), the @Provider annotation is used to declare classes that extend the functionality of JAX-RS by implementing specific contracts. These classes, known as providers, can handle various tasks such as mapping exceptions, filtering requests and responses, providing custom entity readers and writers, and more. The @Provider annotation indicates to the JAX-RS runtime that the annotated class should be registered and used as a provider.

- Message Body Readers and Writers
- Exception Mappers
- Context Resolvers
- Filters and Interceptors
