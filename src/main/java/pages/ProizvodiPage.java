package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProizvodiPage extends BasePage {

        private By negaTela = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/nega-tela/']//span[normalize-space()='Nega tela']");
        private By negaTelaTwo = By.xpath("//li[contains(@class,'product-category')]/a[@aria-label='Posetite kategoriju proizvoda Nega tela']/img");
        private By greenSpa = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green SPA\")]/img");
        private By greenUnique = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green UNIQUE\")]/img");
        private By negaLica = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Nega lica\")]/img");
        private By negaKose = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/nega-kose/']//span[normalize-space()='Nega kose']");
        private By oralnaHigijena = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda [:sr]Oralna higijena [:en]Oral Hygiene [:]\")]/img");
        private By licnaHigijena = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Lična higijena\")]/img");
        private By miniGreeny = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda mini Greeny\")]/img");
        private By greenSunCare = By.xpath("//a[@href='https://greenbsn.com/sr/kolekcija/sun-care/']//span[normalize-space()='Green SUN CARE']");
        private By higijenaDoma = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda [:sr]Higijena doma[:en]Household Care[:]\")]/img");
        private By greenSana = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green SANA+\")]/img");
    private By kolekcije = By.cssSelector("a[aria-label^='Posetite kategoriju proizvoda']");
    private By linkZaKontakt = By.xpath("//a[contains(@href,'/mreza')]//span[contains(text(),'Kontakt')]");


    public ProizvodiPage(WebDriver driver, Duration timeout) {
            super(driver, timeout);
        }

        public By getNegaTela() {
            return negaTelaTwo;
        }

        public By getNegaKose() {
            return negaKose;
        }

        public By getNegaLica() {
            return negaLica;
        }

        public By getHigijenaDoma() {
            return higijenaDoma;
        }

        public By getGreenSunCare() {
            return greenSunCare;
        }

        public By getMiniGreeny() {
            return miniGreeny;
        }

        public By getOralnaHigijena() {
            return oralnaHigijena;
        }

        public By getLicnaHigijena() {
            return licnaHigijena;
        }

        public By getGreenUnique() {
            return greenUnique;
        }

        public By getGreenSpa() {
            return greenSpa;
        }

        public By getGreenSana() {
            return greenSana;
        }

        public void pregledProizvodaZaNeguTela() {
            openDropdownAndClick(linkZaKontakt, negaTela);
        }

    public boolean colectionPresenceCheck(String name) {
        int retries = 2;

        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

                // Sačekaj da svi elementi budu prisutni u DOM-u
                List<WebElement> allColections = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(kolekcije)
                );

                // Malo čekanje da se tekst prikaže (važno u headless modu)
                Thread.sleep(500);

                System.out.println("=== DEBUG: Lista svih kolekcija ===");
                for (WebElement k : allColections) {
                    String text = k.getText().trim();
                    System.out.println("👉 '" + text + "'");

                    if (!text.isEmpty() && text.toLowerCase().contains(name.toLowerCase())) {
                        System.out.println("✅ Kolekcija pronađena: " + text);
                        return true;
                    }
                }
                System.out.println("=== Kraj liste ===");

                return false;
            } catch (StaleElementReferenceException | TimeoutException e) {
                System.out.println("⚠️ Pokušaj ponovo... (preostalo pokušaja: " + (retries - 1) + ")");
                retries--;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return false;
    }






    public void pregledProizvodaZaNeguKose() {
        openDropdownAndClick(linkZaKontakt, negaKose);
        }

    public void pregledProizvodaGreenSunCare() {
            openDropdownAndClick(linkZaKontakt, greenSunCare);
        }


}