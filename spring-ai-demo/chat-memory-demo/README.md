# Notes
## Install & Run Model in Docker
```
>> docker model run ai/qwen3:0.6B-F16 
```

## Test
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/api/chat

>> curl -G -H "username: Tom" --data-urlencode "message=How are you, my name is Tom" http://localhost:8080/api/chat-memory
>> curl -G -H "username: Tom" --data-urlencode "message=I just told you my name, do you still remember it" http://localhost:8080/api/chat-memory
```
