# Notes
## Lombok
### The Conflict: @Builder vs. @NoArgsConstructor
When you use both annotations together, the code will fail to compile.

#### Root Cause
`@Builder` Requirement: To function, `@Builder` needs an All-Args Constructor.
If no other constructors are defined, Lombok generates one automatically.

The Interference: When you add `@NoArgsConstructor`, Lombok detects a manual constructor. 
Consequently, it stops generating the All-Args Constructor.

The Result: The Builder tries to call a constructor that no longer exists, leading to a compilation error.

#### Solution
To fix this, you must explicitly add @AllArgsConstructor. This ensures that both the framework (which needs the no-args constructor) and Lombok’s Builder (which needs the all-args constructor) are satisfied.
```java
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
}
```