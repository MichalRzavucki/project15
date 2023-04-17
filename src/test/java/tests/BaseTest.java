package tests;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.RubberDucksPage;

import static com.codeborne.selenide.Selenide.open;


@Listeners(Listener.class)
public class BaseTest {
    private final String HOME_PAGE = "https://litecart.stqa.ru/en/";
    WebDriver webDriver;
    LoginPage loginPage;
    RubberDucksPage rubberDucksPage;
    Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void setup() {
        logger.info("Before test started");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        loginPage = LoginPage.getInstance(webDriver);
        rubberDucksPage = RubberDucksPage.getInstance(webDriver);
        WebDriverRunner.setWebDriver(webDriver);
        logger.info("Before test ended");
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Before method deleting cookies");
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        logger.info("Opening " + HOME_PAGE);
        open(HOME_PAGE);
    }

//    @AfterClass
//    public void teardown() {
//        logger.info("Tests ended");
//        WebDriverRunner.getWebDriver().quit();
//    }

}
