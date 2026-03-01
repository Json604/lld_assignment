package com.example.metrics;

import java.io.*;

// serialize the singleton to bytes and deserialize it back
// the deserialized object must be the exact same instance (==)
public class SerializationCheck {

    public static void main(String[] args) throws Exception {
        MetricsRegistry before = MetricsRegistry.getInstance();
        before.setCount("LOGIN_COUNT", 99);

        byte[] data = toBytes(before);
        MetricsRegistry after = fromBytes(data);

        System.out.println("Before hash : " + System.identityHashCode(before));
        System.out.println("After hash  : " + System.identityHashCode(after));
        System.out.println("Same object? " + (before == after));
        System.out.println("LOGIN_COUNT from deserialized = " + after.getCount("LOGIN_COUNT"));
    }

    private static byte[] toBytes(MetricsRegistry obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
        }
        return bos.toByteArray();
    }

    private static MetricsRegistry fromBytes(byte[] data) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (MetricsRegistry) in.readObject();
        }
    }
}
