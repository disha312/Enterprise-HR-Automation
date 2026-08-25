Day 15 — TestNG Basics

TestNG is a testing framework used to organize and execute Java tests.

Today I learned the basic TestNG annotations and their execution order.

1. @Test

Marks a method as a test method.

Example:

@Test
public void firstTest() {
System.out.println("Test is running");
}


2. @BeforeMethod

Runs before every @Test method.

Useful for setup that needs to happen before each test.


3. @AfterMethod

Runs after every @Test method.

Useful for cleanup that needs to happen after each test.


4. @BeforeClass

Runs once before the test methods in a class.

Useful for setup that only needs to happen once for the class.


5. @AfterClass

Runs once after all test methods in the class.

Useful for cleanup that only needs to happen once after the class.


Execution Order

@BeforeClass

    ↓

@BeforeMethod
↓
@Test
↓
@AfterMethod

    ↓

@BeforeMethod
↓
@Test
↓
@AfterMethod

    ↓

@AfterClass


Practical Exercise

Created TestNGBasicsTest to demonstrate the TestNG lifecycle.

The test contained:

- @BeforeClass
- @BeforeMethod
- @Test
- @AfterMethod
- @AfterClass

Two @Test methods were executed successfully.

Result:

Total tests run: 2
Passes: 2
Failures: 0
Skips: 0


Key Learning

@BeforeClass and @AfterClass run once per test class.

@BeforeMethod and @AfterMethod run around every test method.

@Test identifies the actual test methods.

TestNG is now configured in the project using Maven.

TestNG version: 7.12.0