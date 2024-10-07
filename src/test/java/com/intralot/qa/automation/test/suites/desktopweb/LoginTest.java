package com.intralot.qa.automation.test.suites.desktopweb;

import com.intralot.qa.automation.base.test.BaseSetupForApplication;
import com.intralot.qa.automation.core.jira.Store_ticket_status_list;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.page.objects.web.desktop.DesktopWebGlobalHomePage;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.intralot.qa.automation.core.jira.JiraServices.makeApiCallsAndUpdateZephyrExecutionsCycles;

public class LoginTest extends BaseSetupForApplication {
    private static String uname;
    private static String psw;

    static DesktopWebGlobalHomePage desktopWebGlobalHomePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpXmlParams() throws Exception {

        // Initialize driver
        switchToPlatformDriver();

        uname = CustomProperties.getPropertyValue("BETUSRNM1");
        psw = CustomProperties.getPropertyValue("BETUSRPSW1");

    }

    @Step("Login User - Step")
    @Test(priority=1, description = "Login User")
    public void QAAU23_39892() throws Exception {

        desktopWebGlobalHomePage = initPage(DesktopWebGlobalHomePage.class);

        desktopWebGlobalHomePage.navigateToBaseUrl();

        desktopWebGlobalHomePage.clickLoginHomePageHeader();
        desktopWebGlobalHomePage.typeUsername(uname);
        desktopWebGlobalHomePage.typePassword(psw);
        desktopWebGlobalHomePage.clickLoginButton();

        desktopWebGlobalHomePage.verifyUserLoggedIn();
        Thread.sleep(5000);
    }

    @AfterMethod
    public void shadhaskjdhkjsadsa(ITestResult result) {
        Store_ticket_status_list.update_tc_status_full_flow(result,
                CustomProperties.getPropertyValue("jira.cycle.id"),
                CustomProperties.getPropertyValue("jira.cycle.folder.name"));
    }

}
