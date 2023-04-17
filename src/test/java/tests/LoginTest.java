package tests;

import com.codeborne.selenide.Condition;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class LoginTest extends BaseTest {

    Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    public void validLoginTest() {
        logger.info("validLoginTest");
        loginPage.attemptValidLoginData();
        loginPage.checkSuccessMessage();
        $(loginPage.successMessage).should(Condition.exist);
        $(loginPage.successMessage).shouldHave(Condition.exactText(loginPage.expectedSuccessMessage));
    }

    @Test
    public void invalidLoginTest() {
        logger.info("invalidLoginTest");
        loginPage.attemptInvalidLoginData();
        loginPage.checkErrorMessage();
        $(loginPage.errorMessage).should(Condition.exist);
        $(loginPage.errorMessage).shouldHave(Condition.exactText(loginPage.expectedErrorMessage));
    }
}
