# Notes
## Test
### H2 Database
#### In-Memory Database
```properties
spring.datasource.url=jdbc:h2:mem:h2-db
```

#### On-Disk Database
```properties
spring.datasource.url=jdbc:h2:file:${user.dir}/tmp/h2-db;AUTO_SERVER=true
```

#### Dashboard
Go to `http://localhost:8080/h2-console`.

### Request
#### Smoke Test
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/test/chat
```

#### Time Tool Test
```
>> curl -G -H "username: Ben" --data-urlencode "message=What is current time in Shanghai, China?" http://localhost:8080/api/tools/local-time
```

#### Help Desk Tool Test
```
>> curl -G -H "username: Ben" --data-urlencode "message=I am not able to login into my account, seems the account is locked." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Ben" --data-urlencode "message=Yes, my account name is Ben, my email is benjamin2714@gmail.com, and my last login attempt is today morning." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Ben" --data-urlencode "message=What is the status of my ticket?" http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Ben" --data-urlencode "message=Update any of my tickets to RESOLVED." http://localhost:8080/api/tools/help-desk
```

```
>> curl -G -H "username: Tom" --data-urlencode "message=Today afternoon after 5 PM I am not able to access my profile, it says unexpected error. My account name is Tom, and my email is tom3678@gmail.com. You can create the ticket directly if you think it is necessary." http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Tom" --data-urlencode "message=What is the status of my ticket?" http://localhost:8080/api/tools/help-desk

>> curl -G -H "username: Tom" --data-urlencode "message=I can access my profile now, please update my ticket." http://localhost:8080/api/tools/help-desk
```
