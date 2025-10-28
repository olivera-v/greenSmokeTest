package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class HomePage extends BasePage{

    private By linkZaMojGreenKutak = By.xpath("//*[text()=\"Moj Green kutak\"]");
    private By linkZaPretragu= By.xpath("(//*[@class=\"qode_icon_font_elegant icon_search \"])[1]");
    private By linkZaBurgerMeni = By.xpath("//*[@class=\"popup_menu normal\"]");
    private By linkZaGreen = By.cssSelector("a[href='https://greenbsn.com/sr/']");;
    private By linkZaOKompaniji = By.xpath("//a[@href='https://greenbsn.com/sr/green/vizija/']/span[text()='O kompaniji']");
    private By linkZaOProizvodima = By.xpath("//a[.//span[normalize-space()='O Green proizvodima']]");
    private By linkZaSertifikate = By.cssSelector("a[href='https://greenbsn.com/sr/green/sertifikati/']");
    private By linkZaNovosti = By.id("nav-menu-item-2839");
    private By linkZaProizvode = By.id("nav-menu-item-892");
    private By linkZaDogadjajiAkcije = By.id("nav-menu-item-1343");
    private By linkZaCene = By.id("nav-menu-item-331");
    private By linkZaKontakt = By.xpath("//a[contains(@href,'/mreza')]//span[contains(text(),'Kontakt')]");

    public HomePage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public void setLinkZaMojGreenKutak (){
        waitForVisible(linkZaMojGreenKutak).click();
    }

    public By getLinkZaMojGreenKutak() {
        return linkZaMojGreenKutak;
    }

    public By getLinkZaPretragu() {
        return linkZaPretragu;
    }

    public By getLinkZaProizvode() {
        return linkZaProizvode;
    }

    public void linkZaMojGreenKutakPresent () {
        if (isElementPresent(linkZaMojGreenKutak)) {

        } else {
            System.out.println("Link za Moj Green Kutak NIJE pronađen!");
        }
    }

    public void linkZaPretraguPresent () {
        if (isElementPresent(linkZaPretragu)) {

        } else {
            System.out.println("Link za pretragu NIJE pronađen!");
        }
    }

    public void linkZaProizvodePresent () {
        if (isElementPresent(linkZaProizvode)) {

        } else {
            System.out.println("Link ka proizvodima NIJE pronađen!");
        }
    }

    public void setLinkZaPretragu (){
        waitForVisible(linkZaPretragu).click();
    }

    public void setLinkZaProizvode(){
        clickElementUsingJS(linkZaProizvode);
    }

    public void setLinkZaKontakt (){
        clickElementUsingJS(linkZaKontakt);    }

    public void hoverLinkZaKontakt () {
        hover(linkZaKontakt);
    }

    public void hoverLinkZaProizvode () {hover(linkZaProizvode); }

    public void selectNSFromDropdownMenu () {
        openDropdownAndClick(linkZaKontakt, By.xpath("//span[text()='Diskont Novi Sad']"));
    }

    public void setLinkZaOKompaniji () {
        openDropdownAndClick(linkZaGreen, linkZaOKompaniji);
    }

    public void setLinkZaOProizvodima () {
        openDropdownAndClick(linkZaGreen, linkZaOProizvodima);
    }

    public void setLinkZaSertifikate () {
        openDropdownAndClick(linkZaGreen, linkZaSertifikate);
    }

}
