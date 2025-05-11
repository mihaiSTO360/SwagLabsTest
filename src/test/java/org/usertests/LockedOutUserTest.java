package org.usertests;

import com.github.javafaker.Faker;
import org.BaseTest;
import org.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LockedOutUserTest extends BaseTest {
    LoginPage loginPage;
    Faker faker;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        faker = new Faker();

    }

    @Test
    public void lockedOutUserSuccessfulLogin() {
        loginPage.lockedOutLogin();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");

    }

    @Test
    public void lockedOutUserNoPasswordLogin() {
        loginPage.fillUsernameField("locked_out_user");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void lockedOutUserRandomPasswordLogin() {
        loginPage.fillUsernameField("locked_out_user");
        String password = faker.internet().password();
        loginPage.fillPasswordField(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
}
