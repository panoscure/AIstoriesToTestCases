package com.intralot.qa.automation.base.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static com.intralot.qa.automation.core.utilities.CustomProperties.loadPropertiesFile;

public class BaseSetupForApplication extends BaseSetup {

    public static WebDriver webDriver;

    @BeforeSuite
    public void initSetup(ITestContext iTestContext) throws Exception {
        // Load properties' values for target Device/Platform (and Platform type)
        loadProperties();

        // Load properties' values
        loadPropertiesFile("src/test/resources/common.properties");
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        //webDriver.quit();
    }

    public static void initializeDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(new String[]{"--start-maximized"});
        chromeOptions.addArguments(new String[]{"--ignore-certificate-errors"});
        chromeOptions.addArguments(new String[]{"--remote-debugging-port=9222"});
        webDriver = new ChromeDriver(chromeOptions);
    }

}
