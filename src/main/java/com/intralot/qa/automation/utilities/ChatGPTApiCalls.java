package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.mashape.unirest.http.exceptions.UnirestException;
import groovy.json.StringEscapeUtils;
import io.restassured.path.json.JsonPath;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ChatGPTApiCalls {

    public ArrayList<String> tickets = new ArrayList<String>();
    public String ticket_description;
    public String ticket_summary;

    public String responseData=null;

    public String sendChatGPTQuestionGetResponse(String question,String end_question, String description) throws IOException, UnirestException {

        int maxRetries = Integer.parseInt(CustomProperties.getPropertyValue("number.of.retries.if.call.fails")); // Maximum number of retries
        for (int retry = 1; retry <= maxRetries; retry++) {
            try {

                String message=question+description.replace("\r\n","") + end_question;
                   message= message.replace("\"", "\\\"") // escape double quotes
                        .replace("/", "\\/");   // escape forward slashes

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();

                String jsonBody = "{\n" +
                        "    \"model\": \"gpt-3.5-turbo\",\n" +
                        "    \"messages\": [\n" +
                        "        {\n" +
                        "            \"role\": \"user\",\n" +
                        "            \"content\": \""+ message +"\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"temperature\": 0.7\n" +
                        "}";
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(jsonBody, mediaType);

                Request request = new Request.Builder()
                        .url(CustomProperties.getPropertyValue("chatGPT.api.url"))
                        .post(body)
                        .addHeader("Authorization", CustomProperties.getPropertyValue("chatGPT.api.authorization"))
                        .addHeader("Cookie", "__cf_bm=O54EHIS45NVdY8GNPTbhnkUavOxiJQe1R7265tGN4yw-1714057881-1.0.1.1-NNrlYGbrij0JKv8L1Bkg6lcgkuyg_ewcHCJ.ikEhVHr1cOsMFuxAXithrTdqVpA5Uiw4IVBqnX.C.CooPaimAQ; _cfuvid=N2S5sAuMlzffXYPrUJ8rvxhJvrjIb.9QZBXcasa5uqk-1713970731241-0.0.1.1-604800000")
                        .build();
                Log.info(String.valueOf(request));
                //Log.info(jsonBody);
                Response response = client.newCall(request).execute();
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // Get the response body as a string
                    String responseData = response.body().string();
                    response.close();
                    // Extract the response body as a JsonPath object
                    JsonPath jsonPath = JsonPath.given(responseData);

                    // Use JsonPath to extract the "content" field
                    String content = jsonPath.getString("choices[0].message.content");
                    Log.error(content);
                    return content;
                } else {
                    // Handle the case when the response is not successful
                    System.out.println("Error: " + response.code() + " - " + response.message());
                    System.out.println("Request URL: " + request.url());
                    // Print the request body for debugging
                    //System.out.println("Request Body: " + jsonBody);
                    // Print the response body for debugging
                    System.out.println("Response Body: " + response.body().string());
                    System.out.println("Request failed! Retrying... (Attempt " + retry + ")");
                    response.close();
                }
                // Close the response to release resources
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String createJiraTestReturnResponse(String test_case, String test_summary, String test_project, String story_id, String story_description, String test_title) throws IOException, UnirestException {

        test_case = StringEscapeUtils.escapeJava(test_case);
        story_description = StringEscapeUtils.escapeJava(story_description);
        test_summary = test_summary + " - " + test_title;
        test_summary = StringEscapeUtils.escapeJava(test_summary);


        int maxRetries = Integer.parseInt(CustomProperties.getPropertyValue("number.of.retries.if.call.fails")); // Maximum number of retries
        for (int retry = 1; retry <= maxRetries; retry++) {
            try {

                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(10, TimeUnit.SECONDS) // Set read timeout to 60 seconds
                        .build();

        // Request body
        String jsonBody = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"" + test_project + "\"\n" +
                "       },\n" +
                "       \"summary\": \"" + test_summary + "\",\n" +
                "       \"description\": \"" + story_description + "\",\n" +
                "       \"customfield_12800\": 1,\n" +
                "       \"customfield_12801\": 1,\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Test\"\n" +
                "       },\n" +
                "       \"labels\": [\n" +
                "        \"Auto_Created\",\n" +
                "        \""+ story_id + "\",\n" +
                "        \"Automation\"\n" +
                "        ] \n" +
                "    },\n" +
                "     \"update\":{\n" +
                "      \"issuelinks\":[\n" +
                "         {\n" +
                "            \"add\":{\n" +
                "               \"type\":{\n" +
                "                  \"name\":\"Relates\",\n" +
                "                  \"inward\":\"relates to\",\n" +
                "                  \"outward\":\"relates to\"\n" +
                "               },\n" +
                "               \"outwardIssue\":{\n" +
                "                  \"key\":\"" + story_id + "\"\n" +
                "               }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonBody, mediaType);

        //System.out.println("body: "+jsonBody);

        // Construct the request
        Request request = new Request.Builder()
                .url(CustomProperties.getPropertyValue("jira.api.url")+"issue")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",  CustomProperties.getPropertyValue("jira.api.authorization"))
                .build();

        // Execute the request and get the response
        Response response = client.newCall(request).execute();
        String responseKey = response.body().string();

        // Handle the response
        if (response.isSuccessful()) {
            System.out.println("Request to create ticket successful!");
            System.out.println("Response: " + responseKey);
            response.close();
            return responseKey;

        } else {
            System.out.println("Error: " + response.code() + " - " + response.message());
            System.out.println("Request failed! Retrying... (Attempt " + retry + ")");
            response.close();
        }
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public void createTestSteps(String test_case_id, String step, String result) throws IOException {
        step = StringEscapeUtils.escapeJava(step);
        result = StringEscapeUtils.escapeJava(result);
        //step = "Whatever test";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"step\": \"" + step + "\",\r\n  " +
                "\"data\": \"\",\r\n  \"result\": \"" + result + "\"\r\n}");
        Request request = new Request.Builder()
                .url(CustomProperties.getPropertyValue("zapi.api.url")+"latest/teststep/"+test_case_id)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", CustomProperties.getPropertyValue("jira.api.authorization"))
                .addHeader("Cookie", "JSESSIONID=1FCD71C35156C01C0A0F41224339E11B; atlassian.xsrf.token=BK3Y-8CZO-B01V-GHFT|dad0808f836f91e2b41dc2481bb0b11825aff95c|lin")
                .build();
        Response response = client.newCall(request).execute();
        //System.out.println("Request: " + request);

        // Handle the response
        if (response.isSuccessful()) {
            Log.info("Request to update ticket steps successful!");
            Log.info("Response: " + response);

        } else {
            Log.info("Request failed!");
            Log.info("Response: " + response.body().string());
        }
        response.close();
    }

    public void setJiraTicketDescription(String ticket_description)
    {
        tickets.add(ticket_description);
    }
    public String getJiraTicketDescription(int index)
    {
        return tickets.get(index);
    }

}

