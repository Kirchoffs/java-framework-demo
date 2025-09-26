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

## Miscellaneous
### Multiple Beans Check
```java
@Bean
public ApplicationRunner runner(ApplicationContext ctx) {
  return args -> {
    System.out.println("Beans of type ConfigBean:");
    String[] beanNames = ctx.getBeanNamesForType(ConfigBean.class);
    for (String name : beanNames) {
      Object bean = ctx.getBean(name);
      System.out.println(" - " + name + " -> @" + System.identityHashCode(bean));
    }

    ConfigBean byType = ctx.getBean(ConfigBean.class);
    System.out.println("By type ConfigBean -> @" + System.identityHashCode(byType));

    Object byName;
    byName = ctx.getBean("customConfigBean");
    System.out.println("By name customConfigBean -> @" + System.identityHashCode(byName));
    byName = ctx.getBean("configBean");
    System.out.println("By name configBean -> @" + System.identityHashCode(byName));
  };
}
```
