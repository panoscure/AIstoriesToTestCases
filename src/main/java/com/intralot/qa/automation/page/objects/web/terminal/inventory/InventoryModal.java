package com.intralot.qa.automation.page.objects.web.terminal.inventory;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryModal {

    WebDriver webDriver;


    public InventoryModal(WebDriver webDriver) {
        this.webDriver = webDriver;
    }




    public InventoryModal selectInventory() {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//span[text()='Inventory']"));
        games.click();

        return this;
    }

    public InventoryModal selectCashToTransfer(String amount) {
        amount = amount + ",000";
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//span[text()='NT$"+ amount +"']"));
        //System.out.println("To webelement einai: "+button.get(0));
        button.click();

        return this;
    }

    public InventoryModal clickToTransfer() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//span[text()='Transfer Amount']"));
        button.click();
        return this;
    }

    public InventoryModal closePopupFailedRequest() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//a[@class='button activated']"));
        button.click();
        return this;
    }



//div[@class='total-cost']/span
public String getTransferStatusMessage() {
    WebElement amount = SeleniumWaits.visibilityOfElementLocated(webDriver,
            By.xpath("//p[@style='white-space: pre-line;']"));
    //System.out.println("To webelement einai: "+button.get(0));
    String text = amount.getText();

    return text;
}

    public InventoryModal pressPlayButtonGame5from39() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//div[text()='Play All']"));
        //System.out.println("To webelement einai: "+button.get(0));
        button.click();

        return this;
    }




}
