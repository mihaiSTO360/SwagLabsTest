package org.usertests;

import org.BaseTest;
import org.pageobject.CheckoutPage;
import org.pageobject.LoginPage;
import org.pageobject.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class StandardUserTest extends BaseTest {
    LoginPage loginPage;
    MainPage mainPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void standardUserSuccessfulLogin() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void socialMediaTwitterButtonTest() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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

    @Test
    public void addingAllProductsToCartTest() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        mainPage.addBackpackToCart();
        mainPage.addBikeLightToCart();
        mainPage.addBoltTShirtToCart();
        mainPage.addFleeceJacketToCart();
        mainPage.addOnesieToCart();
        mainPage.addRedTShirtToCart();
        mainPage.clickOnShoppingCartSelector();
        int expectedCount = 6;
        checkoutPage.getCheckoutItemsNumber();
        Assert.assertEquals(checkoutPage.checkoutItemCount, expectedCount);
    }
}


