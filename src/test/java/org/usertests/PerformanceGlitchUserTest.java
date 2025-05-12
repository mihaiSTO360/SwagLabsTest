package org.usertests;

import com.github.javafaker.Faker;
import org.BaseTest;
import org.pageobject.CheckoutPage;
import org.pageobject.LoginPage;
import org.pageobject.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class PerformanceGlitchUserTest extends BaseTest {
    LoginPage loginPage;
    MainPage mainPage;
    CheckoutPage checkoutPage;
    Faker faker;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        checkoutPage = new CheckoutPage(driver);
        faker = new Faker();
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

    @Test
    public void placingAnOrderTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnCheckoutButtonSelector();
        String firstname = faker.name().firstName();
        checkoutPage.fillInFirstNameField(firstname);
        String lastname = faker.name().lastName();
        checkoutPage.fillInLastNameField(lastname);
        String zipcode = faker.address().zipCode();
        checkoutPage.fillInZipCodeField(zipcode);
        checkoutPage.clickOnContinueCheckout();
        checkoutPage.clickOnFinishCheckout();
        Assert.assertEquals(checkoutPage.orderPlacedSuccessfullyMessage(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @Test
    public void noFirstNameAtCheckoutTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnCheckoutButtonSelector();
        String lastname = faker.name().lastName();
        checkoutPage.fillInLastNameField(lastname);
        String zipcode = faker.address().zipCode();
        checkoutPage.fillInZipCodeField(zipcode);
        checkoutPage.clickOnContinueCheckout();
        Assert.assertEquals(checkoutPage.checkoutFillInErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void noLastNameAtCheckoutTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnCheckoutButtonSelector();
        String firstname = faker.name().firstName();
        checkoutPage.fillInFirstNameField(firstname);
        String zipcode = faker.address().zipCode();
        checkoutPage.fillInZipCodeField(zipcode);
        checkoutPage.clickOnContinueCheckout();
        Assert.assertEquals(checkoutPage.checkoutFillInErrorMessage(), "Error: Last Name is required");
    }

    @Test
    public void noPostalCodeAtChekcoutTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnCheckoutButtonSelector();
        String firstname = faker.name().firstName();
        checkoutPage.fillInFirstNameField(firstname);
        String lastname = faker.name().lastName();
        checkoutPage.fillInLastNameField(lastname);
        checkoutPage.clickOnContinueCheckout();
        Assert.assertEquals(checkoutPage.checkoutFillInErrorMessage(), "Error: Postal Code is required");
    }

}
