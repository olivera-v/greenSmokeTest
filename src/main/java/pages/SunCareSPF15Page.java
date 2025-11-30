package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SunCareSPF15Page extends BasePage{

    public By title = By.xpath("//h1[@class='product_title entry-title']");
    public By price = By.cssSelector("bdi");
    public By description = By.xpath("//p[contains(text(),'Mleko u spreju za srednju zaštitu')]");

    public SunCareSPF15Page(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public String getTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(title)
        );
        return element.getText().trim();
    }

    public String getPriceValue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(price)
        );
        return element.getText().trim();
    }

    public String getDescriptionText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(description)
        );
        return element.getText().trim();
    }

}
