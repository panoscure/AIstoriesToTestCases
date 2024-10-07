package com.intralot.qa.automation.page.objects.web.desktop.chatGPT;

import com.google.common.truth.Truth;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import com.intralot.qa.automation.core.lottery.apigatewayj.models.games.getgamerecord.Generic;
import com.intralot.qa.automation.core.utilities.Log;
import net.bytebuddy.asm.Advice;
import org.awaitility.Awaitility;
import org.openqa.selenium.*;

import javax.mail.search.SentDateTerm;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainPage {

    WebDriver webDriver;

    public static By login_button = By.xpath("//div[text()='Log in']");
    public static By checkbox = By.xpath("<input type=\"checkbox\">");

    public static By message_box = By.id("prompt-textarea");

    public static By submit_chat = By.xpath("//button[@data-testid='fruitjuice-send-button']");
    public static By chatGPT_response = By.xpath("(//div[@class='flex flex-grow flex-col max-w-full'])[last()]");
    public static By find_end_text = By.xpath("//p[text()='Simon Says: Response Ends Here']");




    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public int stored_balance;

    public MainPage login() {
        clickLogin();
        clickCheckbox();
        return this;
    }

    public MainPage clickLogin() {
            WebElement logo = SeleniumWaits.elementToBeClickable(webDriver, login_button);
            logo.click();
        return this;
    }

    public MainPage clickMessageBox() {
        WebElement logo = SeleniumWaits.elementToBeClickable(webDriver, message_box);
        logo.click();
        return this;
    }

    public MainPage sendText(String question,String end_question, String description) throws InterruptedException, UnsupportedEncodingException {
        WebElement question_element = SeleniumWaits.elementToBeClickable(webDriver, message_box);
        String message=question+description.replace("\r","") + end_question;
        message = message.replace("\n","");
        //message = message.replace("•","").trim();
        Log.info(message);
        Thread.sleep(5000);
        //question_element.sendKeys(message);


        byte[] utf8Bytes = message.getBytes("UTF-8");

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value = arguments[1]", question_element, new String(utf8Bytes));

        Thread.sleep(5000);
        question_element.sendKeys("  ");
        WebElement submit = SeleniumWaits.elementToBeClickable(webDriver,submit_chat);
        submit.click();
        return this;
    }

    public String getChatGPTResponse() throws InterruptedException {
        Awaitility.await().pollInterval(2, TimeUnit.SECONDS).atMost(4, TimeUnit.MINUTES).until(() -> {
            List<WebElement> findEndText = webDriver.findElements(find_end_text);
            return findEndText.size()>0;
        });
        Thread.sleep(4000);

        WebElement response = SeleniumWaits.visibilityOfElementLocated(webDriver,
                chatGPT_response);
        return response.getText();
    }


    public MainPage clickCheckbox() {
        WebElement logo = SeleniumWaits.elementToBeClickable(webDriver, checkbox);
        logo.click();
        return this;
    }






}
