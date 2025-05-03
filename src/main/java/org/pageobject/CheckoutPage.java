package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckoutPage extends LoginPage {

    public final By continueShoppingButtonSelector = By.cssSelector("continue-shopping");
    public final By checkoutButtonSelector = By.cssSelector("button[id='checkout']");
    public final By removeItemButtonSelector = By.xpath("//button[contains(text(), 'Remove')]");
    public final By firstNameFieldSelector = By.cssSelector("input[placeholder='First Name']");
    public final By lastNameFieldSelector = By.cssSelector("input[placeholder='Last Name']");
    public final By zipCodeFieldSelector = By.cssSelector("input[placeholder='Zip/Postal Code']");
    public final By continueCheckoutSelector = By.cssSelector("input[id='continue']");
    public final By finishCheckoutSelector = By.cssSelector("button[id='finish']");
    public final By confirmationOrderPlacedMessageSelector = By.cssSelector("div.complete-text");
    public final By CheckoutErrorMessageSelector = By.cssSelector("h3[data-test='error']");
    public int checkoutItemCount = 0;

    public CheckoutPage(ChromeDriver driver) {
        super(driver);
    }

//    public List<String> getCheckoutItemsName() {
//        List<WebElement> itemElements = driver.findElements(By.cssSelector(".cart_item"));
//        List<String> checkoutItems = new ArrayList<>();
//        for (WebElement item : itemElements) {
//            checkoutItems.add(item.getText());
//        }
//        return checkoutItems;
//    }

    public int getCheckoutItemsNumber() {
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));
        return checkoutItemCount = items.size();
    }

    public void removeItemsFromCheckout() {
        for (int i = checkoutItemCount; i > 0; i--) {
            driver.findElement(removeItemButtonSelector).click();
        }
    }

    public void clickOnCheckoutButtonSelector() {
        driver.findElement(checkoutButtonSelector).click();
    }

    public void fillInFirstNameField(String firstname) {
        driver.findElement(firstNameFieldSelector).sendKeys(firstname);
    }

    public void fillInLastNameField(String lastname) {
        driver.findElement(lastNameFieldSelector).sendKeys(lastname);
    }

    public void fillInZipCodeField(String zipcode) {
        driver.findElement(zipCodeFieldSelector).sendKeys(zipcode);
    }

    public void clickOnContinueCheckout() {
        driver.findElement(continueCheckoutSelector).click();
    }

    public void clickOnFinishCheckout() {
        driver.findElement(finishCheckoutSelector).click();
    }

    public String orderPlacedSuccessfullyMessage() {
        return driver.findElement(confirmationOrderPlacedMessageSelector).getText();
    }

    public String checkoutFillInErrorMessage() {
        return driver.findElement(CheckoutErrorMessageSelector).getText();
    }
}