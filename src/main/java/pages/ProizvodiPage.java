package pages;

import org.openqa.selenium.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProizvodiPage extends BasePage {

        private By negaTela = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/nega-tela/']//span[normalize-space()='Nega tela']");
        private By negaKose = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/nega-kose/']//span[normalize-space()='Nega kose']");
        private By greenSunCare = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/sun-care/']//span[normalize-space()='Green SUN CARE']");
        private By linkZaKontakt = By.xpath("//a[contains(@href,'/mreza')]//span[contains(text(),'Kontakt')]");


    public ProizvodiPage(WebDriver driver, Duration timeout) {
            super(driver, timeout);
        }

    public void pregledProizvodaZaNeguTela() {
            openDropdownAndClick(linkZaKontakt, negaTela);
        }

    public void pregledProizvodaZaNeguKose() {
        openDropdownAndClick(linkZaKontakt, negaKose);
        }

    public void pregledProizvodaGreenSunCare() {
            openDropdownAndClick(linkZaKontakt, greenSunCare);
        }

    public List<String> ispisiKolekcije() {
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
        return uniqueNames;
    }


}