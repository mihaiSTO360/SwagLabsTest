package org.usertests;

import org.BaseTest;
import org.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LockedOutUserTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void lockedOutUserSuccessfulLogin() {
        loginPage.fillUsernameField("locked_out_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");

    }
}
