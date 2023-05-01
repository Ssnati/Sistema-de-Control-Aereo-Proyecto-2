package co.edu.uptc.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static PropertiesManager instance;
    private Properties properties;

    private PropertiesManager() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
