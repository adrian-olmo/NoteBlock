package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class ConfigManager {
    private final Properties properties;

    public ConfigManager(String propertiesFilePath) {
        properties = new Properties();
        try ( InputStream input = new FileInputStream(propertiesFilePath)) {
            properties.load(input);
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}