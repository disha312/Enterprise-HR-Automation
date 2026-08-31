package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserManager {

    private static final Logger logger =
            LoggerFactory.getLogger(BrowserManager.class);

    public static Browser launchBrowser(Playwright playwright) {

        String browserName = ConfigReader.get("browser");

        logger.info("Launching browser: {}", browserName);

        switch (browserName.toLowerCase()) {

            case "chromium":
                Browser chromium = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );
                logger.info("Chromium browser launched successfully");
                return chromium;

            case "firefox":
                Browser firefox = playwright.firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );
                logger.info("Firefox browser launched successfully");
                return firefox;

            case "webkit":
                Browser webkit = playwright.webkit().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                );
                logger.info("WebKit browser launched successfully");
                return webkit;

            default:
                logger.error("Unsupported browser: {}", browserName);
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                );
        }
    }
}