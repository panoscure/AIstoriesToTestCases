package com.intralot.qa.automation.test.suites.mobile;

import com.intralot.qa.automation.base.test.BaseSetupForApplication;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.page.objects.mobile.common.*;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseSetupForApplication {
    private static String uname;
    private static String psw;

    static WelcomePage welcomePage;
    static HeaderPage headerPage;
    static LoginPage loginPage;
    static PerimissionsPage perimissionsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpXmlParams() throws Exception {

        // Initialize driver
        switchToPlatformDriver();

        uname = CustomProperties.getPropertyValue("BETUSRNM1");
        psw = CustomProperties.getPropertyValue("BETUSRPSW1");

    }

    @Step("Login User - Step")
    @Test(priority=1, description = "Login User")
    public void login() throws Exception {
        welcomePage = initPage(WelcomePage.class);
        headerPage = initPage(HeaderPage.class);
        loginPage = initPage(LoginPage.class);
        perimissionsPage = initPage(PerimissionsPage.class);

        welcomePage.loginFirstTimeAs(uname,psw);
        Thread.sleep(3000);

        //Check for location permissions
        perimissionsPage.allowPermissionsIfVisible();

        headerPage.verifyUserLoggedIn();

    }

}
