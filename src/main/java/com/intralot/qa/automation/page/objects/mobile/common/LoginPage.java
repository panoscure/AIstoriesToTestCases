package com.intralot.qa.automation.page.objects.mobile.common;

public interface LoginPage {

    void typeUsername(String username);
    void typePassword(String password);
    void clickLoginBtn();
    void clickRegisterBtn();
    void verifyLoginPopupIsVisible();
    void loginUser(String username, String password);
}
