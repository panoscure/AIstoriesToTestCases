package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.api.lottery.HeadersQueryAndFormData;
import com.intralot.qa.automation.api.lottery.apigatewayj.drawoperations.DrawOperations;
import com.intralot.qa.automation.core.utilities.DateUtilities;
import com.intralot.qa.automation.core.utilities.Log;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Draws {

    public static String calculateEndDraw(int gameId, int startDrawId, int multidraws, boolean stripZeros){
        Log.info("calculateEndDraw() for gameId:" + gameId);
        String endDrawUSformat;
        String endDrawUSformatWithoutZeros;

        int endDrawId;
        Long endDrawTime;

        endDrawId=startDrawId+(multidraws-1);

        String responseTxt = DrawOperations.retrieveDrawInfoForRequestedGameCode(
                gameId,
                endDrawId,
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(System.getProperty("bearerToken"))).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        endDrawTime = (Long) jo.get("drawTime");

        //Log.info("End Draw Time in epoc is:" + endDrawTime);

        endDrawUSformat = DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(endDrawTime, "US/Eastern"), stripZeros);
        endDrawUSformatWithoutZeros = DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(endDrawTime, "US/Eastern"), true);
        Log.info("calculateEndDraw(): End Draw Time in US format is: " + endDrawUSformat);
        Log.info("calculateEndDraw(): End Draw Time in US format without zeros is: " + endDrawUSformatWithoutZeros);
        System.setProperty("EndDrawUSformatWithoutZeros", endDrawUSformatWithoutZeros);

        return endDrawUSformat;

    }
    public static String calculateEndDrawPlayDc5(int gameId, int startDrawId, int multidraws, boolean stripZeros){
        Log.info("calculateEndDraw() for gameId:" + gameId);
        String endDrawUSformat;
        String endDrawUSformatWithoutZeros;

        int endDrawId;
        Long endDrawTime;

        endDrawId=startDrawId+(multidraws+1);

        String responseTxt = DrawOperations.retrieveDrawInfoForRequestedGameCode(
                gameId,
                endDrawId,
                HeadersQueryAndFormData.getActiveDrawMobileHeaders(System.getProperty("bearerToken"))).body().asString();

        JSONObject jo = new JSONObject(responseTxt);
        endDrawTime = (Long) jo.get("drawTime");

        //Log.info("End Draw Time in epoc is:" + endDrawTime);

        endDrawUSformat = DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(endDrawTime, "US/Eastern"), stripZeros);
        endDrawUSformatWithoutZeros = DateUtilities.dateToUSformat(DateUtilities.epocToDateTime(endDrawTime, "US/Eastern"), true);
        Log.info("calculateEndDraw(): End Draw Time in US format is: " + endDrawUSformat);
        Log.info("calculateEndDraw(): End Draw Time in US format without zeros is: " + endDrawUSformatWithoutZeros);
        System.setProperty("EndDrawUSformatWithoutZeros", endDrawUSformatWithoutZeros);

        return endDrawUSformat;

    }

}
