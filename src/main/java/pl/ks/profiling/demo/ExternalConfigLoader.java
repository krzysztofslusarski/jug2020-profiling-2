package pl.ks.profiling.demo;

import pl.ks.profiling.demo.stat.StatController;

import java.io.InputStream;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class ExternalConfigLoader {
    static Properties loadFromClassPath() {
        Properties properties = new Properties();
        try (InputStream resource = new GZIPInputStream(StatController.class.getClassLoader().getResourceAsStream("demo.properties.gz"))) {
            properties.load(resource);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read config from classpath resource", e);
        }
        return properties;
    }
}
