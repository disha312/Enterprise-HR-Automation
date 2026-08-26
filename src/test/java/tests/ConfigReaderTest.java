package tests;

import org.testng.annotations.Test;
import utils.ConfigReader;

public class ConfigReaderTest {

    @Test
    public void verifyConfiguration() {

        System.out.println("URL: " + ConfigReader.get("url"));
        System.out.println("Browser: " + ConfigReader.get("browser"));
        System.out.println("Username: " + ConfigReader.get("username"));
        System.out.println("Password: " + ConfigReader.get("password"));
        System.out.println("Headless: " + ConfigReader.get("headless"));
    }
}