package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener {
    Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test start");
        getScreen();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getScreen();
        logger.info("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getScreen();
        logger.info("Test failed");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("On start");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("On finish");
    }

    public void getScreen() {
        String fileName;
        logger.info("Start getting screen");
        File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        fileName = "src/main/resources/screen_" + System.currentTimeMillis() + ".png";
        File res = new File(fileName);
        try {
            FileUtils.copyFile(screenshot, res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Screen was saved to " + fileName);
    }
}
