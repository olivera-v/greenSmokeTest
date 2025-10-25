package tests;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    Faker faker = new Faker();

    BasePage basePage = new BasePage(driver, Duration.ofSeconds(15));

    @BeforeClass
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//        max time to wait for a page load
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
//        max time for asynchronous JS run
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
        if(driver != null) {
            driver.quit();
        }
    }





















}
