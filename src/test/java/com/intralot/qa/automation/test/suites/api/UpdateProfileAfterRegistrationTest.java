package com.intralot.qa.automation.test.suites.api;

import com.intralot.qa.automation.api.lottery.HeadersQueryAndFormData;
import com.intralot.qa.automation.api.lottery.apigatewayj.Authentication;
import com.intralot.qa.automation.api.lottery.apigatewayj.drawoperations.DrawOperations;
import com.intralot.qa.automation.base.test.BaseSetupForApi;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.data.RegistrationWager;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateProfileAfterRegistrationTest extends BaseSetupForApi {

    String bearerToken;
    String sessionToken;
    String username;
    String birthDate = "1980-10-02T12:40:09.646Z";

    @BeforeClass
    public void setupParams(){

        // retrieve username from registration test
        username = System.getProperty("NewUserRegistrationUsername");
        Log.info("Username is:" + username);

    }

    @Step("retrieve Authorization Token - Step")
    @Test(description = "Retrieve Authorization Token", priority = 0)
    public void retrieveAuthorizationToken(){

        String responseTxt = Authentication.grantAuthorizationToken(
                HeadersQueryAndFormData.getAuthOnlineHeaders(),
                HeadersQueryAndFormData.getAuthDataOnlineQueryParameters()).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        bearerToken = "Bearer " + jo.get("access_token");
        System.setProperty("bearerToken", bearerToken);

        Log.info("Token is:" + bearerToken);
    }

    @Step("retrieve Session Token - Step")
    @Test (description = "Retrieve Session Token", priority = 1)
    public void retrieveSessionToken(){

        String responseTxt = DrawOperations.getSessionTokenSignOn(
                HeadersQueryAndFormData.getAuthenticationSignOnHeaders(bearerToken),
                HeadersQueryAndFormData.getAuthenticationSignOnBody(username)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        sessionToken = (String) jo.get("sessionToken");
        System.setProperty("sessionToken", sessionToken);

        Log.info("Session Token is:" + sessionToken);
    }

    @Step("update profile - Step")
    @Test (description = "update profile", priority = 2)
    public void updateProfile(){

       Object obj = DrawOperations.updateProfile(
               HeadersQueryAndFormData.getUpdateProfileHeaders(bearerToken,sessionToken),
               HeadersQueryAndFormData.getUpdateProfileBody(birthDate));

    }


}
