⭐ Interview takeaway
Which locator would you prefer in Playwright?"

Your answer shouldn't simply be:

"XPath."

A better answer is:

"I prefer stable, readable locators such as role, label, placeholder, or test IDs when they're available. I use CSS or XPath when those aren't suitable."


Day 4 — OrangeHRM Login Page Locator Strategy
1. Username field

Actual HTML:

<input
class="oxd-input oxd-input--active"
name="username"
placeholder="Username"
autofocus="">
CSS
page.locator("input[name='username']")
XPath
page.locator("//input[@name='username']")
Preferred locator
page.getByPlaceholder("Username")

Why preferred?
It is readable and directly describes the element's purpose.

2. Password field

Actual HTML:

<input
type="password"
name="password"
placeholder="Password">
CSS
page.locator("input[name='password']")
XPath
page.locator("//input[@name='password']")
Preferred locator
page.getByPlaceholder("Password")
3. Login button

Actual HTML:

<button
type="submit"
class="oxd-button oxd-button--medium oxd-button--main orangehrm-login-button">
Login
</button>
CSS
page.locator("button[type='submit']")
XPath
page.locator("//button[@type='submit']")
Text locator
page.getByText("Login")
Preferred role-based locator
page.getByRole(
AriaRole.BUTTON,
new Page.GetByRoleOptions().setName("Login")
)
Important concept

The HTML doesn't explicitly contain:

role="button"

But <button> has an implicit/semantic button role, so getByRole() can still identify it.

4. ID Locator

We specifically checked for an ID on the Username field.

There was no id attribute.

Therefore, we cannot use something like:

page.locator("#username")

This is an important real-world lesson:

Never invent a locator. Inspect the actual DOM and choose from the attributes that actually exist.

5. Our final login locator strategy

For the actual automation code, we chose the more readable Playwright locators:

// Username
page.getByPlaceholder("Username");

// Password
page.getByPlaceholder("Password");

// Login button
page.getByRole(
AriaRole.BUTTON,
new Page.GetByRoleOptions().setName("Login")
);

And we used them like this:

page.getByPlaceholder("Username").fill("Admin");

page.getByPlaceholder("Password").fill("admin123");

page.getByRole(
AriaRole.BUTTON,
new Page.GetByRoleOptions().setName("Login")
).click();
6. Login validation

After clicking Login, we checked the URL.

Expected dashboard URL contains:

/dashboard/index

Our learning check was:

String currentUrl = page.url();

if (currentUrl.contains("/dashboard/index")) {
System.out.println("Login successful - Dashboard loaded.");
} else {
System.out.println("Login failed.");
}

Our actual result was:

Current URL: https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index

Login successful - Dashboard loaded.
What we'll improve later

Once we introduce TestNG, we'll replace the if/else check with a proper test assertion:

Assert.assertTrue(
page.url().contains("/dashboard/index"),
"Login failed: Dashboard URL was not reached"
);
