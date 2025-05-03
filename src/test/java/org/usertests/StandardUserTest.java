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

public class StandardUserTest extends BaseTest {
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
    public void addingToCartAndRemovingAllProductsTest() {
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
        checkoutPage.getCheckoutItemsNumber();
        Assert.assertEquals(checkoutPage.checkoutItemCount, 6);
        checkoutPage.removeItemsFromCheckout();
        checkoutPage.getCheckoutItemsNumber();
        Assert.assertEquals(checkoutPage.checkoutItemCount, 0);
    }

    @Test
    public void placingAnOrderTest() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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
    public void noFirstNameAtChecout() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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
    public void noLastNameAtChecout() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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
    public void noPostalCodeAtChecout() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
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

    @Test
    public void openEveryItemPage() {
        loginPage.fillUsernameField("standard_user");
        loginPage.fillPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
        mainPage.openBackpackItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=4");
        mainPage.clickOnBackToProductsButton();
        mainPage.openBikeLightItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=0");
        mainPage.clickOnBackToProductsButton();
        mainPage.openBoltTShirtItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=1");
        mainPage.clickOnBackToProductsButton();
        mainPage.openFleeceJacketItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=5");
        mainPage.clickOnBackToProductsButton();
        mainPage.openOnesieItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=2");
        mainPage.clickOnBackToProductsButton();
        mainPage.openRedTShirtItemPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=3");
    }
}


