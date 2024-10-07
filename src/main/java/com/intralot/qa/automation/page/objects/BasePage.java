package com.intralot.qa.automation.page.objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver webDriver;

    // Constructor
    public BasePage(WebDriver webDriver) {

        this.webDriver = webDriver;

        if (webDriver instanceof AppiumDriver)
            PageFactory.initElements(new AppiumFieldDecorator(webDriver), this);
        else
            PageFactory.initElements(webDriver, this);
    }
}
