package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class MrezaNSPage extends BasePage {

    public By address = By.xpath("//h4[contains(text(), 'Romanijska 2')]");
    public By landlinePhone = By.xpath("(//a[contains(text(), '+381 21 ')])[1]");
    public By email = By.xpath("(//a[contains(text(), '@greenbsn.com')])[1]");
    public By facebook = By.xpath("(//a[contains(@href, 'facebook.com') or contains(@href, 'fb/greenbsn')])[1]");

    public MrezaNSPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public boolean verifyFacebookLink() {
        WebElement fb = waitForVisible(facebook);
        String href = fb.getAttribute("href");
        return href.contains("facebook.com");
    }

    public boolean verifyEmailLink() {
        String href = driver.findElement(email).getAttribute("href");
        return href.contains("mailto:");
    }

}