package com.intralot.qa.automation.page.objects.mobile.ios;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverCheck;
import com.intralot.qa.automation.core.driver.engine.DriverFind;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.mobile.android.AndroidHeaderPage;
import com.intralot.qa.automation.page.objects.mobile.common.HeaderPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class IosHeaderPage extends BasePage implements HeaderPage {
    public IosHeaderPage(WebDriver webDriver) {
        super(webDriver);
    }



    public enum IosHeaderPageLocators {
        //Top Section
        BTN_LOGIN("xpath=//android.widget.TextView[@text='LOGIN']"),
        BURGER_MENU("xpath=//android.view.ViewGroup[@resource-id='header_drawer_menu_button']"),
        PROFILE_BTN("xpath=//android.view.ViewGroup[@resource-id='header_profile_button']"),
        NOTIFICATIONS_BTN("xpath=//android.view.ViewGroup[@resource-id='header_notification_button']"),
        BALANCE_TXT("xpath=//android.widget.TextView[contains(@text,'BALANCE:')]"),
        BALANCE_AMOUNT("xpath=//android.widget.TextView[contains(@text,'BALANCE:')]/following-sibling::android.widget.TextView"),

        ;

        private String myLocator;

        IosHeaderPageLocators(String locator) {
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
    @Step("clickLoginBtn")
    public void clickLoginBtn() {
        Log.info("clickLoginBtn()");
        DriverActions.clickElement(webDriver, IosHeaderPageLocators.BTN_LOGIN.getLocator(), 10L);
    }

    @Step("clickBurgerMenuBtn")
    public void clickBurgerMenuBtn() {
        Log.info("clickBurgerMenuBtn()");
        DriverActions.clickElement(webDriver, IosHeaderPageLocators.BURGER_MENU.getLocator(), 10L);
    }

    //Validation Methods
    @Step("verify Header Page and click Login btn")
    public void verifyHeaderPageStruct(){
        Log.info("verifyHeaderPageStruct()");
        assertEquals(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.BTN_LOGIN.getLocator(),5L),true, "BTN_LOGIN is visible");
        assertEquals(DriverFind.getElementBy(webDriver, IosHeaderPageLocators.BTN_LOGIN.getLocator()).getText().equals("LOGIN"), true, "BTN_LOGIN text is correct");

        clickLoginBtn();

    }

    @Step("verify user is logged in")
    public void verifyUserLoggedIn(){
        Log.info("verifyUserLoggedIn()");
        assertEquals(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.PROFILE_BTN.getLocator(),5L),true, "PROFILE_BTN is visible");
        assertEquals(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.NOTIFICATIONS_BTN.getLocator(),5L),true, "NOTIFICATIONS_BTN is visible");
        assertEquals(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.BALANCE_TXT.getLocator(),5L),true, "BALANCE_TXT is visible");
        assertEquals(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.BALANCE_AMOUNT.getLocator(),5L),true, "BALANCE_AMOUNT is visible");

    }
    @Step("verify user is not logged in")
    public void verifyUserNotLoggedIn(){
        Log.info("verifyUserNotLoggedIn()");
        assertFalse(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.PROFILE_BTN.getLocator(), 5L), "PROFILE_BTN is not visible");
        assertFalse(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.NOTIFICATIONS_BTN.getLocator(),5L), "NOTIFICATIONS_BTN is not visible");
        assertFalse(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.BALANCE_TXT.getLocator(),5L), "BALANCE_TXT is not visible");
        assertFalse(DriverCheck.isElementByPresent(webDriver, IosHeaderPageLocators.BALANCE_AMOUNT.getLocator(),5L), "BALANCE_AMOUNT is not visible");

    }

}
