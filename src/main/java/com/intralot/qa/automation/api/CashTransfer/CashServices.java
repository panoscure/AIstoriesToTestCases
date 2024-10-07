package com.intralot.qa.automation.api.CashTransfer;

import com.intralot.qa.automation.core.utilities.CustomProperties;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CashServices {

    public static Object moneyTransferRequestApproval(Map<String, String> headers, String requestBody) {

        return given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBody)
                .when()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .put(CustomProperties.getPropertyValue("swagger.puint.base.url")+"/api/v1.0/pulse/tlc/money-transfers/result")
                .then()
                .statusCode(204);
    }

    public static Object resetAllLimits(String playerPamId, Map<String, String> headers, String requestBody) {

        return given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBody)
                .when()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .put(CustomProperties.getPropertyValue("pamapi") + "/api/v1.0/players/"+ playerPamId + "/limits")
                .then()
                .statusCode(200);
    }

    public static Object loginPamBOResponse(String apiUrl, Map<String, String> headers, String requestBody) {

        return given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBody)
                .when()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .post(apiUrl + "/cc/access/rest/v1/sessions");
    }

    public static Object playerDataResponseNoRedirects(String apiUrl, String path, Map<String, String> headers, Map<String, String> queryParameters) {

        return  given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .queryParams(queryParameters)
                .when()
                .redirects().follow(false)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .get(apiUrl + path);

    }

    public static Object playerDataResponseFromPreviousCallNoRedirects(String apiRedirectedUrl, Map<String, String> headers) {

        return  given()
                .relaxedHTTPSValidation()
                .urlEncodingEnabled(false)
                .headers(headers)
                .when()
                .redirects().follow(false)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .get(apiRedirectedUrl);
    }

}

