package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class PlaywrightSmokeTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
                            .setSlowMo(500)
            );

            Page page = browser.newPage();

            // Open OrangeHRM
            page.navigate("https://opensource-demo.orangehrmlive.com/");

            // Username
            page.getByPlaceholder("Username").fill("Admin");

            // Password
            page.getByPlaceholder("Password").fill("admin123");

            // Login button
            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();

            // Verify successful login
            String currentUrl = page.url();

            System.out.println("Current URL: " + currentUrl);

            if (currentUrl.contains("/dashboard/index")) {
                System.out.println("Login successful - Dashboard loaded.");
            } else {
                System.out.println("Login failed.");
            }

            System.out.println("Login button clicked.");
            System.out.println("Page title: " + page.title());

            browser.close();
        }
    }
}