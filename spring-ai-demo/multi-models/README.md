# Notes
## Install & Run Model in Docker
```
>> docker model run ai/qwen3:0.6B-F16 
```

## Install & Run Model in Ollama
```
>> ollama run llama3.2:1b
```

## Test
```
>> curl -G http://localhost:8080/api/openai/chat --data-urlencode "message=Hello qwen"
>> curl -G http://localhost:8080/api/ollama/chat --data-urlencode "message=Hello llama"
```
