package com.edward.io.base.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
        //objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    public static String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}
