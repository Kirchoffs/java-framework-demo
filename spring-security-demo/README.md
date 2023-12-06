# Notes

- UserDetails, which describes the user for Spring Security.

- GrantedAuthority, which allows us to define actions that the user can execute.

- UserDetailsService, which loads the user details by username.

- UserDetailsManager, which extends the UserDetailsService contract. Beyond the inherited behavior, it also describes actions like creating a user and modifying or deleting a user’s password.

- The __AuthenticationFilter__ intercepts the request and delegates the authentication responsibility to the __AuthenticationManager__. To implement the authentication logic, the __AuthenticationManager__ uses an authentication provider, which is __AuthenticationProvider__. To check the username and the password, the __AuthenticationProvider__ uses a __UserDetailsService__ and a __PasswordEncoder__.

- The __AuthenticationProvider__ uses __UserDetailsService__ to load the user details in the authentication logic. We might implement the UserDetailsService interface to load the user details from a database or any other source.
