package com.intralot.qa.automation.page.objects.web.terminal.mainPageItems;

import com.google.common.truth.Truth;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import com.intralot.qa.automation.core.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPageStaticItems {

    WebDriver webDriver;

    public By credit_balance = By.id("credit_balance");





    public MainPageStaticItems(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public int stored_balance;
    public void storeBalance(){
        stored_balance = getBalance();
    }

    public Integer getBalance() {
        WebElement amount = SeleniumWaits.visibilityOfElementLocated(webDriver,
                credit_balance);
        String text = amount.getText();
        text = text.trim().replace(",","").replace("$","");

        return Integer.parseInt(text);
    }

    public void clickReportingTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.id("nav_main_reporting"));
        button.click();
    }

    public void assertBalanceAmount(Integer ticket_amount) {
        Integer updated_balance_amount = getBalance() + ticket_amount;
        Log.info("previous balance"+stored_balance + "New Balance: " + updated_balance_amount);
        Truth.assertThat(stored_balance).isEqualTo(updated_balance_amount); //assert that balance reduced
    }






}
