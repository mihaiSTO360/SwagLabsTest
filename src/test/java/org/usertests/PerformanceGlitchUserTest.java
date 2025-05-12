package org.usertests;

import org.BaseTest;
import org.pageobject.LoginPage;
import org.pageobject.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class PerformanceGlitchUserTest extends BaseTest {
    LoginPage loginPage;
    MainPage mainPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
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

    @Test
    public void socialMediaTwitterButtonTest() {
        loginPage.performanceLogin();
        String originalWindow = driver.getWindowHandle();
        mainPage.clickOnTwitterButton();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://x.com/saucelabs");
    }

    @Test
    public void socialMediaFacebookButtonTest() {
        loginPage.performanceLogin();
        String originalWindow = driver.getWindowHandle();
        mainPage.clickOnFacebookButtonSelector();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
    }

    @Test
    public void socialMediaLinkedinButtonTest() {
        loginPage.performanceLogin();
        String originalWindow = driver.getWindowHandle();
        mainPage.clickOnLinkedinButtonSelector();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }


}
