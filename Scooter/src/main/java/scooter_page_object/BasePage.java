package scooter_page_object;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected static WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
