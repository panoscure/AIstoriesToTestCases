package com.intralot.qa.automation.page.objects.mobile.android;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverCheck;
import com.intralot.qa.automation.core.driver.engine.DriverFind;
import com.intralot.qa.automation.core.driver.engine.DriverWait;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.mobile.common.HeaderPage;
import com.intralot.qa.automation.page.objects.mobile.common.LoginPage;
import com.intralot.qa.automation.page.objects.mobile.common.WelcomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.MessageFormat;

import static com.intralot.qa.automation.core.driver.engine.DriverCheck.isElementVisible;
import static org.testng.Assert.assertEquals;

public class AndroidWelcomePage extends BasePage implements WelcomePage {
    public AndroidWelcomePage(WebDriver webDriver) {
        super(webDriver);
    }

    HeaderPage headerPage = new AndroidHeaderPage(webDriver);
    LoginPage loginPage = new AndroidLoginPage(webDriver);

    //Locators

    public enum AndroidWelcomePageLocators {
        //Top Section
        BTN_NEXT_STEP("xpath=//android.widget.TextView[@text='NEXT STEP']"),
        BTN_TAKE_ME_HOME("xpath=//android.widget.TextView[@text='TAKE ME HOME']"),
        CLOSE_ICON("xpath=//android.widget.TextView[contains(@text,'WELCOME TO THE')]/following-sibling::android.view.ViewGroup[1]"),
        X_ICON("xpath=//android.widget.TextView[@text='ACCOUNT LOGIN']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup"),
        USER_LOCKED_INFO("xpath=//android.widget.TextView[@text='YOU'VE REACHED THE MAXIMUM LOGIN ATTEMPTS. TO UNLOCK YOUR ACCOUNT, PLEASE CONTACT CUSTOMER CARE AT 1-877-261-5708.']"),
        ;

        private String myLocator;

        AndroidWelcomePageLocators(String locator) {
            myLocator=locator;
        }

        public String getLocator() {
            return myLocator;
        }

        public String getLocatorWithParams(Object... params) {
            return MessageFormat.format(myLocator, params);
        }
    }

    //Action Methods
    @Step("clickNextStepBtn")
    public void clickNextStepBtn() {
        Log.info("clickNextStepBtn()");
        DriverActions.clickElement(webDriver, AndroidWelcomePageLocators.BTN_NEXT_STEP.getLocator(),10L);
    }

    @Step("clickTakeMeHomeBtn")
    public void clickTakeMeHomeBtn() {
        Log.info("clickTakeMeHomeBtn()");
        DriverActions.clickElement(webDriver, AndroidWelcomePageLocators.BTN_TAKE_ME_HOME.getLocator(),10L);
    }
    @Step("clickCloseIcon")
    public void clickCloseIcon() {
        Log.info("clickCloseIcon()");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        DriverActions.clickElement(webDriver, AndroidWelcomePageLocators.CLOSE_ICON.getLocator(),10L);
    }

    //Validation Methods
    @Step("Skip Welcome Page")
    public void skipWelcomePage(){
        Log.info("skipWelcomePage()");

        clickCloseIcon();

    }

    @Step("login as user {0} with pwd {1}")
    public void loginFirstTimeAs(String username, String password){

//        while (true) {
//            try {
//                // Try to locate the Button "Take Me Home"
//                DriverFind.getElementBy(webDriver, AndroidWelcomePageLocators.BTN_TAKE_ME_HOME.getLocator()).isDisplayed();
//
//                // Click the "Take Me Home" button
//                clickTakeMeHomeBtn();
//                break;
//            } catch (org.openqa.selenium.NoSuchElementException e) {
//                // If the "Take Me Home" button is not found, click the "Next Step" button
//                clickNextStepBtn();
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }

        clickCloseIcon();

        headerPage.clickLoginBtn();
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLoginBtn();

    }

    @Step("clickXIcon")
    public void clickXIcon() {
        Log.info("clickXIcon()");
        DriverActions.clickElement(webDriver, AndroidWelcomePageLocators.X_ICON.getLocator(),10L);
    }
    @Step("clickXIcon")
    public void verifyUserLocked() {
        Log.info("clickXIcon()");

    }


}
