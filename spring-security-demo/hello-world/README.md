# Notes

## Test
__Basic__:
```
>> curl http://localhost:8080/hello
```

__Using the curl option -u__, you can specify the username and password to use for server authentication:
```
>> curl -u <username>:<password>  http://localhost:8080/hello
```
The default username is __user__, the default password is randomly generated. You can find the password in the console output when you start the application.

__Using header with Authorization__:
```
>> echo -n <username>:<password> | base64
<base64>

>> curl -H "Authorization: Basic <base64>" http://localhost:8080/hello
```

## Knowledge
### Http Code
HTTP 403 - the server identified the caller of the request, but they don’t have the needed privileges for the call that they are trying to make. (failed authorization)

HTTP 401 - the server doesn’t know who is making the request. (failed authentication)
