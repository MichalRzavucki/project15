package pages;

import com.codeborne.selenide.Condition;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    static LoginPage loginPage = null;
    private final By EMAIL_FIELD = By.xpath("//input[@name='email']");
    private final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final By LOGIN_BUTTON = By.xpath("//button[@name='login']");
    private final String CORRECT_EMAIL = "michal.rzavucki@gmail.com";
    private final String CORRECT_PASSWORD = "30203050";
    private final String INCORRECT_EMAIL = "test@mail.com";
    private final String INCORRECT_PASSWORD = "sdf325fhg";
    public By successMessage = By.xpath("//div[@class='notice success']");
    public String expectedSuccessMessage = "You are now logged in as Michal Rzavucki.";
    public By errorMessage = By.xpath("//div[@class='notice errors']");
    public String expectedErrorMessage = "Wrong password or the account is disabled, or does not exist";
    Logger logger = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static LoginPage getInstance(WebDriver webDriver) {
        if (loginPage == null) {
            loginPage = new LoginPage(webDriver);
        }
        return loginPage;
    }

    public void attemptValidLoginData() {
        logger.info("start filling with " + CORRECT_EMAIL);
        $(EMAIL_FIELD).shouldBe(Condition.exist).sendKeys(CORRECT_EMAIL);
        logger.info("start filling with " + CORRECT_PASSWORD);
        $(PASSWORD_FIELD).shouldBe(Condition.exist).sendKeys(CORRECT_PASSWORD);
        logger.info("start clicking Login Button " + LOGIN_BUTTON);
        $(LOGIN_BUTTON).shouldBe(Condition.exist).click();
        logger.info("Method ends");
    }

    public void attemptInvalidLoginData() {
        logger.info("start filling with " + INCORRECT_EMAIL);
        $(EMAIL_FIELD).shouldBe(Condition.exist).sendKeys(INCORRECT_EMAIL);
        logger.info("start filling with " + INCORRECT_PASSWORD);
        $(PASSWORD_FIELD).shouldBe(Condition.exist).sendKeys(INCORRECT_PASSWORD);
        logger.info("start clicking Login Button " + LOGIN_BUTTON);
        $(LOGIN_BUTTON).shouldBe(Condition.exist).click();
        logger.info("Method ends");
    }

    public void checkErrorMessage() {
        logger.info("message: " + $(errorMessage).shouldBe(Condition.exist).getText());
        System.out.println($(errorMessage).shouldBe(Condition.exist).getText());
    }

    public void checkSuccessMessage() {
        logger.info("message: " + $(successMessage).shouldBe(Condition.exist).getText());
        System.out.println($(successMessage).shouldBe(Condition.exist).getText());
    }

}
