# Notes

## Lazy Annotation
By default, Spring initializes all singleton beans when the application context is created. This can lead to unnecessary overhead, especially for beans that might not be used immediately or frequently. The @Lazy annotation can be used to indicate that a bean should be lazily initialized, meaning it will be created only when it's actually requested for the first time.

While the @Lazy annotation itself is not intended to solve cyclic reference problems, it can help mitigate such issues to some extent. When you have a cyclic dependency between beans, using @Lazy on one or both of the beans involved might help by delaying the initialization of one of the beans until it's explicitly needed. This can break the initialization cycle and prevent certain types of circular reference exceptions.
