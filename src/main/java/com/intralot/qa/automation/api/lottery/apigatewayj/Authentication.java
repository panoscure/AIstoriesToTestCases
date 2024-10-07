package com.intralot.qa.automation.api.lottery.apigatewayj;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class Authentication {

  // Grant authorization token - POST /authentication/token
  public static Response grantAuthorizationToken(
      Map<String, String> headers, Map<String, String> queryData) {

    RestAssured.useRelaxedHTTPSValidation();
    return RestAssured.given().headers(headers).queryParams(queryData)
            .post(CustomProperties.getPropertyValue("apigatewayj") + "/authentication/token");
  }

  // Grant authorization token - POST​/api/v1.0/terminal-actions/signon
  public static String grantAuthorizationTokenTerminalSignOnExtractToken(
      Map<String, String> headers, String authBody) {
    return given()
        .relaxedHTTPSValidation()
        .headers(headers)
        .body(authBody)
        .when()
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
        .post(
            CustomProperties.getPropertyValue("apigatewayj") + "/api/v1.0/terminal-actions/signon")
        .then()
        .statusCode(HTTP_OK)
        .extract()
        .path("access_token");
  }

  public static Object validateTheGivenToken(
      Map<String, String> headers, Map<String, String> queryParams) {
    return given()
        .relaxedHTTPSValidation()
        .headers(headers)
        .queryParams(queryParams)
        .when()
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
        .get(CustomProperties.getPropertyValue("apigatewayj") + "/authentication/check_token");
  }
}
