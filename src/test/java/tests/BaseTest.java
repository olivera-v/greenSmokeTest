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

public class BaseTest {


    Faker faker = new Faker();



    protected static WebDriver driver;
    protected BasePage basePage = new BasePage(driver, Duration.ofSeconds(15));


    @BeforeClass
    public static void beforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // ili samo --headless za starije verzije
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=/tmp/chrome-${UUID.randomUUID()}"); // svaki put novi profil

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
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


