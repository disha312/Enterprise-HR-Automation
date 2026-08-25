package tests;

import org.testng.annotations.*;

public class TestNGBasicsTest {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @Test
    public void firstTest() {
        System.out.println("Test 1 is running");
    }

    @Test
    public void secondTest() {
        System.out.println("Test 2 is running");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}