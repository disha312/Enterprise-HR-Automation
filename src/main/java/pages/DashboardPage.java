package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;
    private final Locator dashboardHeading;

    public DashboardPage(Page page) {
        this.page = page;

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