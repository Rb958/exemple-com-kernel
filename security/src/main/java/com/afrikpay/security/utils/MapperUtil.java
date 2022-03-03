package com.afrikpay.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
    private MapperUtil(){}

    public static String object2Json(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static Object json2Object(String jsonString, TypeReference<?> typeReference) throws JsonProcessingException {
        return mapper.readValue(jsonString, typeReference);
    }
}
