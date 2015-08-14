package com.dudo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public final class KryoSerializeUtil {


    public static String serializeStr(Object object) {
        try {
            byte[] bytes = serialize(object);
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
            Object o = unserialize(bytes);
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] serialize(Object object) {
        Kryo kryo = new Kryo();
        byte[] buffer = new byte[2048];
        try {
            Output output = new Output(buffer);
            kryo.writeClassAndObject(output, object);
            return output.toBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object unserialize(byte[] bytes) {
        Kryo kryo = new Kryo();
        try {
            Input input = new Input(bytes);
            return kryo.readClassAndObject(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
