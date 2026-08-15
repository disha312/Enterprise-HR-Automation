package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class DashboardPage {

    private final Page page;
    private final Locator dashboardHeading;

    public DashboardPage(Page page) {
        this.page = page;

        dashboardHeading = page.getByText("Dashboard");
    }
    public boolean isDashboardDisplayed() {
        return dashboardHeading.isVisible();
    }
}