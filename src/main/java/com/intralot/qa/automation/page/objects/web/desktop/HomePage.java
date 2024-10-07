package com.intralot.qa.automation.page.objects.web.desktop;

import com.intralot.qa.automation.core.driver.engine.JavaScriptExecutors;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage clickLoginButton() {
        WebElement loginBtn = SeleniumWaits.elementToBeClickable(webDriver, By.xpath("//button[text()='Login']"));
        JavaScriptExecutors.clickElement(webDriver, loginBtn);
        return this;
    }

    public HomePage clickHeaderLogo() {
        WebElement logo = SeleniumWaits.elementToBeClickable(webDriver, By.xpath("//header//img[@title='Logo']"));
        logo.click();
        return this;
    }

    public HomePage clickUserIcon() {
        Awaitility
                .await()
                .pollInterval(2, TimeUnit.SECONDS)
                .atMost(20, TimeUnit.SECONDS)
                .until(() ->
                {
                    WebElement userIcon = SeleniumWaits.visibilityOfElementLocated(webDriver,
                            By.xpath("//div[contains(@class, 'QuickLottoMyAccountArea')]//button"));
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    js.executeScript("arguments[0].click();", userIcon);
                    WebElement myAccountBtn = SeleniumWaits.elementToBeClickable(webDriver, By.xpath("//p[text()='MY ACCOUNT']"));
                    return myAccountBtn.isDisplayed();
                });
        return this;
    }

    public BigDecimal getBalance() {
        Awaitility
                .await()
                .pollInterval(3, TimeUnit.SECONDS)
                .atMost(8, TimeUnit.SECONDS)
                .until(() ->
                {
                    WebElement balance = SeleniumWaits.visibilityOfElementLocated(webDriver,
                            By.xpath("//p[contains(text(), 'BALANCE')]//following-sibling::p/span"));
                    return balance.isDisplayed();
                });
        WebElement balance = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//p[contains(text(), 'BALANCE')]//following-sibling::p/span"));
        String balanceAsString = balance.getText();
        String balanceWithoutSymbols = balanceAsString.substring(1).replace(",", "");
        BigDecimal balanceAsBigDecimal = new BigDecimal(balanceWithoutSymbols);
        return balanceAsBigDecimal;
    }

    public HomePage clickLinkInTopMenu(String linkName) {
        WebElement linkInTopMenu = SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("//div[@class='treeLinkHorizontal']//span[text()='" + linkName + "']"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", linkInTopMenu);

        return this;
    }

    public HomePage clickTicketCheckerInTopMenu() {
        WebElement link = SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("//div[@class='treeLinkHorizontal']//span/a[text()='TICKET CHECKER']"));
        link.click();
        return this;
    }

    public HomePage clickCartButton() {
        WebElement cartBtn = SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("//div[@class='cart-indicator']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", cartBtn);
        return this;
    }

    public String numberOfGameInCart() {
        WebElement gamesInCart = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//div[@class='cart-indicator']//span[@class='cart-circle']"));
        return gamesInCart.getText();
    }

    public void clickGameFromCarousel(String gameName) {
        JavaScriptExecutors.clickElementBy(webDriver,
                By.xpath("(//div[./h3[text()='" + gameName + "']]//button[text()='Play Now'])[1]"));
    }

    public String getCashBalance() {
        Awaitility
                .await()
                .pollInterval(3, TimeUnit.SECONDS)
                .atMost(8, TimeUnit.SECONDS)
                .until(() ->
                {
                    WebElement balance = SeleniumWaits.visibilityOfElementLocated(webDriver,
                            By.xpath("//p[contains(text(), 'Cash Balance')]"));
                    return balance.isDisplayed();
                });
        WebElement balance = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//p[contains(text(), 'Cash Balance')]"));


        String balanceAsString = balance.getText();
        String balanceWithoutSymbols = balanceAsString.substring(1).replace(",", "");
        //BigDecimal balanceAsBigDecimal = new BigDecimal(balanceWithoutSymbols);
        return (balanceWithoutSymbols);
    }

    public HomePage clickHideBalanceToggle() {
        Awaitility
                .await()
                .pollInterval(2, TimeUnit.SECONDS)
                .atMost(20, TimeUnit.SECONDS)
                .until(() ->
                {
                    WebElement balanceToggle = SeleniumWaits.visibilityOfElementLocated(webDriver,
                            By.xpath("//span[@class='MuiSwitch-thumb css-19gndve']"));
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    js.executeScript("arguments[0].click();", balanceToggle);
                    //WebElement myAccountBtn = SeleniumWaits.elementToBeClickable(webDriver, By.xpath("//p[text()='MY ACCOUNT']"));
                    return balanceToggle.isDisplayed();
                });
        return this;
    }

    public HomePage clickOKToVerifyNowButton() {
        SeleniumWaits.elementToBeClickable(webDriver, By.xpath("//button[text()='Verify now']")).click();
        return this;
    }

}
