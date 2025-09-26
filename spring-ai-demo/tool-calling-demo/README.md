# Notes
## Test
### H2
Go to `http://localhost:8080/h2-console`.

### Request
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/test/chat
```

```
>> curl -G -H "username: Ben" --data-urlencode "message=What is current time in Shanghai, China?" http://localhost:8080/api/tools/local-time
```

```
>> curl -G -H "username: Ben" --data-urlencode "message=I am not able to login into my account, seems the account is locked." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Ben" --data-urlencode "message=Yes, my account name is Ben, my email is benjamin2714@gmail.com, and my last login attempt is today morning." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Ben" --data-urlencode "message=What is the status of my ticket" http://localhost:8080/api/tools/help-desk
```

```
>> curl -G -H "username: Tom" --data-urlencode "message=Today afternoon after 5 PM I am not able to access my profile, it says unexpected error. My account name is Tom, and my email is tom3678@gmail.com. You can create the ticket directly if you think it is necessary." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Tom" --data-urlencode "message=What is the status of my ticket" http://localhost:8080/api/tools/help-desk
```
