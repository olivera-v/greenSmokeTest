package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.MojGreenKutakPage;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class BasicFuncionalityTest extends BaseTest{

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(10));
    MojGreenKutakPage mojGreenKutak = new MojGreenKutakPage(driver, Duration.ofSeconds(10));

    @Test
    public void openingHomePage() {
        homePage.linkZaProizvodePresent();
        homePage.linkZaPretraguPresent();
        homePage.linkZaMojGreenKutakPresent();
        Assert.assertTrue(homePage.isElementPresent(homePage.getLinkZaMojGreenKutak()) &&
                homePage.isElementPresent(homePage.getLinkZaPretragu()) &&
                homePage.isElementPresent(homePage.getLinkZaProizvode()));

    }

    @Test
    public void navigationToAboutCompany() {
        homePage.setLinkZaOKompaniji();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1/span[text()='Vizija']")));
        Assert.assertTrue(driver.findElement(By.xpath("//h1/span[text()='Vizija']")).isDisplayed());
    }

    @Test
    public void navigationToAboutProducts() {
        homePage.setLinkZaOProizvodima();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://greenbsn.com/sr/green/o-green-proizvodima/"));
        Assert.assertTrue("Nije otvorena stranica O proizvodima.",
                driver.getCurrentUrl().endsWith("sr/green/o-green-proizvodima/")
        );
    }

    @Test
    public void navigationToCertificates() {
        homePage.setLinkZaSertifikate();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlToBe("https://greenbsn.com/sr/green/sertifikati/"));
        Assert.assertTrue("Nije otvorena stranica Sertifikati.",
                    driver.getCurrentUrl().endsWith("/sr/green/sertifikati/")
            );
        }



    @Test
    public void verifyHTTPSandSSL() throws Exception {
        String url = driver.getCurrentUrl();
        assertTrue("Stranica ne koristi HTTPS!", url.startsWith("https://"));

        HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
        conn.connect();

        Certificate[] certs = conn.getServerCertificates();
        assertTrue("Sertifikat nije prisutan!", certs.length > 0);

        conn.disconnect();
    }

    @Test
    public void unsuccessfulLogin(){
        driver.get("https://greenbsn.com/sr/");
        homePage.setLinkZaMojGreenKutak();
        homePage.switchToNewlyOpenedTab();
        mojGreenKutak.logovanje("nesto","nesto");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Prijava']")).isDisplayed());
        driver.switchTo().window(driver.getWindowHandles().stream().filter(h -> !h.equals(driver.getWindowHandle())).findFirst().orElse(driver.getWindowHandle())).close(); driver.switchTo().window(driver.getWindowHandles().iterator().next());

    }

    @Test
    public void successfulLogin(){
        driver.get("https://greenbsn.com/sr/");
        homePage.setLinkZaMojGreenKutak();
        homePage.switchToNewlyOpenedTab();
        mojGreenKutak.logovanje("1-0008826","olivera");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='listing page-orders' and h3[text()='Porudžbine']]")).isDisplayed());
        driver.switchTo().window(driver.getWindowHandles().stream().filter(h -> !h.equals(driver.getWindowHandle())).findFirst().orElse(driver.getWindowHandle())).close(); driver.switchTo().window(driver.getWindowHandles().iterator().next());

    }

}
