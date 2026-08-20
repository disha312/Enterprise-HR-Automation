package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class NegativeLoginTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();

            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            //Scenario 1
            page.getByPlaceholder("Username").fill("WrongUser");
            page.getByPlaceholder("Password").fill("admin123");

            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();


            Locator errorMessage = page.getByText("Invalid credentials");

            errorMessage.waitFor();

            assert errorMessage.textContent().equals("Invalid credentials")
                    : "Expected 'Invalid credentials' message was not displayed";

            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            System.out.println("Scenario 1 passed");


            //Scenario 2
            page.getByPlaceholder("Username").fill("Admin");
            page.getByPlaceholder("Password").fill("wrongpassword");

            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();

            Locator errorMessage2 = page.getByText("Invalid credentials");
            errorMessage.waitFor();

            assert errorMessage2.textContent().equals("Invalid credentials")
                    : "Expected 'Invalid credentials' message was not displayed";

            System.out.println("Scenario 2 passed");



            //Scenario 3
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            page.getByPlaceholder("Username").fill("");
            page.getByPlaceholder("Password").fill("admin123");

            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();

            Locator usernameRequired = page.locator("div.oxd-input-group")
                    .filter(new Locator.FilterOptions().setHasText("Username"))
                    .getByText("Required");

            usernameRequired.waitFor();

            assert usernameRequired.isVisible()
                    : "Expected Username 'Required' message was not displayed";

            System.out.println("Scenario 3 passed");


            //Scenario 4
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            page.getByPlaceholder("Username").fill("Admin");
            page.getByPlaceholder("Password").fill("");

            page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")
            ).click();

            Locator passwordRequired = page.locator("div.oxd-input-group")
                    .filter(new Locator.FilterOptions().setHasText("Password"))
                    .getByText("Required");

            passwordRequired.waitFor();

            assert passwordRequired.isVisible()
                    : "Expected Password 'Required' message was not displayed";

            System.out.println("Scenario 4 passed");


            browser.close();
        }
    }
}
