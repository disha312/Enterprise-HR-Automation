package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import java.nio.file.Paths;

public class BasePage {

    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }
    public void click(Locator locator) {
        locator.click();
    }
    public void fill(Locator locator, String text) {
        locator.fill(text);
    }
    public String getText(Locator locator) {
        return locator.textContent();
    }
    public void waitFor(Locator locator) {
        locator.waitFor();
    }
    public void takeScreenshot(String fileName) {
        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(fileName))
                        .setFullPage(true)
        );
    }
}