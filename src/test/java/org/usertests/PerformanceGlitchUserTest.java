package org.usertests;

import com.github.javafaker.Faker;
import org.BaseTest;
import org.pageobject.CheckoutPage;
import org.pageobject.LoginPage;
import org.pageobject.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public void noPostalCodeAtCheckoutTest() {
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

    @Test
    public void addingToCartAndRemovingAllProductsTest() {
        loginPage.performanceLogin();
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
    public void openEveryItemPageTest() {
        loginPage.performanceLogin();
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

    @Test
    public void continueShoppingButtonTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.clickOnContinueShoppingButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void aboutPageTest() {
        loginPage.performanceLogin();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnAboutPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");

    }

    @Test
    public void logOutTest() {
        loginPage.performanceLogin();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnLogoutPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

    }

    @Test
    public void allItemsButtonTest() {
        loginPage.performanceLogin();
        mainPage.openBackpackItemPage();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnAllItemsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void resetAppStateTest() {
        loginPage.performanceLogin();
        mainPage.addBackpackToCart();
        mainPage.clickOnBurgerMenuButton();
        mainPage.clickOnResetAppState();
        mainPage.clickOnShoppingCartSelector();
        checkoutPage.getCheckoutItemsNumber();
        Assert.assertEquals(checkoutPage.checkoutItemCount, 0);
    }

    @Test
    public void lowHighPriceFilterTest() {
        loginPage.performanceLogin();
        mainPage.clickOnFilterMenu();
        mainPage.filterMenu("lohi");
        List<Double> actualPrices = mainPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices, "Prețurile sunt sortate crescător!");

    }

    @Test
    public void highLowPriceFilterTest() {
        loginPage.performanceLogin();
        mainPage.clickOnFilterMenu();
        mainPage.filterMenu("hilo");
        List<Double> actualPrices = mainPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());
        Assert.assertEquals(actualPrices, expectedPrices, "Prețurile sunt sortate descrescător!");
    }

}
