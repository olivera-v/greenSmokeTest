package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.ProizvodiPage;
import pages.SunCareProductsPage;
import pages.SunCareSPF15Page;

import java.time.Duration;

public class ProductsAndColectionsTest extends BaseTest{

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(15));
    ProizvodiPage proizvodiPage = new ProizvodiPage(driver, Duration.ofSeconds(15));
    SunCareProductsPage sunCareProizvodiPage = new SunCareProductsPage(driver, Duration.ofSeconds(15));
    SunCareSPF15Page sunCareSPF15 = new SunCareSPF15Page(driver, Duration.ofSeconds(15));

    @Test
    public void navigateToProductsAndCheckColections () {
        homePage.setLinkZaProizvode();
        Assert.assertTrue(
                driver.findElement(proizvodiPage.getNegaTela()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getGreenSana()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getGreenSpa()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getGreenSunCare()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getGreenUnique()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getHigijenaDoma()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getLicnaHigijena()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getMiniGreeny()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getNegaLica()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getNegaKose()).isDisplayed() &&
                        driver.findElement(proizvodiPage.getOralnaHigijena()).isDisplayed());
    }

    @Test
    public void navigateToProductsAndCheckColectionsTwo() {
        homePage.setLinkZaProizvode();
        Assert.assertTrue(proizvodiPage.colectionPresenceCheck("nega kose"));
        Assert.assertTrue(proizvodiPage.colectionPresenceCheck("mini greeny"));

    }

    @Test
    public void bodyCareColectionLaunc() {
        homePage.setLinkZaProizvode();
        proizvodiPage.pregledProizvodaZaNeguTela();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("nega-tela"));
    }

    @Test
    public void hairCareColectionLaunc() {
        homePage.setLinkZaProizvode();
        proizvodiPage.pregledProizvodaZaNeguKose();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("nega-kose"));
    }

    @Test
    public void navigateThroughPagesToProduct() {
        homePage.setLinkZaProizvode();
        proizvodiPage.pregledProizvodaGreenSunCare();
        sunCareProizvodiPage.pregledProizvodaLosionPosleSuncanja();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Losion posle')]")).isDisplayed());
    }

    @Test
    public void displeyPricePictureDescriptionCheck() {
        homePage.setLinkZaProizvode();
        proizvodiPage.pregledProizvodaGreenSunCare();
        sunCareProizvodiPage.pregledProizvodaMlekoSPF15();
        String title = sunCareSPF15.getTitleText();
        String price = sunCareSPF15.getPriceValue();
        String description = sunCareSPF15.getDescriptionText();
        Assert.assertEquals("Mleko za sunčanje SPF 15", title);
        Assert.assertEquals("1.656,00 RSD", price);
        Assert.assertTrue(description.contains("Mleko u spreju"));

    }
}
