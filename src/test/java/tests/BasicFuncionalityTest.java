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
        homePage.waitForVisible(By.xpath("//h1/span[text()='Vizija']"));
        Assert.assertTrue(driver.findElement(By.xpath("//h1/span[text()='Vizija']")).isDisplayed());
    }

    @Test
    public void navigationToAboutProducts() {
        homePage.setLinkZaOProizvodima();
        basePage.waitForURL("https://greenbsn.com/sr/green/o-green-proizvodima/");
        Assert.assertTrue("The About Products page is not open.",
                driver.getCurrentUrl().endsWith("sr/green/o-green-proizvodima/")
        );
    }

    @Test
    public void navigationToCertificates() {
        homePage.setLinkZaSertifikate();
        basePage.waitForURL("https://greenbsn.com/sr/green/sertifikati/");
        Assert.assertTrue("The Certificates page is not open.",
                    driver.getCurrentUrl().endsWith("/sr/green/sertifikati/")
            );
        }



    @Test
    public void verifyHTTPSandSSL() throws Exception {
        String url = driver.getCurrentUrl();
        assertTrue("The site does not use HTTPS!", url.startsWith("https://"));

        HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
        conn.connect();

        Certificate[] certs = conn.getServerCertificates();
        assertTrue("Certificate not present!", certs.length > 0);

        conn.disconnect();
    }

    @Test
    public void unsuccessfulLogin() {
        homePage.setLinkZaMojGreenKutak();
        homePage.switchToNewlyOpenedTab();
        homePage.waitingForTwoTabsToOpenAndSwitchToTheOtherOne();
        homePage.checkingIfThePageIsOpen("/login.php");
        mojGreenKutak.logovanje("nesto", "nesto");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertTrue(usernameField.isDisplayed());
//        Assert.assertTrue(driver.findElement2(By.xpath("//*[@id=\"username\"]")).isDisplayed());
            driver.switchTo().window(driver.getWindowHandles().stream().filter(h -> !h.equals(driver.getWindowHandle())).findFirst().orElse(driver.getWindowHandle())).close(); driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }


//    @Test
//    public void successfulLogin() throws InterruptedException {
//        homePage.setLinkZaMojGreenKutak();
//        homePage.switchToNewlyOpenedTab();
//        homePage.switchToNewlyOpenedTab();
//        homePage.waitingForTwoTabsToOpenAndSwitchToTheOtherOne();
//        homePage.checkingIfThePageIsOpen("/login.php");
//        mojGreenKutak.logovanje("??????","?????"); // valid credentials should be entered
//        mojGreenKutak.waitForPageToLoad();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlToBe("https://my.greenbsn.com/myOrders.php"));
//        Assert.assertEquals("Nismo na očekivanoj login stranici!",
//                "https://my.greenbsn.com/myOrders.php",
//                driver.getCurrentUrl());
//        driver.switchTo().window(driver.getWindowHandles().stream().filter(h -> !h.equals(driver.getWindowHandle())).findFirst().orElse(driver.getWindowHandle())).close(); driver.switchTo().window(driver.getWindowHandles().iterator().next());
//    }

}
