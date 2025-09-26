package org.syh.demo.thirdparty.experiment.jackson.jsonpath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrictJsonEditorTest {
    public static void main(String[] args) throws Exception {
        testFromEmptyJson();
        testFromJson();
    }

    private static void testFromEmptyJson()  throws Exception {
        System.out.println("======== Test From Empty Json ========");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        StrictJsonEditor editor = new StrictJsonEditor(root, mapper);

        // 1. Set values
        editor.set("app.config.version", 1.8);
        editor.set("app.users[0].name", "John");

        // 2. Append to array
        editor.set("app.tags", mapper.createArrayNode()); // Ensure array exists
        editor.append("app.tags", "Internal");
        editor.append("app.tags", "Production");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(editor.getRoot()));

        // 3. Delete
        editor.delete("app.config.version");

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(editor.getRoot()));
        System.out.println();
    }

    private static void testFromJson() throws Exception {
        System.out.println("======== Test From Json ========");

        ObjectMapper mapper = new ObjectMapper();

        // From POJO
        System.out.println("==== Test From POJO ====");

        UserProfile user = new UserProfile("Alice", 25, Arrays.asList("Java", "AI"));
        ObjectNode rootFromPojo = mapper.valueToTree(user);
        StrictJsonEditor editor1 = new StrictJsonEditor(rootFromPojo, mapper);

        System.out.println("Initial JSON (From POJO): " + rootFromPojo);

        editor1.set("name", "Alice Smith");
        editor1.append("tags", "Spring");

        System.out.println("After valid ops: " + editor1.getRoot());

        try {
            editor1.set("name.firstName", "Alice");
        } catch (IllegalStateException e) {
            System.err.println("Caught expected error (ValueNode conflict): " + e.getMessage());
        }
        System.out.println();

        // From Map
        System.out.println("==== Test From Map ====");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 99);
        dataMap.put("info", new HashMap<String, String>() {{ put("status", "active"); }});

        ObjectNode rootFromMap = mapper.valueToTree(dataMap);
        StrictJsonEditor editor2 = new StrictJsonEditor(rootFromMap, mapper);

        System.out.println("Initial JSON (From Map): " + rootFromMap);

        editor2.set("info.lastLogin", "1995-12-12");

        try {
            editor2.append("info", "new-element");
        } catch (IllegalStateException e) {
            System.err.println("Caught expected error (ObjectNode as Array conflict): " + e.getMessage());
        }

        editor2.set("log[0].event", "login");
        try {
            editor2.set("log.meta", "some-data");
        } catch (IllegalStateException e) {
            System.err.println("Caught expected error (ArrayNode as Object conflict): " + e.getMessage());
        }

        System.out.println("After valid ops: " + editor2.getRoot());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserProfile {
        private String name;
        private int age;
        private List<String> tags;
    }
}
