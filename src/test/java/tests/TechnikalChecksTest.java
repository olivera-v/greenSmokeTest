package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class TechnikalChecksTest extends BaseTest{

    BasePage basePage = new BasePage(driver, Duration.ofSeconds(15));

    @Test
    public void testLoadTime() {
        long startTime = System.currentTimeMillis();
        driver.get("https://greenbsn.com/sr/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;
        System.out.println("Page load time: " + loadTime + " ms");
        Assert.assertTrue("The page loaded too slowly.: " + loadTime + " ms", loadTime < 5000);
    }

    @Test
    public void checkingTheConsoleInTheBrovser() {
        basePage.verifyNoJavaScriptErrors();
    }
}
