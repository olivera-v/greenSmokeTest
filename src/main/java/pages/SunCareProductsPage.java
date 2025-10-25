package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SunCareProductsPage extends BasePage{

    public By mlekoSPF15 = By.xpath("(//*[@href=\"https://greenbsn.com/sr/proizvod/mleko-za-suncanje-spf-15/\"])[2]");
    public By mlekoSPF30 = By.xpath("(//*[@href=\"https://greenbsn.com/sr/proizvod/mleko-za-suncanje-spf-30/\"])[2]");
    public By losionPosleSuncanja = By.xpath("(//*[@href=\"https://greenbsn.com/sr/proizvod/losion-posle-suncanja/\"])[2]");



    public SunCareProductsPage (WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void pregledProizvodaMlekoSPF15 (){
        driver.findElement(mlekoSPF15).click();
    }

    public void pregledProizvodaLosionPosleSuncanja (){
        driver.findElement(losionPosleSuncanja).click();
    }

}
