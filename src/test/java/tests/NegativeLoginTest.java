package tests;

import pages.LoginPage;
import base.BaseTest;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NegativeLoginTest extends BaseTest {

    private static final Logger logger =
            LoggerFactory.getLogger(NegativeLoginTest.class);

    @Test(groups = {"regression", "login"})
    public void negativeLoginScenarios() {

        logger.info("Starting negative login test scenarios");

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        LoginPage loginPage = new LoginPage(page);

        //Scenario 1
        loginPage.enterUsername("WrongUser");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Locator errorMessage = page.getByText("Invalid credentials");

        errorMessage.waitFor();

        Assert.assertEquals(
                errorMessage.textContent(),
                "Invalid credentials",
                "Expected 'Invalid credentials' message was not displayed"
        );
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        logger.info("Starting Scenario 1: Invalid username");
        logger.info("Scenario 1 passed");


        //Scenario 2
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();

        Locator errorMessage2 = page.getByText("Invalid credentials");
        errorMessage2.waitFor();


        Assert.assertEquals(
                errorMessage2.textContent(),
                "Invalid credentials",
                "Expected 'Invalid credentials' message was not displayed"
        );

        logger.info("Starting Scenario 2: Invalid password");
        logger.info("Scenario 2 passed");


        //Scenario 3
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.enterUsername("");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Locator usernameRequired = page.locator("div.oxd-input-group")
                .filter(new Locator.FilterOptions().setHasText("Username"))
                .getByText("Required");

        usernameRequired.waitFor();

        Assert.assertTrue(
                usernameRequired.isVisible(),
                "Expected Username 'Required' message was not displayed"
        );

        logger.info("Starting Scenario 3: Empty username");
        logger.info("Scenario 3 passed");

        //Scenario 4
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Locator passwordRequired = page.locator("div.oxd-input-group")
                .filter(new Locator.FilterOptions().setHasText("Password"))
                .getByText("Required");

        passwordRequired.waitFor();

        Assert.assertTrue(
                passwordRequired.isVisible(),
                "Expected Password 'Required' message was not displayed"
        );

        logger.info("Starting Scenario 4: Empty password");
        logger.info("Scenario 4 passed");


        logger.info("Negative login test scenarios completed");

    }
}

