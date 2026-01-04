package org.syh.demo.springai.toolcalling.tools;

import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.util.json.schema.JsonSchemaGenerator;
import org.springframework.stereotype.Component;

@Component
public class TimeTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTools.class);

    public TimeTools() {
        LOGGER.info("TimeTools initialization ...");
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodInputJsonSchema = JsonSchemaGenerator.generateForMethodInput(method);
            LOGGER.info("Method: {}, Input JSON Schema: {}", method.getName(), methodInputJsonSchema);
        }
    }

    @Tool(name = "get_current_local_time", description = "Get the current local time in the user's timezone.")
    public String getCurrentLocalTime() {
        LOGGER.info("Returning the current time in user's local timezone.");
        return LocalTime.now().toString();
    }

    @Tool(name = "get_current_time", description = "Get the current time in the specified time zone.")
    public String getCurrentTime(@ToolParam(required = true, description = "Value representing the time zone") String timeZone) {
        LOGGER.info("Returning the current time in the timezone {}", timeZone);
        return LocalTime.now(ZoneId.of(timeZone)).toString();
    }
}
