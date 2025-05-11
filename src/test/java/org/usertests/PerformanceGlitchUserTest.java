package org.usertests;

import org.BaseTest;
import org.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PerformanceGlitchUserTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void performanceGlitchUserSuccessfulLogin() {
        loginPage.performanceLogin();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void performanceGlitchUserNoPasswordLogin() {
        loginPage.fillUsernameField("performance_glitch_user");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");

    }


}
