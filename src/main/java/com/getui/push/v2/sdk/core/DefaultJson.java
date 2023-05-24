package com.getui.push.v2.sdk.core;

import com.getui.push.v2.sdk.IJson;
import com.getui.push.v2.sdk.dto.res.statistic.StatisticDTO;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;


/**
 * create by getui on 2020/9/25
 *
 * @author getui
 */
public class DefaultJson implements IJson {
    private static final Gson GSON = createGson();

    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(StatisticDTO.class, new JsonDeserializer<StatisticDTO>() {
            @Override
            public StatisticDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                StatisticDTO statisticDTO = new StatisticDTO();
                JsonObject jo = json.getAsJsonObject();
                for (Map.Entry<String, JsonElement> mx : jo.entrySet()) {
                    String key = mx.getKey();
                    JsonElement v = mx.getValue();
                    if (v.isJsonArray()) {
                        statisticDTO.put(key, context.deserialize(v, List.class));
                    } else if (v.isJsonPrimitive()) {
                        Object value = v.getAsString();
                        try {
                            Number numValue = NumberFormat.getInstance().parse((String) value);
                            if (numValue != null && numValue.toString().equals(value)) {
                                if (numValue instanceof Long && (Long) numValue <= Integer.MAX_VALUE && (Long) numValue >= Integer.MIN_VALUE) {
                                    value = Integer.valueOf((String) value);
                                } else {
                                    value = numValue;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                        statisticDTO.put(key, value);
                    } else if (v.isJsonObject()) {
                        statisticDTO.put(key, context.deserialize(v, Map.class));
                    }
                }
                return statisticDTO;
            }
        });
        return gsonBuilder.create();
    }


    @Override
    public String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        return GSON.toJson(obj);
    }

    @Override
    public <T> T fromJson(String jsonString, Type type) {
        return GSON.fromJson(jsonString, type);
    }

    @Override
    public <T> T fromJson(String jsonString, Class<T> tClass) {
        return GSON.fromJson(jsonString, tClass);
    }

}
