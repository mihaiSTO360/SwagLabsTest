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

public class    ProblemUserTest extends BaseTest {
    LoginPage loginPage;
    MainPage mainPage;
    CheckoutPage checkoutPage;
    Faker faker;

    @BeforeMethod
    public void setUpPages() {
        faker = new Faker();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void problemUserSuccessfulLogin() {
        loginPage.problemLogin();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void problemUserNoPasswordLogin() {
        loginPage.fillUsernameField("problem_user");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void problemUserRandomPasswordLogin() {
        loginPage.fillUsernameField("problem_user");
        String password = faker.internet().password();
        loginPage.fillPasswordField(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void continueShoppingButtonTest() {
        loginPage.problemLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnContinueShoppingButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void resetAppStateTest() {
        loginPage.problemLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnResetAppState();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.getCheckoutItemsNumber();
        Assert.assertEquals(checkoutPage.checkoutItemCount, 0);
    }

    @Test
    public void logOutTest() {
        loginPage.problemLogin();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnLogoutPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

    }

    @Test
    public void allItemsButtonTest() {
        loginPage.problemLogin();
        mainPage.openBackpackItemPage();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnAllItemsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void socialMediaTwitterButtonTest() {
        loginPage.problemLogin();
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
        loginPage.problemLogin();
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
        loginPage.problemLogin();
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
