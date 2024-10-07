package com.intralot.qa.automation.page.objects.factory;

import com.intralot.qa.automation.page.objects.mobile.android.*;
import com.intralot.qa.automation.page.objects.mobile.common.*;
import com.intralot.qa.automation.page.objects.mobile.ios.*;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.page.objects.web.desktop.DesktopWebGlobalHomePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class PageObjectGeneratorFactory {

    public static PageObjectGeneratorFactory instance = null;

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public PageObjectGeneratorFactory() {
    }

    @SuppressWarnings("unchecked")
    public <T> T createPage(Class<T> pageType) {

        WebDriver targetDriver = driver.get();

        if (targetDriver instanceof AndroidDriver) {

            AndroidDriver androidDriver = (AndroidDriver) targetDriver;

            //Android App
            if (pageType.isAssignableFrom(HeaderPage.class)) {
                return (T) new AndroidHeaderPage(androidDriver);
            } else if (pageType.isAssignableFrom(WelcomePage.class)) {
                return (T) new AndroidWelcomePage(androidDriver);
            } else if (pageType.isAssignableFrom(LoginPage.class)) {
                return (T) new AndroidLoginPage(androidDriver);
            } else if (pageType.isAssignableFrom(PerimissionsPage.class)) {
                return (T) new AndroidPermissionsPage(androidDriver);
            }

            //AndroidLocationPermissionsPagePageLocators
            //Android Mobile Web


        }


        else if (targetDriver instanceof IOSDriver) {

            IOSDriver iosDriver = (IOSDriver) targetDriver;

            //iOS App
            if (pageType.isAssignableFrom(HeaderPage.class)) {
                return (T) new IosHeaderPage(iosDriver);
            } else if (pageType.isAssignableFrom(WelcomePage.class)) {
                return (T) new IosWelcomePage(iosDriver);
            } else if (pageType.isAssignableFrom(LoginPage.class)) {
                return (T) new IosLoginPage(iosDriver);
            } else if (pageType.isAssignableFrom(PerimissionsPage.class)) {
                return (T) new IosPermissionsPage(iosDriver);
            }
            //iOS Mobile Web

        }

        // Emulated WebDriver
        else if ((!(targetDriver instanceof AppiumDriver)) && CustomProperties.getPlatformType().toLowerCase().equals("emulated_mobile_web")) {

            WebDriver webDriver = targetDriver;

//            if (pageType.isAssignableFrom(WebAccountSideMenuPage.class)) {
//                return (T) new MobileWebAccountSideMenuPage(webDriver);
//            } else if (pageType.isAssignableFrom(WebCommunicationPreferencesPage.class)) {
//                return (T) new MobileWebCommunicationPreferencesPage(webDriver);
//            }
        }

        // Desktop WebDriver
        else {

            WebDriver webDriver = targetDriver;

            /////////////////////// ONLINE PAGES ///////////////////////////////
            if (pageType.isAssignableFrom(DesktopWebGlobalHomePage.class)) {
                return (T) new DesktopWebGlobalHomePage(webDriver);
            }

        }

        return null;
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public static PageObjectGeneratorFactory getInstance() {
        if (instance == null) {
            instance = new PageObjectGeneratorFactory();
        }
        return instance;
    }

}
