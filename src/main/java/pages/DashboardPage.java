package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage extends BasePage {

    private final Locator dashboardHeading;

    public DashboardPage(Page page) {
        super(page);

        //dashboardHeading = page.getByText("Dashboard");
        dashboardHeading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Dashboard")
        );
    }

    public boolean isDashboardDisplayed() {
        dashboardHeading.waitFor();
        return dashboardHeading.isVisible();
    }
}