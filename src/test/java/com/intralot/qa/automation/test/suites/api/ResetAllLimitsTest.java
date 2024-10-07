package com.intralot.qa.automation.test.suites.api;

import com.intralot.qa.automation.api.pam.HeadersCookiesQueriesAndFormData;
import com.intralot.qa.automation.api.pam.PamServices;
import com.intralot.qa.automation.base.test.BaseSetupForApi;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResetAllLimitsTest extends BaseSetupForApi {

    String playerPamId;

    @BeforeClass
    public void setupParams(){

        playerPamId = CustomProperties.getPropertyValue("PAMIDuser2");
    }

    @Step("Reset All Limits - Step")
    @Test(description = "Reset All Limits", priority = 1)
    public void resetAllLimits(){

        Log.info("resetAllLimits() for player Pam Id: " + playerPamId);

        Object obj =
                PamServices.resetAllLimits(playerPamId, HeadersCookiesQueriesAndFormData.getPamHeaders(),HeadersCookiesQueriesAndFormData.getResetAllLimitsBody());

    }
}
