package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserManager {

    public static Browser launchBrowser(Playwright playwright) {

        String browserName = ConfigReader.get("browser");

        switch (browserName.toLowerCase()) {

            case "chromium":
                return playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );

            case "firefox":
                return playwright.firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );

            case "webkit":
                return playwright.webkit().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                );
        }
    }
}