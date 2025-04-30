package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends LoginPage {

    public final By continueShoppingButtonSelector = By.cssSelector("continue-shopping");
    public final By checkoutButtonSelector = By.cssSelector("checkout");
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

    public int getCheckoutItemsNumber(){
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));
       return checkoutItemCount = items.size();
    }

}