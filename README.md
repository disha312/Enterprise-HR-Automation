# Enterprise HR Automation

UI automation testing project for the OrangeHRM application using Java and Playwright.

## Tech Stack

- Java
- Maven
- Playwright
- IntelliJ IDEA
- Git & GitHub

## Current Test Coverage

### Login Smoke Test

- Open OrangeHRM
- Enter valid username
- Enter valid password
- Click Login
- Verify that the Dashboard is loaded

### Negative Login Testing

The following scenarios are covered:

1. Invalid username
2. Invalid password
3. Empty username
4. Empty password

Expected validation messages are verified using Playwright locators and assertions.

## Locator Strategies

The project practices:

- Placeholder locators
- Role-based locators
- Text locators
- CSS locators
- Scoped locators

Locator documentation is available in:

`docs/Locator-Strategy.md`

## Project Structure

```text
Enterprise-HR-Automation/
├── README.md
├── pom.xml
├── docs/
│   ├── Locator-Strategy.md
│   └── Negative-test.md
└── src/
    └── test/
        └── java/
            └── tests/
                ├── PlaywrightSmokeTest.java
                └── NegativeLoginTest.java