package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.MrezaNSPage;
import pages.MrezaPage;

import java.time.Duration;

public class ContactTest extends BaseTest{

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(15));
    MrezaPage mrezaPage = new MrezaPage(driver, Duration.ofSeconds(15));
    MrezaNSPage mrezaNSPage = new MrezaNSPage(driver, Duration.ofSeconds(15));


    @Test
    public void openingContactPage() throws InterruptedException {
        homePage.setLinkZaKontakt();
        mrezaPage.scrollToBottom();
        WebElement emailInput = mrezaPage.waitForVisible(mrezaPage.inputField); // By locator za email input
        Assert.assertTrue(emailInput.isDisplayed());
    }

    @Test
    public void dataDisplayNS() {
        homePage.hoverLinkZaKontakt();
        homePage.selectNSFromDropdownMenu();
        Assert.assertTrue(mrezaNSPage.waitForVisible(mrezaNSPage.address).isDisplayed());
        Assert.assertTrue(mrezaNSPage.waitForVisible(mrezaNSPage.landlinePhone).isDisplayed());
        Assert.assertTrue(mrezaNSPage.waitForVisible(mrezaNSPage.facebook).isDisplayed());
    }

    @Test
    public void checkingTheFunctionalityOfLinks() {
        homePage.hoverLinkZaKontakt();
        homePage.selectNSFromDropdownMenu();
        Assert.assertTrue("Facebook link nije ispravan!", mrezaNSPage.verifyFacebookLink());
        Assert.assertTrue("Email link nije ispravan!", mrezaNSPage.verifyEmailLink());
    }
}