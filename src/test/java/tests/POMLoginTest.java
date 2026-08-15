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

            page.navigate(
                    "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
            );

            LoginPage loginPage = new LoginPage(page);
            DashboardPage dashboardPage = new DashboardPage(page);

            loginPage.enterUsername("Admin");
            loginPage.enterPassword("admin123");
            loginPage.clickLogin();

            assert dashboardPage.isDashboardDisplayed()
                    : "Dashboard was not displayed after login";

            System.out.println("POM Login test passed - Dashboard displayed.");

            // We'll add the login flow next.

            browser.close();
        }
    }
}
