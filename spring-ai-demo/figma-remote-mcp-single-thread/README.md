# Notes
## Build
```
>> mvn clean install
>> mvn spring-boot:run
```

## Check
### Inspector
```
>> npx @modelcontextprotocol/inspector -y
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

## MCP Client Config
### Cline
```json
{
  "mcpServers": {
    "mcp-server-remote-demo": {
      "url": "http://localhost:6174/mcp",
      "type": "streamableHttp",
      "headers": {
        "X-Figma-Token": "put_your_figma_token_here"
      }
    }
  }
}
```
