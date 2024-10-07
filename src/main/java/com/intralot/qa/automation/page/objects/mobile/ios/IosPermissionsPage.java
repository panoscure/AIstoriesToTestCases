package com.intralot.qa.automation.page.objects.mobile.ios;

import com.intralot.qa.automation.core.driver.engine.DriverActions;
import com.intralot.qa.automation.core.driver.engine.DriverCheck;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.BasePage;
import com.intralot.qa.automation.page.objects.mobile.android.AndroidPermissionsPage;
import com.intralot.qa.automation.page.objects.mobile.common.PerimissionsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class IosPermissionsPage extends BasePage implements PerimissionsPage {
    public IosPermissionsPage(WebDriver webDriver) {
        super(webDriver);
    }

    //Locators
    public enum IosLocationPermissionsPagePageLocators {

        //Android 13 Emulator
//        PERMISSIONS_DIALOG("xpath=//android.widget.Button[@resource-id='com.android.permissioncontroller:id/grant_dialog']"),
//        WHILE_USING_APP_BTN("xpath=//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"),
//        ONLY_THIS_TIME_BTN("xpath=//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']"),
//        DONT_ALLOW_BTN("xpath=//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']"),

        //Huawei device
        PERMISSIONS_DIALOG("xpath=//android.widget.Button[@resource-id='com.android.packageinstaller:id/dialog_container']"),
        PERMISSIONS_ALLOW_BTN("xpath=//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']"),
        LOCATION_DENY_BTN("xpath=//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_deny_button]"),

        ;

        private String myLocator;

        IosLocationPermissionsPagePageLocators(String locator) {
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
    @Step("clickAllowPermissionsBtn")
    public void clickAllowPermissionsBtn() {
        Log.info("clickAllowPermissionsBtn()");
        DriverActions.clickElement(webDriver, IosLocationPermissionsPagePageLocators.PERMISSIONS_ALLOW_BTN.getLocator(), 10L);
    }

    @Step("allowPermissionsIfVisible")
    public void allowPermissionsIfVisible(){
        Log.info("allowPermissionsIfVisible");
        if (DriverCheck.isElementByVisible(webDriver, IosLocationPermissionsPagePageLocators.PERMISSIONS_DIALOG.getLocator(),10L)){
            Log.info("Permissions Dialog is visible");
            clickAllowPermissionsBtn();
        }
    }

}
