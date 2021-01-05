package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.IJson;
import com.getui.push.v2.sdk.core.DefaultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

public final class Jsons {

    static IJson json = new DefaultJson();

    private static final Logger logger = LoggerFactory.getLogger(Jsons.class);

    public static void main(String[] args) throws IOException {
    }

    public static <T> T fromJson(String jsonString, Type type) {
        if (jsonString == null || jsonString.trim().length() == 0) {
            return null;
        } else {
            try {
                return json.fromJson(jsonString, type);
            } catch (Exception var3) {
                logger.error("parse json string to object error, input=" + jsonString, var3);
                return null;
            }
        }
    }

}
