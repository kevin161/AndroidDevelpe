package com.gyz.androiddevelope.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.GsonUtils.java
 * @author: ZhaoHao
 * @date: 2016-11-23 16:14
 */
public class GsonUtils  {
    private static final String TAG = "GsonUtils";

    /**
     * 创建可解析泛型与boolean 类型转化的GsonBuilder
     *
     * @return GsonBuilder
     * */
    public static GsonBuilder createBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Boolean.class, booleanAsIntAdapter);
        builder.registerTypeAdapter(boolean.class, booleanAsIntAdapter);
        builder.registerTypeAdapterFactory(new TypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> tTypeToken) {
                final Class<T> rawType = (Class<T>) tTypeToken.getRawType();
                final T[] types = rawType.getEnumConstants();
                if (rawType.isEnum()) {
                    return new TypeAdapter<T>() {
                        public void write(JsonWriter out, T value)
                                throws IOException {
                            if (value == null) {
                                out.nullValue();
                            } else {
                                int index = 0;
                                for (int i = 0; i < types.length; i++) {
                                    if (types[i].toString().equals(
                                            value.toString())) {
                                        index = i;
                                        break;
                                    }
                                }
                                out.value(index);
                            }
                        }

                        public T read(JsonReader reader) throws IOException {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                return null;
                            } else {
                                return types[reader.nextInt()];
                            }
                        }
                    };
                }
                return null;
            }
        });
        return builder;
    }


    private static final TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {
        @Override
        public void write(JsonWriter jsonWriter, Boolean aBoolean)
                throws IOException {
            if (null == aBoolean) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(aBoolean ? 1 : 0);
            }
        }

        @Override
        public Boolean read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            switch (peek) {
                case BOOLEAN:
                    return jsonReader.nextBoolean();
                case NULL:
                    jsonReader.nextNull();
                    return null;
                case NUMBER:
                    return jsonReader.nextInt() != 0;
                case STRING:
                    return Boolean.parseBoolean(jsonReader.nextString());
                default:
                    throw new IllegalStateException(
                            "Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
    };
}
