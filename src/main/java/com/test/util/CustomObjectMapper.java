package com.test.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.test.common.constants.Active;

import java.text.SimpleDateFormat;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        super();
        //为null字段时不显示
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //日期格式
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Active.class, new KeyNamedEnumDeserializer<>(Active.class));
        this.registerModule(module);
    }
}
