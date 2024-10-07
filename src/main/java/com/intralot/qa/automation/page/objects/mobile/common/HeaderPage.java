package com.intralot.qa.automation.page.objects.mobile.common;

public interface HeaderPage {

    void clickLoginBtn();
    void clickBurgerMenuBtn();

    void verifyHeaderPageStruct();
    void verifyUserLoggedIn();
    void verifyUserNotLoggedIn();
}
