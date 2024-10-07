package com.intralot.qa.automation.test.suites.api;

import com.intralot.qa.automation.api.lottery.HeadersQueryAndFormData;
import com.intralot.qa.automation.api.lottery.apigatewayj.Authentication;
import com.intralot.qa.automation.api.lottery.apigatewayj.drawoperations.DrawOperations;
import com.intralot.qa.automation.base.test.BaseSetupForApi;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.DateUtilities;
import com.intralot.qa.automation.core.utilities.Log;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class GetActiveDrawsTest extends BaseSetupForApi {

    String bearerToken;
    String megaMillionsGameId;
    String luckyForLifeGameId;
    String powerballGameId;
    String dc3GameId;
    String dc5GameId;
    String r2rGameId;
    int activeDrawId;
    int modifiedActiveDrawId;
    Long drawTime;
    Long modifiedDrawTimeLongValue;
    Long sleepTime = (long) 0;
    Long sleep = (long) 85000;

    @BeforeClass
    public void setupParams(){

        megaMillionsGameId = CustomProperties.getPropertyValue("megamillions");
        powerballGameId = CustomProperties.getPropertyValue("powerball");
        dc3GameId = CustomProperties.getPropertyValue("dc3");
        dc5GameId = CustomProperties.getPropertyValue("dc5");
        r2rGameId = CustomProperties.getPropertyValue("r2r");
        luckyForLifeGameId = CustomProperties.getPropertyValue("lucky.for.life");
    }

    @Step("retrieve Authorization Token")
    @Test (description = "Retrieve Authorization Token", priority = 0)
    public void retrieveAuthorizationToken(){

        String responseTxt = Authentication.grantAuthorizationToken(
                HeadersQueryAndFormData.getAuthOnlineHeaders(),
                HeadersQueryAndFormData.getAuthDataOnlineQueryParameters()).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        bearerToken = "Bearer " + jo.get("access_token");
        System.setProperty("bearerToken", bearerToken);

        Log.info("Token is:" + bearerToken);
    }

    @Step("Retrieve active draws")
    @Test (description = "Retrieve Active Draws", priority = 1)
    public void retrieveActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(megaMillionsGameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");

        Log.info("Active Draw Id for MegaMillions is:" + activeDrawId);
        System.setProperty("MegaMillionsActiveDrawId", String.valueOf(activeDrawId));

        Log.info("Draw Time in epoc for MegaMillions is:" + drawTime);

        //Log.info("Draw Time in readable format for MegaMillions is:" + DateUtilities.epocToLocalDateTime(drawTime));
        Log.info("Draw Time in readable format for MegaMillions is:" + DateUtilities.epocToDateTime(drawTime, "US/Eastern"));

        Log.info("Draw Time in US format is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));

        System.setProperty("MegaMillions_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));
        System.setProperty("MegaMillions_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),true));
    }

    //    Powerball Active Draws
    @Step("Retrieve active draws Powerball")
    @Test (description = "Retrieve Active Draws Powerball", priority = 2)
    public void retrievePowerballActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(powerballGameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");

        Log.info("Active Draw Id for Powerball is:" + activeDrawId);
        System.setProperty("PowerballActiveDrawId", String.valueOf(activeDrawId));

        Log.info("Draw Time in epoc for Powerball is:" + drawTime);

        //Log.info("Draw Time in readable format for Powerball is:" + DateUtilities.epocToLocalDateTime(drawTime));
        Log.info("Draw Time in readable format for Powerball is:" + DateUtilities.epocToDateTime(drawTime, "US/Eastern"));

        Log.info("Draw Time in US format for Powerball is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));

        System.setProperty("Powerball_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));
        System.setProperty("Powerball_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),true));
    }

    //    DC3 Active Draws
    @Step("Retrieve active draws DC3")
    @Test (description = "Retrieve Active Draws DC3", priority = 3)
    public void retrieveDC3ActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(dc3GameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");

        Log.info("Active Draw Id for DC3 is:" + activeDrawId);
        System.setProperty("DC3ActiveDrawId", String.valueOf(activeDrawId));

        Log.info("Draw Time in epoc for DC3 is:" + drawTime);

        Log.info("Draw Time in readable format for DC3 is:" + DateUtilities.epocToDateTime(drawTime, "US/Eastern"));

        Log.info("Draw Time in US format for DC3 is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));

        System.setProperty("DC3_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));
        System.setProperty("DC3_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),true));
    }

    //    DC5 Active Draws
    @Step("Retrieve active draws DC5")
    @Test (description = "Retrieve Active Draws DC5", priority = 4)
    public void retrieveDC5ActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(dc5GameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");

        Log.info("Active Draw Id for DC5 is:" + activeDrawId);
        System.setProperty("DC5ActiveDrawId", String.valueOf(activeDrawId));

        Log.info("Draw Time in epoc for DC5 is:" + drawTime);

        Log.info("Draw Time in readable format for DC5 is:" + DateUtilities.epocToDateTime(drawTime, "US/Eastern"));

        Log.info("Draw Time in US format for DC5 is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));

        System.setProperty("DC5_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));
        System.setProperty("DC5_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),true));
    }

    //    Race2Riches Active Draws
    @Step("Retrieve active draws Race2Riches")
    @Test (description = "Retrieve Active Draws Race2Riches", priority = 5)
    public void retrieveRace2RichesActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(r2rGameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");


//        Issue: In the R2R game, a draw occurs every 4 minutes, and the automation test takes approximately 4 minutes to execute.
//        Consequently, the next draw may begin during the test execution, depending on the test start time.
//        To address this issue, we have implemented the following workaround:
//            1. The code gets the current time in the Eastern timezone, then converts it to an epoch long value.
//            2. It then converts the epoch long value to a LocalDateTime object.
//            3. It then gets the difference between the current time and the start time of the draw. This value is in minutes.
//            4. It then gets the difference between the current time and the start time of the draw. This value is in seconds.
//            5. It then gets the number of seconds that have passed since the last minute. This value is obtained by subtracting the value obtained in step 4 from the value obtained in step 5.
//            6. It then adds the value obtained in step 6 to the current time. This gives the start time of the next draw.
//            7. It then converts the start time of the next draw to a long value.
//            8. It then calculates the sleep time by subtracting the value obtained in step 5 from the value obtained in step 8.
//            9. It then sleeps for the number of milliseconds obtained in step 9.

        Instant originalDrawTime = Instant.ofEpochMilli(drawTime);  // Convert the long value to an Instant
        LocalDateTime activeDraw = DateUtilities.epocToDateTime(drawTime, "US/Eastern");
        modifiedActiveDrawId = activeDrawId;
        modifiedDrawTimeLongValue = drawTime;

        ZoneId easternZone = ZoneId.of("US/Eastern");
        ZonedDateTime easternZoneDateTime = ZonedDateTime.now(easternZone);
        Instant instantEasternZoneDateTime = easternZoneDateTime.toInstant();
        long currentDateTimeLong = instantEasternZoneDateTime.toEpochMilli();
        LocalDateTime formattedCurrentDateTime = DateUtilities.epocToDateTime(currentDateTimeLong, "US/Eastern");

        long minutesDifference = ChronoUnit.MINUTES.between(formattedCurrentDateTime, activeDraw);
        long x = 60 * minutesDifference;
        long secondsDifference = ChronoUnit.SECONDS.between(formattedCurrentDateTime, activeDraw);

        Log.info("The Next Draw Will Start In:" +  secondsDifference + "Seconds");


        if (secondsDifference >= 55) {
            // Add 4 minutes to date
            Instant modifiedDrawTime = originalDrawTime.plus(4, ChronoUnit.MINUTES);    // Add 4 minutes to the Instant
            modifiedDrawTimeLongValue = modifiedDrawTime.toEpochMilli();    // Convert the modified Instant back to a long value
            modifiedActiveDrawId =activeDrawId + 1;
//            sleepTime = (9+secondsDifference - x) * 1000;
            sleepTime = sleep;

            Log.info("The Sleep Time Is:" +  sleepTime + "Milli Seconds");
        }


        Log.info("Active Draw Id for Race2Riches is:" + modifiedActiveDrawId);
        System.setProperty("Race2RichesActiveDrawId", String.valueOf(modifiedActiveDrawId));

        Log.info("Draw Time in epoc for Race2Riches is:" + modifiedDrawTimeLongValue);

        Log.info("Draw Time in readable format for Race2Riches is:" + DateUtilities.epocToDateTime(modifiedDrawTimeLongValue, "US/Eastern"));

        Log.info("Draw Time in US format for Race2Riches is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(modifiedDrawTimeLongValue, "US/Eastern"),false));

        System.setProperty("Race2Riches_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(modifiedDrawTimeLongValue, "US/Eastern"),false));
        System.setProperty("Race2Riches_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(modifiedDrawTimeLongValue, "US/Eastern"),true));

        System.setProperty("DifferenceInSeconds", String.valueOf(sleepTime));

    }

    // Active Draws For The Game Lucky For Life
    @Step("Retrieve active draws")
    @Test (description = "Retrieve Active Draws", priority = 1)
    public void retrieveLuckyForLifeActiveDraws(){

        String responseTxt = DrawOperations.retrieveActiveDrawForRequestedGameCode(
                Integer.valueOf(luckyForLifeGameId),
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(bearerToken)).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        activeDrawId = (int) jo.get("drawId");
        drawTime = (Long) jo.get("drawTime");

        Log.info("Active Draw Id for LuckyForLife is:" + activeDrawId);
        System.setProperty("LuckyForLifeActiveDrawId", String.valueOf(activeDrawId));

        Log.info("Draw Time in epoc for LuckyForLife is:" + drawTime);

        //Log.info("Draw Time in readable format for MegaMillions is:" + DateUtilities.epocToLocalDateTime(drawTime));
        Log.info("Draw Time in readable format for LuckyForLife is:" + DateUtilities.epocToDateTime(drawTime, "US/Eastern"));

        Log.info("Draw Time in US format is: " + DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));

        System.setProperty("LuckyForLife_DrawTime", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),false));
        System.setProperty("LuckyForLife_DrawTime_WithoutZeros", DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(drawTime, "US/Eastern"),true));
    }

}
