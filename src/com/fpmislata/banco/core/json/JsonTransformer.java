package com.fpmislata.banco.core.json;

/**
 *
 * @author Samuel Lao
 */
public interface JsonTransformer {
    String objectToJson(Object o);
    <T> T jsonToObject(String json, Class <T> clazz);
}
