package com.intralot.qa.automation.page.objects.web.desktop;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverCheck;
import com.intralot.qa.automation.core.driver.engine.DriverWait;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.web.common.WebGlobalHomePage;
import com.intralot.qa.automation.core.utilities.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

import static org.testng.Assert.assertTrue;


public class DesktopWebGlobalHomePage extends BasePage implements WebGlobalHomePage {
    public DesktopWebGlobalHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public enum DesktopWebGlobalHomePageLocators {

        LOGIN_BTN_HOME("xpath=//*[@id='open-login-form']"),
        USERNAME_TEXTBOX("xpath=//*[@id='username']"),
        PASSWORD_TEXTBOX("xpath=//*[@id='password']"),
        LOGIN_BTN("xpath=//*[@id='login']"),
        BALANCE_CONTAINER("xpath=//*[@class='balance-container']"),
        ;

        private String myLocator;

        DesktopWebGlobalHomePageLocators(String locator) {
            myLocator=locator;
        }

        public String getLocator() {
            return myLocator;
        }

        public String getLocatorWithParams(Object... params) {
            return MessageFormat.format(myLocator, params);
        }
    }

    @Step("navigateToBaseUrl")
    public void navigateToBaseUrl(){
        DriverActions.navigateToBaseUrl(webDriver, CustomProperties.getPropertyValue("dcilotteryurl"));
    }

    @Step("clickLoginHomePageHeader")
    public void clickLoginHomePageHeader() {
        DriverActions.clickElement(webDriver, DesktopWebGlobalHomePageLocators.LOGIN_BTN_HOME.getLocator(), 5L);
    }

    @Step("typeUsername")
    public void typeUsername(String uname){
        DriverWait.forElementToBeClickableBy(webDriver, DesktopWebGlobalHomePageLocators.USERNAME_TEXTBOX.getLocator(), 5L).sendKeys(uname);
    }

    @Step("typePassword")
    public void typePassword(String pwd){
        DriverWait.forElementToBeClickableBy(webDriver, DesktopWebGlobalHomePageLocators.PASSWORD_TEXTBOX.getLocator(), 5L).sendKeys(pwd);
    }

    @Step("clickLoginButton")
    public void clickLoginButton() {
        DriverActions.clickElement(webDriver, DesktopWebGlobalHomePageLocators.LOGIN_BTN.getLocator(), 5L);
    }

    @Step("verify user is logged in")
    public void verifyUserLoggedIn(){
        Log.info("verifyUserLoggedIn()");
        assertTrue(DriverCheck.isElementByPresent(webDriver, DesktopWebGlobalHomePageLocators.BALANCE_CONTAINER.getLocator(), 5L), "User Balance is not visible");

    }

}
