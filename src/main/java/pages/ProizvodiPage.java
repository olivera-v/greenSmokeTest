package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ProizvodiPage extends BasePage {

        private By negaTela = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Nega tela\")]/img");
        private By greenSpa = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green SPA\")]/img");
        private By greenUnique = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green UNIQUE\")]/img");
        private By negaLica = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Nega lica\")]/img");
        private By negaKose = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda [:en]Hair Care[:sr]Nega kose[:]\")]/img");
        private By oralnaHigijena = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda [:sr]Oralna higijena [:en]Oral Hygiene [:]\")]/img");
        private By licnaHigijena = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Lična higijena\")]/img");
        private By miniGreeny = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda mini Greeny\")]/img");
        private By greenSunCare = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green SUN CARE\")]/img");
        private By higijenaDoma = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda [:sr]Higijena doma[:en]Household Care[:]\")]/img");
        private By greenSana = By.xpath("//a[contains(@aria-label, \"Posetite kategoriju proizvoda Green SANA+\")]/img");
        private By kolekcije = By.cssSelector(".product-category");

        public ProizvodiPage(WebDriver driver, Duration timeout) {
            super(driver, timeout);
        }

        public By getNegaTela() {
            return negaTela;
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
            moveToAndClick(negaTela);
        }

        public boolean colectionPresenceCheck
                (String name) {
            List<WebElement> allColections = driver.findElements(kolekcije);
            for (WebElement k : allColections) {
                if (k.getText().toLowerCase().contains(name.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }

        public void pregledProizvodaZaNeguKose() {
            moveToAndClick(negaKose);
        }

        public void pregledProizvodaGreenSunCare() {
            moveToAndClick(greenSunCare);
        }


}