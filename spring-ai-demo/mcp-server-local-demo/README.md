# Notes
## Build
```
>> mvn clean install
```

## Check
```
>> npx @modelcontextprotocol/inspector
```

| Setting          | Value                                    |
|------------------|------------------------------------------|
| Transport Type   | `STDIO`                                  |
| Command          | `java`                                   |
| Arguments        | `-jar /path/to/your/mcp/server/jar`      |

## JDK Setup
```
>> brew install jenv
>> echo 'export PATH="$HOME/.jenv/bin:$PATH"' >> ~/.zshrc
>> echo 'eval "$(jenv init -)"' >> ~/.zshrc
```

```
>> brew install openjdk@17
```

```
>> jenv add /path/to/your/jdk
>> jenv versions
>> ls ~/.jenv/versions
```

```
>> jenv global OpenJDK-17
```
