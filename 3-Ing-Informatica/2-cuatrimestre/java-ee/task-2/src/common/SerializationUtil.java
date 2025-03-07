package src.common;

import src.common.packages.CustomPackage;

import java.io.*;

public class SerializationUtil {
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(obj);

        return byteOut.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(byteIn);

        return in.readObject();
    }
}
