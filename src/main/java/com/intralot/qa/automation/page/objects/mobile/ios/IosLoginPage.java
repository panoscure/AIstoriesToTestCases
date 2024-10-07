package com.intralot.qa.automation.page.objects.mobile.ios;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverCheck;
import com.intralot.qa.automation.core.driver.engine.DriverWait;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.mobile.common.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

import static org.testng.Assert.assertTrue;

public class IosLoginPage extends BasePage implements LoginPage {
    public IosLoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public enum IosLoginPageLocators {
        //Top Section
        USERNAME_TEXT_BOX("iOSNsPredicate=value == \"Username/Email\""),
        PASSWORD_TEXT_BOX("iOSNsPredicate=value == \"Password\""),
        LOGIN_BTN("iOSClassChain=**/XCUIElementTypeOther[`label == \"LOGIN\"`][2]"),
        REGISTER_BTN("iOSClassChain=**/XCUIElementTypeOther[`label == \"REGISTER\"`][2]"),
        LOGIN_POPUP("iOSClassChain=**/XCUIElementTypeOther[`label == \"ACCOUNT LOGIN Username/Email Password \uDB80\uDE08 Remember me FORGOT PASSWORD LOGIN\"`]"),
        ;

        private String myLocator;

        IosLoginPageLocators(String locator) {
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
        DriverWait.forElementToBeClickableBy(webDriver, IosLoginPageLocators.USERNAME_TEXT_BOX.getLocator(),5L).clear();
        DriverWait.forElementToBeClickableBy(webDriver, IosLoginPageLocators.USERNAME_TEXT_BOX.getLocator(),5L).sendKeys(username);

    }

    @Step("typePassword")
    public void typePassword(String password) {
        Log.info("typePassword()");
        DriverWait.forElementToBeClickableBy(webDriver, IosLoginPageLocators.PASSWORD_TEXT_BOX.getLocator(),5L).clear();
        DriverWait.forElementToBeClickableBy(webDriver, IosLoginPageLocators.PASSWORD_TEXT_BOX.getLocator(),5L).sendKeys(password);

    }

    @Step("clickLoginBtn")
    public void clickLoginBtn() {
        Log.info("clickLoginBtn()");
        DriverActions.clickElement(webDriver, IosLoginPageLocators.LOGIN_BTN.getLocator(), 5L);
    }

    @Step("clickRegisterBtn")
    public void clickRegisterBtn() {
        Log.info("clickLoginBtn()");
        DriverActions.clickElement(webDriver, IosLoginPageLocators.REGISTER_BTN.getLocator(), 5L);
    }

    @Step("loginUser")
    public void loginUser(String username, String password){
        Log.info("loginUser()");

        typeUsername(username);
        typePassword(password);
        clickLoginBtn();

    }
    @Step("verify Login Popup is Visible")
    public void verifyLoginPopupIsVisible(){
        Log.info("verifyLoginPopupIsVisible()");
        assertTrue(DriverCheck.isElementByVisible(webDriver, IosLoginPageLocators.LOGIN_POPUP.getLocator(), 20L), "Login popup is not visible");

    }
}
