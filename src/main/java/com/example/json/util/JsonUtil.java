package com.example.json.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public final class JsonUtil {
    public static final String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        return mapper.writeValueAsString(object);
    }

}
