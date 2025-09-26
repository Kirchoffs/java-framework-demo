# Notes
## Build
```
>> mvn clean install
>> mvn spring-boot:run
```

## Check
### MCP
```
>> npx @modelcontextprotocol/inspector
```

### Database
Go to `http://localhost:6174/h2-console`.

### SSE
#### Basic
| Setting          | Value                                    |
|------------------|------------------------------------------|
| Transport Type   | `SSE`                                    |
| URL              | `http://localhost:6174/sse`              |

#### Authorization
Fetch the access token from the result of below command:
```
>> curl -XPOST http://localhost:6174/oauth2/token --data grant_type=client_credentials --user mcp-client:secret
```

```
>> curl http://localhost:6174/sse -H"Authorization: Bearer {put_your_access_token_here}"
```

### Streamable HTTP
Add `spring.ai.mcp.server.protocol=STREAMABLE` in __application.properties__.  

| Setting          | Value                                    |
|------------------|------------------------------------------|
| Transport Type   | `Streamable HTTP`                        |
| URL              | `http://localhost:6174/mcp`              |
