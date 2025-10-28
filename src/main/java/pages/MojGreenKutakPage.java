package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MojGreenKutakPage extends BasePage{

    public By usernameField = By.xpath("//*[@id=\"username\"]");
    public By passwordField = By.xpath("//*[@id=\"password\"]");
    public By prijavaButton = By.id("login-submit");
    public By languageChangeBtn = By.id("s2id_autogen1");
    public By englishLng = By.xpath("//span[text()='English']");

    public MojGreenKutakPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void logovanje(String korisnik, String lozinka) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(prijavaButton));

        driver.findElement(usernameField).sendKeys(korisnik);
        driver.findElement(passwordField).sendKeys(lozinka);
        driver.findElement(prijavaButton).click();
    }

    public void changeLanguageToEnglish() {
        driver.findElement(languageChangeBtn).click();
        moveToAndClick(englishLng);
    }
























}
