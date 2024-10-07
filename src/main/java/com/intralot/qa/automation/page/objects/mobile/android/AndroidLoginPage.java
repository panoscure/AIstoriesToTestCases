package com.intralot.qa.automation.page.objects.mobile.android;

import com.intralot.qa.automation.core.driver.engine.*;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.mobile.common.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AndroidLoginPage extends BasePage implements LoginPage {
    public AndroidLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    //Locators
//    @AndroidFindBy(xpath="//android.widget.TextView[@text='LOGIN']")
//    public WebElement btnLogin;

    public enum AndroidLoginPageLocators {
        //Top Section
        USERNAME_TEXTBOX("xpath=//android.widget.EditText[@text='Username/Email']"),
        PASSWORD_TEXTBOX("xpath=//android.widget.EditText[@text='Password']"),
        LOGIN_BTN("xpath=(//android.widget.TextView[@text='LOGIN'])[1]/parent::android.view.ViewGroup"),
        REGISTER_BTN("xpath=//android.widget.TextView[@text='REGISTER']"),
        LOGIN_POPUP("xpath=//android.widget.TextView[@text='ACCOUNT LOGIN']/parent::android.view.ViewGroup"),
        ;

        private String myLocator;

        AndroidLoginPageLocators(String locator) {
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
    @Step("typeUsername")
    public void typeUsername(String username) {
        Log.info("typeUsername()");
        DriverWait.forElementToBeClickableBy(webDriver,AndroidLoginPageLocators.USERNAME_TEXTBOX.getLocator(),5L).clear();
        DriverWait.forElementToBeClickableBy(webDriver,AndroidLoginPageLocators.USERNAME_TEXTBOX.getLocator(),5L).sendKeys(username);

    }

    @Step("typePassword")
    public void typePassword(String password) {
        Log.info("typePassword()");
        DriverWait.forElementToBeClickableBy(webDriver,AndroidLoginPageLocators.PASSWORD_TEXTBOX.getLocator(),5L).clear();
        DriverWait.forElementToBeClickableBy(webDriver,AndroidLoginPageLocators.PASSWORD_TEXTBOX.getLocator(),5L).sendKeys(password);

    }

    @Step("clickLoginBtn")
    public void clickLoginBtn() {
        Log.info("clickLoginBtn()");
        DriverActions.clickElement(webDriver, AndroidLoginPageLocators.LOGIN_BTN.getLocator(), 5L);
    }

    @Step("clickRegisterBtn")
    public void clickRegisterBtn() {
        Log.info("clickLoginBtn()");
        DriverActions.clickElement(webDriver, AndroidLoginPageLocators.REGISTER_BTN.getLocator(), 5L);
    }

    @Step("loginUser")
    public void loginUser(String username, String password){
        Log.info("loginUser()");

        typeUsername(username);
        typePassword(password);
        clickLoginBtn();

    }

    //Validation Methods
//    @Step("verify Header Page and click Login btn")
//    public void verifyHeaderPageStruct(){
//        Log.info("verifyHeaderPageStruct()");
//        assertEquals(DriverCheck.isElementByPresent(webDriver, AndroidHeaderPage.AndroidHeaderPageLocators.BTN_LOGIN.getLocator(),5L),true, "BTN_LOGIN is visible");
//        assertEquals(DriverFind.getElementBy(webDriver, AndroidHeaderPage.AndroidHeaderPageLocators.BTN_LOGIN.getLocator()).getText().equals("LOGIN"), true, "BTN_LOGIN text is correct");
//
//        clickLoginBtn();
//
//    }
    @Step("verify Login Popup is Visible")
    public void verifyLoginPopupIsVisible(){
        Log.info("verifyLoginPopupIsVisible()");
        assertTrue(DriverCheck.isElementByVisible(webDriver, AndroidLoginPageLocators.LOGIN_POPUP.getLocator(), 20L), "Login popup is not visible");

    }
}
