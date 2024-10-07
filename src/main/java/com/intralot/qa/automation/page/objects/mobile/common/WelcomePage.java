package com.intralot.qa.automation.page.objects.mobile.common;

public interface WelcomePage {

    void clickNextStepBtn();

    void clickTakeMeHomeBtn();
    void clickCloseIcon();

    void skipWelcomePage();

    void loginFirstTimeAs(String username, String password);
    void clickXIcon();
    void verifyUserLocked();
}
