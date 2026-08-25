1. Playwright Auto-Waiting

Playwright automatically waits for elements to be ready before performing actions such as:

page.getByPlaceholder("Username").fill("Admin");

and:

loginButton.click();

So we generally do not need Thread.sleep().

Avoid:

Thread.sleep(3000);

because it adds an arbitrary delay instead of waiting for the actual condition.

2. Explicit Waiting

Use explicit waiting when we specifically need to wait for an element or condition.

Example:

errorMessage.waitFor();

We used this for the dynamically displayed "Invalid credentials" message.

Explicit waits should be used where appropriate, not everywhere.

3. Assertions

An assertion verifies that the expected result actually happened.

Example:

assert dashboardPage.isDashboardDisplayed()
        : "Dashboard was not displayed after login";

If the condition is true, execution continues.

If it is false, the assertion fails with the supplied message.

We also strengthened our negative-login assertions to verify the actual error text:

assert errorMessage.textContent().equals("Invalid credentials")
        : "Expected 'Invalid credentials' message was not displayed";
4. What We Improved
POMLoginTest

After clicking Login, we added:

page.waitForURL("**/dashboard/index");

Then we verify the Dashboard:

assert dashboardPage.isDashboardDisplayed()
        : "Dashboard was not displayed after login";

So the flow is:

Click Login
     ↓
Wait for Dashboard URL
     ↓
Verify Dashboard
     ↓
PASS
NegativeLoginTest

We verified all four scenarios:

Invalid username → Invalid credentials ✅
Invalid password → Invalid credentials ✅
Empty username   → Required            ✅
Empty password   → Required            ✅

All four passed.

