package tests;

import com.microsoft.playwright.*;
import pages.DashboardPage;
import pages.LoginPage;

public class POMLoginTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();

            try {
                page.navigate(
                        "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
                );

                LoginPage loginPage = new LoginPage(page);
                DashboardPage dashboardPage = new DashboardPage(page);

                loginPage.enterUsername("Admin");
                loginPage.enterPassword("admin123");
                loginPage.clickLogin();

                page.waitForURL("**/dashboard/index");

                page.screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(java.nio.file.Paths.get(
                                        "screenshots",
                                        "login-success.png"
                                ))
                                .setFullPage(true)
                );

                if (!dashboardPage.isDashboardDisplayed()) {
                    throw new RuntimeException(
                            "Dashboard was not displayed after login"
                    );
                }

                System.out.println(
                        "POM Login test passed - Dashboard displayed."
                );

            } catch (RuntimeException e) {

                page.screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(java.nio.file.Paths.get(
                                        "screenshots",
                                        "login-failure.png"
                                ))
                                .setFullPage(true)
                );

                throw e;
            }

            browser.close();
        }
    }
}