package tests;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BasePage;

import java.time.Duration;
import java.util.UUID;


public class BaseTest {

protected static WebDriver driver;
protected static BasePage basePage;
Faker faker = new Faker();

@BeforeClass
public static void beforeAll() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920,1080");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

    basePage = new BasePage(driver, Duration.ofSeconds(15));
}

@Before
public void beforeEach() {
    basePage.navigateTo("https://greenbsn.com/sr/");
}

@After
public void afterEach() {
    driver.manage().deleteAllCookies();
}

@AfterClass
public static void afterAll() {
    if (driver != null) {
        driver.quit();
    }
}
}


