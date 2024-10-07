package com.intralot.qa.automation.page.objects.web.common;

public interface WebGlobalHomePage {

    void navigateToBaseUrl();

    void clickLoginHomePageHeader();

    void typeUsername(String uname);

    void typePassword(String pwd);

    void clickLoginButton();

    void verifyUserLoggedIn();
}
