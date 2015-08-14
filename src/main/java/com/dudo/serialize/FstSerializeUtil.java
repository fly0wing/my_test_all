package com.dudo.serialize;

import org.nustaq.serialization.FSTConfiguration;

public final class FstSerializeUtil {
    static FSTConfiguration configuration = FSTConfiguration
            .createStructConfiguration();

    public static String serializeStr(Object object) {
        try {
            byte[] bytes = configuration.asByteArray(object);
            StringBuilder strBuilder = new StringBuilder();
            for (byte b : bytes) {
                strBuilder.append(b).append(',');
            }
            strBuilder.deleteCharAt(strBuilder.length() - 1);
            return strBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object unserializeStr(String str) {
        if (str == null) {
            return null;
        }
        try {
            String[] split = str.split(",");
            byte[] bytes = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bytes[i] = Byte.parseByte(split[i]);
            }
            Object o = configuration.asObject(bytes);
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] serialize(Object object) {
        try {
            return configuration.asByteArray(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return configuration.asObject(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
