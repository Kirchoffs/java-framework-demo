# Notes
## Install & Run Model in Docker
```
>> docker model run ai/qwen3:0.6B-F16 
```

```
>> docker model ls
```

## Test
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/api/chat
```
