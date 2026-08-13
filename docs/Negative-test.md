NEGATIVE LOGIN — INVALID USERNAME

Input:
Username → getByPlaceholder("Username")
Password → getByPlaceholder("Password")
Login    → getByRole(BUTTON, name="Login")

Expected error:
"Invalid credentials"

Locator:
getByText("Invalid credentials")


Invalid username
→ getByText("Invalid credentials")

Invalid password
→ getByText("Invalid credentials")

Empty username
→ Username input-group → getByText("Required")

Empty password
→ Password input-group → getByText("Required")

page.locator("div.oxd-input-group")
.filter(new Locator.FilterOptions().setHasText("Username"))
.getByText("Required")

