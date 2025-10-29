package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ProizvodiPage;
import pages.SunCareProductsPage;
import pages.SunCareSPF15Page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductsAndColectionsTest extends BaseTest{

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(15));
    ProizvodiPage proizvodiPage = new ProizvodiPage(driver, Duration.ofSeconds(15));
    SunCareProductsPage sunCareProizvodiPage = new SunCareProductsPage(driver, Duration.ofSeconds(15));
    SunCareSPF15Page sunCareSPF15 = new SunCareSPF15Page(driver, Duration.ofSeconds(15));



    @Test
    public void navigateToProductsAndCheckColectionsThree() {
        homePage.hoverLinkZaProizvode();
        List<String> uniqueNames = proizvodiPage.ispisiKolekcije();
        // Asertacija da ima tačno 10 jedinstvenih kolekcija
        Assert.assertTrue("Lista kolekcija nema tačno 10 proizvoda!", uniqueNames.size() == 10);
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
