package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MrezaPage extends BasePage{

    public By inputField = By.id("email");


    public MrezaPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

}
