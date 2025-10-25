package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SunCareSPF15Page extends BasePage{

    public By title = By.xpath("//h1[@class='product_title entry-title']");
    public By price = By.cssSelector("bdi");
    public By description = By.xpath("//p[contains(text(),'Mleko u spreju za srednju zaštitu')]");

    public SunCareSPF15Page(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public String getTitleText() {
        WebElement element = driver.findElement(title);
        return element.getText().trim();
    }

    public String getPriceValue() {
        WebElement element = driver.findElement(price);
        return element.getText().trim();
    }

    public String getDescriptionText() {
        WebElement element = driver.findElement(description);
        return element.getText().trim();
    }

}
