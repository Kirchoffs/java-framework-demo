package org.syh.demo.thirdparty.experiment.jackson.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

public class StrictJsonEditor {
    @Getter
    private final JsonNode root;
    private final ObjectMapper mapper;

    public StrictJsonEditor(JsonNode root, ObjectMapper mapper) {
        if (!(root instanceof ObjectNode)) {
            throw new IllegalArgumentException("Root must be an ObjectNode");
        }
        this.root = root;
        this.mapper = mapper;
    }

    public void set(String path, Object value) {
        PathInfo pathInfo = PathInfo.parse(path);
        JsonNode parent = navigateToPath(pathInfo.parentPath, true);
        JsonNode valueTree = mapper.valueToTree(value);

        if (pathInfo.isArray) {
            handleArraySegment(parent, pathInfo.leaf, valueTree);
        } else {
            if (!(parent instanceof ObjectNode objectNode)) {
                throw new IllegalStateException("Node at path [" + pathInfo.parentPath + "] is not an ObjectNode, cannot set field: " + pathInfo.leaf);
            }
            objectNode.set(pathInfo.leaf, valueTree);
        }
    }

    public void append(String path, Object value) {
        JsonNode target = navigateToPath(path, true);

        if (!target.isMissingNode() && !target.isArray()) {
            throw new IllegalStateException("Node at path [" + path + "] already exists and is not an ArrayNode");
        }

        if (target.isMissingNode()) {
            PathInfo pathInfo = PathInfo.parse(path);
            JsonNode parent = navigateToPath(pathInfo.parentPath, true);
            if (parent instanceof ObjectNode objectNode) {
                target = objectNode.putArray(pathInfo.leaf);
            } else {
                throw new IllegalStateException("Cannot create array under non-object node at: " + pathInfo.parentPath);
            }
        }

        ((ArrayNode) target).add(mapper.valueToTree(value));
    }

    public void delete(String path) {
        if (path == null || path.isEmpty()) {
            return;
        }

        PathInfo pathInfo = PathInfo.parse(path);
        JsonNode parent = navigateToPath(pathInfo.parentPath, false);

        if (parent.isMissingNode() || parent.isNull()) {
            return;
        }

        if (pathInfo.isArray) {
            ArrayInfo info = ArrayInfo.parse(path);
            JsonNode node = parent.get(info.fieldName);
            if (node != null && node.isArray()) {
                ArrayNode arrayNode = (ArrayNode) node;
                if (info.index >= 0 && info.index < arrayNode.size()) {
                    arrayNode.remove(info.index);
                }
            }
        } else if (parent instanceof ObjectNode objectNode) {
            objectNode.remove(pathInfo.leaf);
        }
    }

    private JsonNode navigateToPath(String path, boolean createMissing) {
        if (path == null || path.isEmpty()) {
            return root;
        }

        String[] segments = path.split("\\.");
        JsonNode current = root;

        for (String segment : segments) {
            if (segment.contains("[")) {
                current = handleArraySegment(current, segment, null);
            } else {
                if (createMissing) {
                    if (current.isValueNode() || current.isArray()) {
                        throw new IllegalStateException("Cannot access field '" + segment + "' on a non-object node");
                    }

                    ObjectNode currentNode = (ObjectNode) current;
                    JsonNode nextNode = currentNode.path(segment);

                    if (nextNode.isMissingNode() || nextNode.isNull()) {
                        nextNode = currentNode.putObject(segment);
                    }
                    current = nextNode;
                } else {
                    current = current.path(segment);
                }
            }

            if (!createMissing && current.isMissingNode()) break;
        }
        return current;
    }

    private JsonNode handleArraySegment(JsonNode current, String segment, JsonNode valueToSet) {
        ArrayInfo info = ArrayInfo.parse(segment);

        if (!(current instanceof ObjectNode currentNode)) {
            throw new IllegalStateException("Cannot access array '" + info.fieldName + "' on a non-object node");
        }

        JsonNode node = currentNode.path(info.fieldName);

        if (!node.isMissingNode() && !node.isNull() && !node.isArray()) {
            throw new IllegalStateException("Field '" + info.fieldName + "' is not an ArrayNode");
        }

        if (node.isMissingNode() || node.isNull()) {
            node = currentNode.putArray(info.fieldName);
        }

        ArrayNode arrayNode = (ArrayNode) node;

        while (arrayNode.size() <= info.index) {
            arrayNode.addNull();
        }

        if (valueToSet != null) {
            arrayNode.set(info.index, valueToSet);
        } else {
            JsonNode element = arrayNode.get(info.index);
            if (element == null || element.isMissingNode() || element.isNull()) {
                arrayNode.set(info.index, mapper.createObjectNode());
            } else if (!element.isObject()) {
                throw new IllegalStateException("Array element at [" + info.index + "] is not an ObjectNode");
            }
        }
        return arrayNode.get(info.index);
    }

    private static class ArrayInfo {
        public String fieldName;
        public int index;

        ArrayInfo(String fieldName, int index) {
            this.fieldName = fieldName;
            this.index = index;
        }

        public static ArrayInfo parse(String segment) {
            int open = segment.indexOf('[');
            int close = segment.indexOf(']');
            String fieldName = segment.substring(0, open);
            int index = Integer.parseInt(segment.substring(open + 1, close));
            return new ArrayInfo(fieldName, index);
        }
    }

    private static class PathInfo {
        public String parentPath;
        public String leaf;
        public boolean isArray;

        public PathInfo(String parentPath, String leaf, boolean isArray) {
            this.parentPath = parentPath;
            this.leaf = leaf;
            this.isArray = isArray;
        }

        public static PathInfo parse(String path) {
            String parentPath = "";
            String leaf = path;

            int lastDot = path.lastIndexOf('.');
            if (lastDot != -1) {
                parentPath = path.substring(0, lastDot);
                leaf = path.substring(lastDot + 1);
            }
            boolean isArray = leaf.contains("[");

            return new PathInfo(parentPath, leaf, isArray);
        }
    }
}
