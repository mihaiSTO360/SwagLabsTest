package org.usertests;

import org.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.pageobject.LoginPage;

public class StandardUserTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void standardUserSuccessfulLogin() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
