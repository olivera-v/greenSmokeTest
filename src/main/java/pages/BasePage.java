package pages;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.*;
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
    public void scrollIntoView(By locator) {
        WebElement element = waitForVisible(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForPageToLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickElementUsingJS(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(locator)
        );
    }

    //ACTIONS - MOUSE
    public void hover2(By locator) {
        actions.moveToElement(waitForVisible(locator)).perform();
    }
    public void hover(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Sačekaj da element bude prisutan u DOM-u
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Scroll element u viewport
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        // Hover preko JS
        ((JavascriptExecutor) driver).executeScript(
                "var evObj = document.createEvent('MouseEvents');" +
                        "evObj.initMouseEvent('mouseover', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                        "arguments[0].dispatchEvent(evObj);", element
        );
    }


    public void moveToAndClick(By locator) {
        actions.moveToElement(waitForVisible(locator)).click().perform();
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }


public void openDropdownAndClick(By mainElementLocator, By dropdownOptionLocator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // 1️⃣ Sačekaj da se glavni element pojavi
    WebElement mainElement = wait.until(ExpectedConditions.presenceOfElementLocated(mainElementLocator));

    // 2️⃣ Hover preko JavaScript-a (simulira prelazak miša)
    js.executeScript(
            "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent('mouseover', true, true, window, 0, 0, 0, 0, 0," +
                    "false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);", mainElement);

    // 3️⃣ Sačekaj da dropdown opcija postoji u DOM-u
    WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownOptionLocator));

    // 4️⃣ Klik pomoću JavaScript-a (zaobilazi overlay / nevidljivost)
    js.executeScript("arguments[0].click();", dropdownOption);
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
