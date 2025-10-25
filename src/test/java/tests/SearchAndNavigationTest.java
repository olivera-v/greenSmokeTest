package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.MojGreenKutakPage;
import pages.SearchPage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchAndNavigationTest extends BaseTest{

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(10));
    SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(10));
    MojGreenKutakPage mojGreenKutak = new MojGreenKutakPage(driver, Duration.ofSeconds(10));



    @Test
    public void usingTheProductSearchField() throws InterruptedException {
        homePage.setLinkZaPretragu();
        searchPage.pretragaPojma("losion");
        assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Losion')]")).isDisplayed());
    }

    @Test
    public void usingTheProductSearchFieldFail() throws InterruptedException {
        homePage.setLinkZaPretragu();
        searchPage.invalidSearch();
        System.out.println("Test treba da padne.");
        assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Losion')]")).isDisplayed());

    }

    @Test
    public void checkingTheLinksInTheMainManu() throws Exception {
        List<WebElement> linkovi = driver.findElements(By.tagName("a"));

        for (WebElement link : linkovi) {
            String href = link.getAttribute("href");
            if (href == null || href.isEmpty() || !href.startsWith("http")) continue;

            HttpURLConnection c = (HttpURLConnection) new URL(href).openConnection();
            c.setRequestMethod("HEAD");
            c.connect();
            int kod = c.getResponseCode();
            assertTrue("Neispravan link: " + href + " (kod: " + kod + ")", kod < 400);
        }
    }

    @Test
    public void loadingInEnglish() {
        homePage.setLinkZaMojGreenKutak();
        homePage.switchToNewlyOpenedTab();
        mojGreenKutak.changeLanguageToEnglish();
        assertTrue(driver.findElement(By.xpath("//a[text()='Forgot your password?']")).isDisplayed());
    }

}
