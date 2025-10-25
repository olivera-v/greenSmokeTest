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
        long startTime = System.currentTimeMillis();  // početak merenja vremena

        driver.get("https://greenbsn.com/sr/"); // otvaranje stranice

        // čekaj dok se stranica potpuno ne učita
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

        long endTime = System.currentTimeMillis(); // kraj merenja
        long loadTime = endTime - startTime;

        System.out.println("Vreme učitavanja stranice: " + loadTime + " ms");

        // Asertacija — očekujemo manje od 5000 ms (5 sekundi)
        Assert.assertTrue("Stranica se učitala presporo: " + loadTime + " ms", loadTime < 5000);
    }

    @Test
    public void checkingTheConsoleInTheBrovser() {
        basePage.verifyNoJavaScriptErrors();
    }
}
