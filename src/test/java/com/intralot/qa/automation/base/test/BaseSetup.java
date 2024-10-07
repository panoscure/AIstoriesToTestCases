package com.intralot.qa.automation.base.test;

import com.intralot.qa.automation.page.objects.factory.PageObjectGeneratorFactory;
import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverFactory;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import static com.intralot.qa.automation.core.utilities.CustomProperties.loadPropertiesFile;

public class BaseSetup {

    public WebDriver driver;

    public void loadProperties() throws IOException {
        String usePropertiesFile = null;

        switch (System.getenv("TARGET_PLATFORM")) {

            case "DesktopWeb":
                usePropertiesFile = "desktop_web.properties";
                break;
            case "AndroidMobileWeb":
                usePropertiesFile = "android_mobile_web.properties";
                break;
            case "iOSMobileWeb":
                usePropertiesFile = "ios_mobile_web.properties";
                break;
            case "EmulatedWeb":
                usePropertiesFile = "emulated_mobile_web.properties";
                break;
            case "AndroidApp":
                usePropertiesFile = "android_native_app.properties";
                break;
            case "iOSApp":
                usePropertiesFile = "ios_native_app.properties";
                break;
        }

        loadPropertiesFile("src/test/resources/" + usePropertiesFile);
    }

    public void getWebDriver() {
        driver = DriverFactory.getDesktopWebDriver();
    }

    public void getMobileDriver() throws Exception {

        if (CustomProperties.getPlatform().toLowerCase().equals("android")) {

            if (CustomProperties.getPlatformType().toLowerCase().equals("native_application"))
                driver = DriverFactory.getAndroidApplicationDriver();
            else if (CustomProperties.getPlatformType().toLowerCase().equals("mobile_web"))
                driver = DriverFactory.getAndroidWebDriver();
        }
        else if (CustomProperties.getPlatform().toLowerCase().equals("ios")) {

            if (CustomProperties.getPlatformType().toLowerCase().equals("native_application"))
                driver = DriverFactory.getiOSApplicationDriver();
            else if (CustomProperties.getPlatformType().toLowerCase().equals("mobile_web"))
                driver = DriverFactory.getiOSWebDriver();
        }

        else if (CustomProperties.getPlatform().toLowerCase().equals("desktop")) {
            if (CustomProperties.getPlatformType().toLowerCase().equals("emulated_mobile_web"))
                driver = DriverFactory.getEmulatedWebDriver();
        }
    }

    public void setDriver() {
        PageObjectGeneratorFactory.getInstance().setDriver(driver);
    }

    protected <T> T initPage(Class<T> pageType) {
        return PageObjectGeneratorFactory.getInstance().createPage(pageType);
    }

    public void quitDriver() {
        DriverActions.closeSession(driver);
    }

    public void refreshPage() {
        DriverActions.refreshPage(driver);
    }

    public void switchToWebDriver() {
        if ((driver==null) || (driver instanceof AppiumDriver) || ((!(driver instanceof AppiumDriver)) && CustomProperties.getPlatformType().toLowerCase().equals("emulated_mobile_web")))
            getWebDriver();
        setDriver();
    }

    public void switchToMobileDriver() throws Exception {
        if (driver==null)
            getMobileDriver();
        else if (!(driver instanceof AppiumDriver)) {
            quitDriver();
            getMobileDriver();
        }
        setDriver();
    }

    @Step("Step1 - Launch App")
    public void switchToPlatformDriver() throws Exception {

        switch (System.getenv("TARGET_PLATFORM")) {

            case "DesktopWeb":
                switchToWebDriver();
                break;
            case "AndroidMobileWeb":
            case "iOSMobileWeb":
            case "EmulatedWeb":
            case "AndroidApp":
            case "iOSApp":
                switchToMobileDriver();
                break;
        }
    }


}
