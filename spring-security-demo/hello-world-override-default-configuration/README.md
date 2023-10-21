# Notes

## Test
```
>> curl -u john:42 http://localhost:8080/hello
```

## Spring Security Knowledge
### UserDetailsService & PasswordEncoder
Another way for configuring:
```
@Configuration
public class Config extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User
            .withUsername("john")
            .password("42")
            .authorities("read")
            .build();
        
        userDetailsService.createUser(user);
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
```