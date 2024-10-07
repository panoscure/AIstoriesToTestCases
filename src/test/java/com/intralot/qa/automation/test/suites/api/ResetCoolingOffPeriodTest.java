package com.intralot.qa.automation.test.suites.api;

import com.intralot.qa.automation.api.pam.HeadersCookiesQueriesAndFormData;
import com.intralot.qa.automation.api.pam.PamServices;
import com.intralot.qa.automation.base.test.BaseSetupForApi;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResetCoolingOffPeriodTest extends BaseSetupForApi {

    String playerPamId;

    @BeforeClass
    public void setupParams(){

        playerPamId = CustomProperties.getPropertyValue("PAMIDuser2");
    }

    @Step("Reset Cooling-Off Period - Step")
    @Test (description = "Reset Cooling-Off Period", priority = 1)
    public void resetCoolingOffPeriod(){

        Log.info("resetCoolingOffPeriod() for player Pam Id: " + playerPamId);

        Object obj =
                PamServices.resetCoolingOffPeriod(playerPamId, HeadersCookiesQueriesAndFormData.getPamHeaders(),HeadersCookiesQueriesAndFormData.getResetCoolingOffBody());


    }

}
