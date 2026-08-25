package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionTest {

    @Test
    public void softAssertionExample() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(true, "First check failed");

        softAssert.assertEquals("Dashboard", "Dashboard",
                "Second check failed");

        softAssert.assertTrue(true, "Third check failed");

        System.out.println("All checks were executed.");

        softAssert.assertAll();
    }
}
