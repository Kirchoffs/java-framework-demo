# Notes
## Run
```
>> export OPENAI_API_KEY=...
>> export GITHUB_PERSONAL_ACCESS_TOKEN=...

>> mvn spring-boot:run
```

## Test
### Smoke Test
```
>> curl -G --data-urlencode "message=How are you" http://localhost:8080/test/chat
```

```
>> curl -G --data-urlencode "message=Who are you and how can you help me?" http://localhost:8080/api/mcp-client/chat
```

```
>> curl -G --data-urlencode "message=What kind of tools do you have?" http://localhost:8080/api/mcp-client/chat
```

### Filesystem MCP
```
>> curl -G --data-urlencode "message=Create a file named test.json with any content you like in the tmp directory." http://localhost:8080/api/mcp-client/chat

>> curl -G --data-urlencode "message=Tell me your current directory path." http://localhost:8080/api/mcp-client/chat

>> curl -G --data-urlencode "message=What do you have in projects folder" http://localhost:8080/api/mcp-client/chat
```

### Github MCP
```
>> curl -G --data-urlencode "message=List down the Github repositories of Kirchoffs" http://localhost:8080/api/mcp-client/chat
```

### Demo Remote MCP
```
>> curl -G --data-urlencode "message=Create a ticket for me with account 'Ben' and issue description 'Ben has worked for a long time'." http://localhost:8080/api/mcp-client/chat
```

## MCP Server Configuration
### MCP Server Without Environment Parameters
#### Docker
```json
{
  "mcpServers": {
    "filesystem": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "--mount", "type=bind,src=/Users/username/tmp,dst=/projects/tmp",
        "-w",
        "/projects",
        "mcp/filesystem",
        "/projects"
      ]
    }
  }
}
```

#### NPX
```json
{
  "mcpServers": {
    "filesystem": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "/Users/username/tmp"
      ]
    }
  }
}
```

### MCP Server With Environment Parameters
```json
{
  "mcpServers": {
    "github": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "-e",
        "GITHUB_PERSONAL_ACCESS_TOKEN",
        "ghcr.io/github/github-mcp-server"
      ]
    }
  }
}
```

or

```json
{
  "mcpServers": {
    "github": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "-e",
        "GITHUB_PERSONAL_ACCESS_TOKEN",
        "ghcr.io/github/github-mcp-server"
      ],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "put_your_github_access_token_here"
      }
    }
  }
}
```

## MCP Inspector
```
>> npx @modelcontextprotocol/inspector
```

### Filesystem MCP (NPX)
Use `npx` as __Command__.  
Use `-y @modelcontextprotocol/server-filesystem /Users/username/tmp` as __Arguments__.

### Github MCP (Docker)
Use `docker` as __Command__.  
Use `run -i --rm -e GITHUB_PERSONAL_ACCESS_TOKEN ghcr.io/github/github-mcp-server` as __Arguments__.  
Add __Environment Variebles__ for `GITHUB_PERSONAL_ACCESS_TOKEN`.
