package pages;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    Faker faker = new Faker();

    //KONSTRUKTOR
    public BasePage (WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    //NAVIGATION
    public void navigateTo(String url) {
        driver.get(url);
        waitForPageToLoad();
    }

    //WAITING
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForPageToLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    //ACTIONS - MOUSE
    public void hover(By locator) {
        actions.moveToElement(waitForVisible(locator)).perform();
    }

    public void moveToAndClick(By locator) {
        actions.moveToElement(waitForVisible(locator)).click().perform();
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void openDropdownAndClick(By mainElementLocator, By dropdownOptionLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        // 1️⃣ Pređi mišem preko glavnog elementa
        WebElement mainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainElementLocator));
        actions.moveToElement(mainElement).perform();

        // 2️⃣ Sačekaj da se pojavi padajući meni
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptionLocator));

        // 3️⃣ Klikni na traženu opciju iz menija
        dropdownOption.click();
    }

    //ACTIONS - KEYBOARD
    public void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    public void typeAndClick(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
        el.submit();
    }

    //FRAMES
    public void switchToNewlyOpenedTab () {
        String originalTab = driver.getWindowHandle();

        // Čekamo dok se ne pojavi više od 1 taba
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        // Prebacujemo se na tab koji nije originalni
        for (String tabHandle : driver.getWindowHandles()) {
            if (!tabHandle.equals(originalTab)) {
                driver.switchTo().window(tabHandle);
                break;
            }
        }
    }

    //CHECK
    public boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    public void verifyNoJavaScriptErrors() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        boolean hasErrors = logs.getAll().stream()
                .anyMatch(log -> log.getLevel().equals(Level.SEVERE));

        // Ispiši u konzolu sve JS logove (korisno za debug)
        logs.forEach(System.out::println);

        Assert.assertEquals("⚠️ JavaScript greške pronađene na stranici!", false, hasErrors);
    }
}
