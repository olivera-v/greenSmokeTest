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
        try {
            Thread.sleep(500); // kratko čekanje da se meni otvori
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Svi <a> unutar li sa klasom product_cat
        List<WebElement> items = driver.findElements(By.cssSelector("li.menu-item-object-product_cat > a"));

        // Set za deduplikaciju URL-ova
        Set<String> seenHrefs = new HashSet<>();
        // Lista za imena kolekcija bez duplikata
        List<String> uniqueNames = new ArrayList<>();

        System.out.println("=== Spisak kolekcija ===");
        for (WebElement item : items) {
            String name = item.findElement(By.tagName("span")).getText();
            String href = item.getAttribute("href");

            if (href == null || href.isEmpty()) continue;

            // Preskoči duplikate
            if (seenHrefs.contains(href)) continue;
            seenHrefs.add(href);
            uniqueNames.add(name);

            // Izdvajanje dela između poslednje dve kose crte
            String lastPart = "";
            String[] parts = href.split("/");
            lastPart = parts[parts.length - 1].isEmpty() ? parts[parts.length - 2] : parts[parts.length - 1];

            System.out.println(name + " -> " + lastPart);
        }
        System.out.println("=== Kraj spiska ===");

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
