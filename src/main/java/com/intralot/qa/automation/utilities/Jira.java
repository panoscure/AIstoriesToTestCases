package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.path.json.JsonPath;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class Jira {

    public ArrayList<String> tickets = new ArrayList<String>();
    public String ticket_description;
    public String ticket_summary;
    public String ticket_project;
    public String test_case_key;
    public String test_case_id;
    public String test_case_keys_seperated_with_comma;


    JiraApiCalls jira_api = new JiraApiCalls();
    TestCaseSeparation TCSeparation = new TestCaseSeparation();


    public void loginAndGetJiraTicketDetailsPerTicket(String ticket) throws IOException, UnirestException {

            String responseData = jira_api.callJiraGetStoryDetailsReturnResponse(ticket);
            // Parse the JSON using JsonPath
            JsonPath jsonPath = new JsonPath(responseData);

            // Extract the value of the 'description' field
            String description = jsonPath.getString("issues[0].fields.description");
            String summary = jsonPath.getString("issues[0].fields.summary");
            String project_key = jsonPath.getString("issues[0].fields.project.key");
            setJiraTicketProjectPerTicket(project_key);
            setJiraTicketDescriptionPerTicket(description);
            setJiraTicketSummaryPerTicket(summary);
            //Log.info(description);
    }

    public void  createTestCase(String test_case, String test_summary, String test_project, String story_id, String story_description) throws IOException, UnirestException, InterruptedException {
        TCSeparation.seperateTC(test_case);
        Log.info("Number of test cases: " + TCSeparation.getTestCaseList().size());
        try{
        for (int i = 0; i < TCSeparation.getTestCaseList().size(); i++) {
            String test = TCSeparation.getTestCaseOneByOne(i);
            String test_title = TCSeparation.getTestTitle(i);
            Thread.sleep(2000);
            String responseData = jira_api.createJiraTestReturnResponse(test, test_summary, test_project, story_id, story_description, test_title);
            // Parse the JSON using JsonPath
            JsonPath jsonPath = new JsonPath(responseData);

            // Extract the value of the 'description' field
            String test_case_key = jsonPath.getString("key");
            Log.info("Test Case Key: " + test_case_key);
            setJiraTestCaseKey(test_case_key);
            String test_case_id = jsonPath.getString("id");
            Log.info("Test Case id: " + test_case_id);
            setJiraTestCaseid(test_case_id);

            Log.warn("Test Case" + i + ": " + test);
            Log.info("end of test case");

            TestCaseSeparation testStepSeparation = new TestCaseSeparation();
            testStepSeparation.seperateTestSteps(test);
            Log.info("Number of test Steps: " + testStepSeparation.getTestStepList().size());
            try {
                for (int j = 0; j < testStepSeparation.getTestStepList().size(); j++) {
                    String test_step_description = testStepSeparation.getTestStepOneByOne(j);

                    String expected_result = testStepSeparation.getTestResultOneByOne(j);
                    //Log.info("Test step description: " + test_step_description);
                    //Log.info("End of test step description");
                    //Log.info("Test step result: " + expected_result);
                    //Log.info("End of test step result");
                    Thread.sleep(3000);
                    jira_api.createTestSteps(test_case_id, test_step_description, expected_result);
                }
            } catch (IOException e) {
                Log.error("Exception when Creating Test steps");
                e.printStackTrace();
            }
            Log.info("******************************");

        }
    }       catch (IOException e) {
            Log.error("Exception when Creating Test Cases");
            e.printStackTrace();
        }
    }





    public void setJiraTicketDescription(String ticket_description)
    {
        tickets.add(ticket_description);
    }
    public String getJiraTicketDescription(int index)
    {
        return tickets.get(index);
    }

    public void setJiraTicketDescriptionPerTicket(String ticket_descr)
    {
        ticket_description = ticket_descr;
    }
    public String getJiraTicketDescriptionPerTicket()
    {
        return ticket_description;
    }

    public void setJiraTicketSummaryPerTicket(String ticket_sum)
    {
        ticket_summary = ticket_sum;
    }
    public String getJiraTicketSummaryPerTicket()
    {
        return ticket_summary;
    }
    public void setJiraTicketProjectPerTicket(String project)
    {
        ticket_project = project;
    }
    public String getJiraTicketProjectPerTicket()
    {
        return ticket_project;
    }

    public void setJiraTestCaseKey(String key)
    {
        test_case_key = key;
        setJiraTestCasesKeyOneString(key);
    }
    public void setJiraTestCasesKeyOneString(String key) {
        if (test_case_keys_seperated_with_comma != null) {
            test_case_keys_seperated_with_comma = test_case_keys_seperated_with_comma + "," + key;
        }
        else{
            test_case_keys_seperated_with_comma = key;
        }
    }
    public String getJiraTestCasesKeys()
    {
        return test_case_keys_seperated_with_comma;
    }

    public void setJiraTestCaseid(String id)
    {
        test_case_id = id;
    }
    public String getJiraTestCaseid()
    {
        return test_case_id;
    }


}

