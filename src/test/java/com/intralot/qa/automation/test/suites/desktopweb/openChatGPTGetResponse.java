package com.intralot.qa.automation.test.suites.desktopweb;

import com.intralot.qa.automation.base.test.BaseSetupForApplication;
import com.intralot.qa.automation.core.driver.engine.DriverFactory;
import com.intralot.qa.automation.core.jira.Store_ticket_status_list;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.web.desktop.chatGPT.MainPage;
import com.intralot.qa.automation.utilities.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class openChatGPTGetResponse extends BaseSetupForApplication {


    String question =
            "You are a Quality Assurance engineer and you are " +
            "requested to create test cases for a Business user story. " +
            "Create test cases and negative test cases and boundary test cases "+
            ",create more test cases than a lot of steps, to fully cover "+
            "the testing of the business user story, With test steps and expected results." +
            "Always start test cases and steps from number 1 and the steps with bullet,"+
            "each test step and result must be in the same line. Dont use /n or <br> " +
            "please always start test cases and steps from number 1 "+
            "and the steps with bullet, each test step and result must"+
            "be in the same line dont use /n or n or <br>, form must be " +
            "-Test Case 1: description of test case 1 "+
            "-> Test Step 1: description for test step 1 -> Test Result 1: Result for for step 1 -> " +
            "Test Step 2: description for test step 2 -> Test Result 2: Result for for step 2"+
            "-> Test Case 2: description of test case 2 etc- " +
            "Please always have Test Case 1 and not New Test Case"+
            "do not miss any requirement and also include any tables or arrays that might exist,"+
            "Please when you finish with your response add at the end Simon Says: Response Ends Here" +
            "for everything that is written on the following story that starts here - ";

    String end_question = " - Story ends here";
    String test_cases_string ="";
    @BeforeMethod(alwaysRun = true)
    public void initializeWebDriver() {
        //webDriver = DriverFactory.initializeAndGetChromeDriverBoni(CustomProperties.getPropertyValue("chatGPT.resolution"),true);
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(new String[]{"--no-sandbox"});
        chromeOptions.addArguments(new String[]{"--disable-dev-shm-usage"});
//        chromeOptions.addArguments(new String[]{"--remote-debugging-port=9222"});
        chromeOptions.addArguments(new String[]{"--start-maximized"});
        chromeOptions.addArguments(new String[]{"--ignore-certificate-errors"});
        chromeOptions.addArguments(new String[]{"disable-infobars"});
        chromeOptions.addArguments(new String[]{"--disable-extensions"});
        chromeOptions.addArguments(new String[]{"--remote-allow-origins=*"});
        chromeOptions.addArguments(new String[]{"--disable-web-security"});
        chromeOptions.addArguments(new String[]{"user-data-dir=C:\\Users\\maurogiannopoulos\\AppData\\Local\\Google\\Chrome\\User Data"});
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Step("Jira Update")
    @Test(priority=1, description = "Jira Update")
    public void ChatGPT() throws InterruptedException, IOException, UnirestException {

        MainPage main = new MainPage(webDriver);
        ReadXLS xls = new ReadXLS();
        Jira jira = new Jira();
        ChatGPTApiCalls apiChatGPT = new ChatGPTApiCalls();




        /******************************Get all ids from XLS and store them to an array******************************/
        xls.readAndStoreIssuesFromXLS();
        Log.info("Number of tickets in xls: "+xls.getJiraTickets().size());
        for(int i=0;i<xls.getJiraTickets().size();i++) {
            Jira jira_tc = new Jira();

            Log.info("Id of the ticket in xls: "+xls.getJiraTicketFromXls(i));
            /******************************Get Jira story id and retrieve the description and summary******************************/
            jira.loginAndGetJiraTicketDetailsPerTicket(xls.getJiraTicketFromXls(i));    //provide the story id and store the description and Summary

            if(CustomProperties.getPropertyValue("chatGPT.type.selection").equals("UI")) {
                /******************************Open URL ******************************/
                webDriver.get(CustomProperties.getPropertyValue("chatGPT.url"));
                /******************************Request from chatGPT the test cases******************************/
                main.sendText(question, end_question, jira.getJiraTicketDescriptionPerTicket());
                /******************************Wait ChatGPT for the response******************************/
                test_cases_string = main.getChatGPTResponse();
            }
            else{
                Log.info("API Version");
                test_cases_string = apiChatGPT.sendChatGPTQuestionGetResponse(question, end_question, jira.getJiraTicketDescriptionPerTicket());
            }

            /******************************Create test cases based on the story provided******************************/
            jira_tc.createTestCase(test_cases_string, jira.getJiraTicketSummaryPerTicket(), jira.getJiraTicketProjectPerTicket(), xls.getJiraTicketFromXls(i), jira.getJiraTicketDescriptionPerTicket());
            /******************************Update xlsx with the story and the test cases created******************************/
            xls.updateXlsx(xls.getJiraTicketFromXls(i), jira_tc.getJiraTestCasesKeys(), i);


        }
    }

    @AfterMethod
    public void updateJiraTc(ITestResult result) {
        webDriver.close();
        Log.info("Jira Status: " + CustomProperties.getPropertyValue("jira.enable.status"));
        if(CustomProperties.getPropertyValue("jira.enable.status").equals("1"))
        {
            System.out.println("Will update jira");
        Store_ticket_status_list.update_tc_status_full_flow(result,
                CustomProperties.getPropertyValue("jira.cycle.id"),
                CustomProperties.getPropertyValue("jira.cycle.folder.name"));
        }
    }

}
