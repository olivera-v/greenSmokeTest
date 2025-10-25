package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SearchPage extends BasePage{

    private By searchField = By.xpath("(//*[@type=\"text\"])[2]");

    public SearchPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void pretragaPojma( String pojam){
        typeAndClick(searchField, pojam);
    }

    public void invalidSearch () {
        typeAndClick(searchField, String.valueOf(faker.name()));
    }
}
