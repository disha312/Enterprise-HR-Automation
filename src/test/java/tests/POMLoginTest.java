package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class POMLoginTest {

    @DataProvider(name = "loginData" , parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {"Admin", "admin123", "success"},
                {"WrongUser", "admin123", "failure"},
                {"Admin", "wrongpassword", "failure"}
        };
    }

    @Test( dataProvider = "loginData",
            groups = {"smoke", "regression", "login"})
    public void loginTest(String username, String password, String expectedResult) {

        System.out.println(
                "Running: " + username + " | Thread: " +
                        Thread.currentThread().getName()
        );

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

                loginPage.enterUsername(username);
                loginPage.enterPassword(password);
                loginPage.clickLogin();

                if (expectedResult.equals("success")) {

                    page.waitForURL("**/dashboard/index");

                    page.screenshot(
                            new Page.ScreenshotOptions()
                                    .setPath(java.nio.file.Paths.get(
                                            "screenshots",
                                            "login-success.png"
                                    ))
                                    .setFullPage(true)
                    );

                    Assert.assertTrue(
                            dashboardPage.isDashboardDisplayed(),
                            "Dashboard was not displayed after login"
                    );

                    System.out.println(
                            "POM Login test passed - Dashboard displayed."
                    );

                } else {

                    Assert.assertTrue(
                            loginPage.isInvalidCredentialsDisplayed(),
                            "Invalid credentials message was not displayed"
                    );

                    System.out.println(
                            "Negative login test passed for: " + username
                    );
                }

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