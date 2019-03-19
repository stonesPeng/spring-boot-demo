package com.stone.configuration.utils;

import java.io.*;

/**
 * @author honorstone
 * @date 2019/3/19 16:15
 */
public class SerializeUtils {

    /**
     * jdk 序列化工具的序列化与反序列化
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] JDKObjectToBytes(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        objectOutput.writeObject(object);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T JDKBytesToObject(byte[] bytes, Class clazz) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object object = objectInputStream.readObject();
        return (T) object;
    }

}
