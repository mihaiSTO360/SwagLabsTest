package org.usertests;

import org.BaseTest;
import org.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProblemUserTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void problemUserSuccessfulLogin() {
        loginPage.fillUsernameField("problem_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }
}
