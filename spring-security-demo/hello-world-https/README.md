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

## HTTPS
### Steps
```
>> openssl req -newkey rsa:2048 -x509 -keyout key.perm -out cert.pem -days 365
```
This command will generate a self-signed certificate and private key. The certificate will be valid for 365 days. The private key will be stored in the file key.perm, and the certificate will be stored in the file cert.pem.  
Here I use `akira` as __PEM pass phrase__.

```
>> openssl pkcs12 -export -in cert.pem -inkey key.perm -out certificate.p12 -name "certificate"
```
This command will generate a PKCS12 keystore file (self-signed certificate) named `certificate.p12`.  
Here I use `akira` as __export password__.

Finally copy the `certificate.p12` to `src/main/resources` folder, and add the following properties to `application.properties`:
```
server.ssl.key-store-type=PKCS12
server.ssl.key-store=classpath:certificate.p12
server.ssl.key-store-password=akira
```
The value of `server.ssl.key-store-password` is the __export password__.

### Test
```
>> echo -n <username>:<password> | base64
<base64>

>> curl -H "Authorization: Basic <base64>" -k https://localhost:8080/hello
```
You can use `-k` option to skip testing the authenticity of the certificate.

## Knowledge
### Http Code
HTTP 403 - the server identified the caller of the request, but they don’t have the needed privileges for the call that they are trying to make. (failed authorization)

HTTP 401 - the server doesn’t know who is making the request. (failed authentication)
