package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.core.utilities.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCaseSeparation {

    String test_case_description = "";
    // Store test steps and results
    ArrayList<String> testSteps = new ArrayList<>();
    ArrayList<String> testResults = new ArrayList<>();

    ArrayList test_case = new ArrayList<>();
    ArrayList test_title = new ArrayList<>();


    public void seperateTC(String all_tests) {

        String input= all_tests;

        // Create an ArrayList to store the separated test cases
        ArrayList<String> TC = new ArrayList<>();
/*
        // Split the input string based on the "Test Case" pattern and add them to the ArrayList
        String[] parts = input.split("Test Case \\d+:");
        for (String part : parts) {
            // Remove leading and trailing whitespace and add to the ArrayList
            TC.add(part.trim());
            setTestCase(part.trim());
        }
*/
        // Define the pattern for matching test case strings
        Pattern patternTC = Pattern.compile("Test Case \\d+:.*?((?=Test Case)|$)", Pattern.DOTALL);
        Matcher matcherTC = patternTC.matcher(input);

        // Extract and add test cases to the list
        while (matcherTC.find()) {
            String testCase = matcherTC.group().trim(); // Trim to remove leading and trailing whitespace
            TC.add(testCase);
            setTestCase(testCase); // Call your method if necessary
        }

        String patternString = "Test Case \\d+: (.+?)\\n";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        // Iterate through each test case and extract the corresponding text
        while (matcher.find()) {
            String testTitleText = matcher.group(1);
            setTestTitle(testTitleText);
            Log.info("Test Title Text: "+testTitleText);
        }

    }

    public void seperateTestSteps(String all_steps) {

        String testCaseInput = all_steps;


        // Regular expressions for matching test steps and test results
        String testStepPattern = "Test Step \\d+: (.+)";
        String testResultPattern = "Test Result \\d+: (.+)";

        // Create pattern objects
        Pattern stepPattern = Pattern.compile(testStepPattern);
        Pattern resultPattern = Pattern.compile(testResultPattern);

        // Create matcher objects
        Matcher stepMatcher = stepPattern.matcher(testCaseInput);
        Matcher resultMatcher = resultPattern.matcher(testCaseInput);

        Log.info("Test Steps: "+testCaseInput);

        // Match and print test steps
        while (stepMatcher.find()) {
            String step = stepMatcher.group(1).trim(); // Trim to remove leading/trailing spaces
            if (!step.isEmpty()) {
                step = step;
                Log.info("Step Found: "+step);
            }
            else{
                step="No Data";
            }
            setTestStep(step);
            //System.out.println("*Test Step*: " + step);
        }
        // Match and print test results
        while (resultMatcher.find()) {
            String result = resultMatcher.group(1).trim(); // Trim to remove leading/trailing spaces
            if (!result.isEmpty()) {
                result = result;
                //System.out.println(result);
            }
            else{
                result="No Data";
            }
            setTestResult(result);
            //System.out.println("*Test Result*: " + result);
        }


    }


    public void setTestCase(String testCase){
        test_case.add(testCase);
        Log.info("NEW TEST CASE: " + testCase);
    }
    public String getTestCaseOneByOne(int index){return (String) test_case.get(index);}
    public ArrayList getTestCaseList(){return test_case;}

    public void setTestTitle(String title){test_title.add(title);}
    public String getTestTitle(int index){return (String) test_title.get(index);}


    public void setTestCaseDescription(String testCaseDescription){test_case_description = testCaseDescription;}
    public String getTestCaseDescription(){return test_case_description;}

    public void setTestStep(String testCase){testSteps.add(testCase);}
    public String getTestStepOneByOne(int index){return (String) testSteps.get(index);}
    public ArrayList getTestStepList(){return testSteps;}

    public void setTestResult(String testCase){test_case.add(testCase);}
    public String getTestResultOneByOne(int index){return (String) test_case.get(index);}
    public ArrayList getTestResultList(){return test_case;}
}

