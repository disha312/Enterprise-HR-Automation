package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class LoginPage {
       private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;

       public LoginPage(Page page) {
           this.page = page;

           usernameInput =
                   page.getByPlaceholder("Username");

           passwordInput =
                   page.getByPlaceholder("Password");

           loginButton =
                   page.getByRole(
                           AriaRole.BUTTON,
                           new Page.GetByRoleOptions().setName("Login")
                   );

       }
    // Action
    public void enterUsername(String username) {
        usernameInput.fill(username);
    }
    public void enterPassword(String password) {
        passwordInput.fill(password);
    }
    public void clickLogin() {
        loginButton.click();
    }
   }
