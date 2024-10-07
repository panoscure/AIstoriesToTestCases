package com.intralot.qa.automation.api.lottery.apigatewayj.drawoperations;

import com.intralot.qa.automation.core.utilities.CustomProperties;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_OK;

public class DrawOperations {

    public static Response grantAuthorizationToken(
            Map<String, String> headers, Map<String, String> queryData) {

        RestAssured.useRelaxedHTTPSValidation();
        return RestAssured.given().headers(headers).queryParams(queryData)
                .post(CustomProperties.getPropertyValue("apigatewayj") + "/authentication/token");
    }

    public static Response getSessionTokenSignOn(
            Map<String, String> headers, String body) {

        RestAssured.useRelaxedHTTPSValidation();
        return RestAssured.given()
                .headers(headers)
                .body(body)
                .when()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .post(CustomProperties.getPropertyValue("apigatewayj") + "/api/v1.0/authentication");

    }

    public static Object updateProfile(Map<String, String> headers, String requestBody) {

        return given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBody)
                .when()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .put(CustomProperties.getPropertyValue("apigatewayj") + "/api/v1.0/my-profile")
                .then()
                .statusCode(200);
    }

    public static Response retrieveActiveDrawForRequestedGameCode(Integer gameId, Map<String, String> headers) {

        RestAssured.useRelaxedHTTPSValidation();

        return RestAssured.given()
                .accept(JSON)
                .headers(headers)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .get(CustomProperties.getPropertyValue("apigatewayj") + "/api/v3.0/draws/{gameId}/active" , gameId);
    }

    public static Response retrieveDrawInfoForRequestedGameCode(Integer gameId, Integer drawId, Map<String, String> headers) {

        RestAssured.useRelaxedHTTPSValidation();

        return RestAssured.given()
                .accept(JSON)
                .headers(headers)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .get(CustomProperties.getPropertyValue("apigatewayj") + "/api/v3.0/draws/{gameId}/{drawId}" , gameId, drawId);
    }
}
