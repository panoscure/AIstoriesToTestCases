package com.intralot.qa.automation.base.test;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

import static com.intralot.qa.automation.core.utilities.CustomProperties.loadPropertiesFile;

public class BaseSetupForApi {

    @BeforeSuite
    public void initApiSetup(ITestContext iTestContext) throws Exception {

        // Load properties' values
        loadPropertiesFile("src/test/resources/common.properties");
    }
}
