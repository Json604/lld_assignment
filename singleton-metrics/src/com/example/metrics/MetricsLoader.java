package com.example.metrics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// reads metrics.properties and puts the initial values into the singleton
public class MetricsLoader {

    public MetricsRegistry loadFromFile(String filePath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(filePath)) {
            props.load(in);
        }

        MetricsRegistry reg = MetricsRegistry.getInstance();

        for (String name : props.stringPropertyNames()) {
            String rawVal = props.getProperty(name, "0").trim();
            long parsed;
            try {
                parsed = Long.parseLong(rawVal);
            } catch (NumberFormatException ex) {
                parsed = 0L;
            }
            reg.setCount(name, parsed);
        }
        return reg;
    }
}
