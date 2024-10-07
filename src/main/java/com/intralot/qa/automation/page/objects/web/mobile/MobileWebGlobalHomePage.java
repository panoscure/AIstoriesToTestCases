package com.intralot.qa.automation.page.objects.web.mobile;

import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.web.common.WebGlobalHomePage;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class MobileWebGlobalHomePage extends BasePage implements WebGlobalHomePage {

    public MobileWebGlobalHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public enum MobileWebGlobalHomePageLocators {

        LOGIN_BTN_HOME("xpath=//*[@id='open-login-form']"),
        USERNAME_TEXTBOX("xpath=//*[@id='username']"),
        PASSWORD_TEXTBOX("xpath=//*[@id='password']"),
        LOGIN_BTN("xpath=//*[@id='login']"),
        BALANCE_CONTAINER("xpath=//*[@class='balance-container']"),
        ;

        private String myLocator;

        MobileWebGlobalHomePageLocators(String locator) {
            myLocator=locator;
        }

        public String getLocator() {
            return myLocator;
        }

        public String getLocatorWithParams(Object... params) {
            return MessageFormat.format(myLocator, params);
        }
    }

    @Override
    public void navigateToBaseUrl() {

    }

    @Override
    public void clickLoginHomePageHeader() {

    }

    @Override
    public void typeUsername(String uname) {

    }

    @Override
    public void typePassword(String pwd) {

    }

    @Override
    public void clickLoginButton() {

    }

    @Override
    public void verifyUserLoggedIn() {

    }
}
