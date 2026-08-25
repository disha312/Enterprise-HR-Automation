package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDataProviderTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"Admin", "admin123"},
                {"WrongUser", "admin123"},
                {"Admin", "wrongpassword"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {

        System.out.println(
                "Username: " + username +
                        " | Password: " + password
        );
    }
}