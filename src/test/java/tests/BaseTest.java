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
//    options.addArguments("--headless=new"); // koristi novi headless
//    options.addArguments("--disable-gpu"); // preporučeno
//    options.addArguments("--remote-allow-origins=*");
//
//    // Jedinstveni profil za svaki test (radi na Windows i Linux)
//    String userDataDir = System.getProperty("java.io.tmpdir") + "chrome-" + UUID.randomUUID();
//    options.addArguments("--user-data-dir=" + userDataDir);
    options.addArguments("--headless");
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


