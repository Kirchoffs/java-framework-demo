# Notes
## Install & Run Model in Docker (Chat Model)
```
>> docker model run ai/qwen3:4B-F16
```

```
>> docker model run ai/qwen3:0.6B-F16 
```

```
>> docker model ls
```

## Instal & Run Model in Docker (Embedding Model)
```
>> ollama pull embeddinggemma:300m
>> ollama run embeddinggemma:300m "apple"
```

```
>> ollama list
```

## Qdrant
### HTTP / REST API + Dashboard
Go to `http://localhost:6333/dashboard`

### gRPC API
```
spring.ai.vectorstore.qdrant.initialize-schema=true
spring.ai.vectorstore.host=localhost
spring.ai.vectorstore.port=6334
spring.ai.vectorstore.collection-name=test_vector_db
```

### Console Command
#### General information
```
GET collections
GET collections/test_vector_db
```

```
POST collections/test_vector_db/points/scroll
{
  "limit": 5,
  "with_payload": true,
  "with_vector": false
}
```

#### Exact-match search
```
POST collections/test_vector_db/points/scroll
{
  "filter": {
    "must": [
      {
        "key": "doc_content",
        "match": {
          "value": "Earth's poles are moving."
        }
      }
    ]
  },
  "limit": 5,
  "with_payload": true,
  "with_vector": true
}
```

#### Vector search
```
POST collections/test_vector_db/points/search
{
  "vector": [0.618, 0.367, 0.318, ...],
  "limit": 3,
  "with_payload": true
}
```

You can use JS code below to generate a random vector:
```
>> JSON.stringify(Array.from({length: 768}, () => Math.random()))
```

#### Recommend similar items
```
POST collections/test_vector_db/points/recommend
{
  "positive": ["..."],
  "limit": 3,
  "with_payload": true
}
```

## Test
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/test/chat
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/test/embedding
```

```
>> curl -G -H "username: Ben" --data-urlencode "message=Tell me a fact about snails." http://localhost:8080/api/rag/fun-facts
>> curl -G -H "username: Ben" --data-urlencode "message=Tell me a fact about the sea." http://localhost:8080/api/rag/fun-facts
```

```
>> curl -G -H "username: Ben" --data-urlencode "message=Tell me a fact about the REPLUG." http://localhost:8080/api/rag/rag-paper
>> curl -G -H "username: Ben" --data-urlencode "message=Tell me a fact about the REPLUG." http://localhost:8080/api/rag/rag-paper-with-advisor
```

```
>> curl -G -H "username: Ben" --data-urlencode "message=What is the stock price of Google today?" http://localhost:8080/api/rag/web-search
```
