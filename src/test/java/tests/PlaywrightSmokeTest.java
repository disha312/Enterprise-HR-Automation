package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class PlaywrightSmokeTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();

            // Open OrangeHRM
            page.navigate("https://opensource-demo.orangehrmlive.com/");

            // Enter username
            page.getByPlaceholder("Username").fill("Admin");

            // Enter password
            page.getByPlaceholder("Password").fill("admin123");

            // Click Login
            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();

            // Verify successful login
            String currentUrl = page.url();

            System.out.println("Current URL: " + currentUrl);

            assert currentUrl.contains("/dashboard/index")
                    : "Login failed - Dashboard was not loaded";

            browser.close();
        }
    }
}