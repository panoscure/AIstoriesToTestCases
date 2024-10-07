package com.intralot.qa.automation.page.objects.web.desktop.operationsUI;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class OperationsUIModal {

    WebDriver webDriver;


    public OperationsUIModal(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public OperationsUIModal operationsLogin(String username,String password) {
        enterOperationsUsername(username);
        enterOperationsPassword(password);
        clickLoginButton();
        return this;
    }

    public OperationsUIModal enterOperationsUsername(String username) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//input[@id='username']"));
        games.sendKeys(username);
        return this;
    }



    public OperationsUIModal clickLoginButton() {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//button[@id='btnLogin']"));
        games.click();
        return this;
    }

    public OperationsUIModal enterOperationsPassword(String password) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//input[@id='password']"));
        games.sendKeys(password);
        return this;
    }

    public OperationsUIModal selectMoneyTransferTab() {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//span[text()='Money Transfers']"));
        games.click();
        return this;
    }

    public String getTransactionID() {
        WebElement delete = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//i[@title='Delete']"));
        delete.click();
        WebElement amount = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//div[@class='modal-body']"));
        String text = amount.getText();
        text = text.substring(text.length() - 5);
        text = text.replace("?","");
        return text;
    }


    public OperationsUIModal closeTransactionIDpopup() {
        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                //By.xpath("//span[text()='×']"));
        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].click();", button);
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }






}
