# Notes
## Build
```
>> mvn clean install
>> mvn spring-boot:run
```

## Check
```
>> npx @modelcontextprotocol/inspector
```

### SSE
| Setting          | Value                                    |
|------------------|------------------------------------------|
| Transport Type   | `SSE`                                    |
| URL              | `http://localhost:6174/sse`              |

### Streamable HTTP
Add `spring.ai.mcp.server.protocol=STREAMABLE` in __application.properties__.  

| Setting          | Value                                    |
|------------------|------------------------------------------|
| Transport Type   | `Streamable HTTP`                        |
| URL              | `http://localhost:6174/mcp`              |
