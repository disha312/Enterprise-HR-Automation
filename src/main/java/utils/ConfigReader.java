package utils;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigReader {

    private static final Properties properties = new Properties();

    private static final Logger logger =
            LoggerFactory.getLogger(ConfigReader.class);

    static {
        String environment = System.getProperty("env", "test");

        String configFile = "config-" + environment + ".properties";

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(configFile)) {

            if (input == null) {
                logger.error("Configuration file not found: {}", configFile);
                throw new RuntimeException(configFile + " not found");
            }

            properties.load(input);

            logger.info("Loaded configuration: {}", configFile);

        } catch (Exception e) {
            logger.error("Failed to load configuration: {}", configFile, e);
            throw new RuntimeException("Failed to load " + configFile, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}