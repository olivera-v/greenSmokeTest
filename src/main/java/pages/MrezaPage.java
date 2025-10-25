package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MrezaPage extends BasePage{

    public By title = By.xpath("//h2[normalize-space()='Green Business Solutions']/parent::div");
    public By inputField = By.id("email");


    public MrezaPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

}
