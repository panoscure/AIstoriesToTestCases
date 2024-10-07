package com.intralot.qa.automation.test.utilities.extensions.listeners;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.utilities.Log;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.*;
import java.io.IOException;
import static com.intralot.qa.automation.core.driver.engine.DriverFactory.driver;

public class TestListener implements ITestListener, IInvokedMethodListener {

    // Constructor
    public TestListener() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    private static String getTestMethodName(ITestResult testResult) {
        return testResult.getMethod().getConstructorOrMethod().getName();
    }

    // Image attachment(s) for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachment(s) for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }


    // HTML attachment(s) for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }


    @Override
    public void onTestSuccess(ITestResult testResult) {
        Log.info(getTestMethodName(testResult) + " has passed.");
        testResult.setStatus(ITestResult.SUCCESS);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        Log.info(getTestMethodName(testResult) + " was skipped.");
        testResult.setStatus(ITestResult.SKIP);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if (null != testResult.getThrowable()) {

            Log.info(getTestMethodName(testResult) + " has failed.");

            if(driver != null) {
                // Get screenshot
                try {
                    DriverActions.takeScreenshot(driver, getTestMethodName(testResult));
                    DriverActions.copyScreenshotToReportNG();

                    // Get screenshot for Allure
                    saveScreenshotPNG(driver);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Log.error("Generated exception: \n" + testResult.getThrowable().getMessage());

            // Save a log on Allure
            saveTextLog(getTestMethodName(testResult) + " failed and screenshot taken!");

            testResult.setStatus(ITestResult.FAILURE);
        }
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    public void onTestFailure(ITestResult testResult) {
    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult testResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
    }

    public void onStart(ITestContext context) {
    }
}

