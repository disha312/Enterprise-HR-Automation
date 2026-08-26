package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import utils.BrowserManager;

public class BrowserManagerTest {

    @Test
    public void verifyBrowserLaunch() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = BrowserManager.launchBrowser(playwright);

            System.out.println(
                    "Browser launched successfully: "
                            + browser.browserType().name()
            );

            browser.close();
        }
    }
}
