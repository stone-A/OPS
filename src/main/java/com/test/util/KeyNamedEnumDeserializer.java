package com.test.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.test.common.constants.KeyNamed;

import java.io.IOException;

/**
 * 为{@link KeyNamed}提供一个自定义的反序列化器

 */
public class KeyNamedEnumDeserializer<E extends Enum & KeyNamed> extends JsonDeserializer<E> {
    Class<E> targetClass;

    public KeyNamedEnumDeserializer(Class<E> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Integer key = p.getCodec().readValue(p, Integer.class);
        if (key == null) {
            return null;
        }
        E[] enums = targetClass.getEnumConstants();
        for (E e : enums) {
            if (key.equals(e.getKey())) {
                return e;
            }
        }
        return null;
    }
}
