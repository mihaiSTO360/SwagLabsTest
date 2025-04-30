package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends LoginPage {

    public final By shoppingCartSelector = By.cssSelector("a.shopping_cart_link");
    public final By addToCartSelector = By.cssSelector(".inventory_list .inventory_item:nth-child(1) button.btn_inventory");
    //    nth-child(2) înseamnă "al doilea copil al părintelui său", deci funcționează doar dacă butoanele sunt
//    frați între ei (adică apar pe același nivel în DOM).
    public final By backpackItemSelector = By.xpath("//div[.='Sauce Labs Backpack']");
    public final By backpackAddToCartSelector = By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']");
    public final By bikeLightItemSelector = By.xpath("//div[.='Sauce Labs Bike Light']");
    public final By bikeLightAddToCartSelector = By.cssSelector("button[id='add-to-cart-sauce-labs-bike-light']");
    public final By boltTShirtItemSelector = By.xpath("//div[.='Sauce Labs Bolt T-Shirt']");
    public final By boltTShirtAddToCartSelector = By.cssSelector("button[id='add-to-cart-sauce-labs-bolt-t-shirt']");
    public final By fleeceJacketItemSelector = By.xpath("//div[.='Sauce Labs Fleece Jacket']");
    public final By fleeceJacketAddToCartSelector = By.cssSelector("button[id='add-to-cart-sauce-labs-fleece-jacket']");
    public final By onesieItemSelector = By.xpath("//div[.='Sauce Labs Onesie']");
    public final By onesieAddToCartSelector = By.cssSelector("button[id='add-to-cart-sauce-labs-onesie']");
    public final By redTShirtItemSelector = By.xpath("//div[.='Test.allTheThings() T-Shirt (Red)']");
    public final By redTShirtAddToCartItemSelector = By.cssSelector("button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
    public final By twitterButtonSelector = By.cssSelector("a[data-test='social-twitter']");
    public final By facebookButtonSelector = By.cssSelector("a[data-test='social-facebook']");
    public final By linkedinButtonSelector = By.cssSelector("a[data-test='social-linkedin']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnTwitterButton() {
        driver.findElement(twitterButtonSelector).click();
    }

    public void clickOnFacebookButtonSelector() {
        driver.findElement(facebookButtonSelector).click();
    }

    public void clickOnLinkedinButtonSelector() {
        driver.findElement(linkedinButtonSelector).click();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCartSelector).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLightAddToCartSelector).click();
    }

    public void addBoltTShirtToCart() {
        driver.findElement(boltTShirtAddToCartSelector).click();
    }

    public void addFleeceJacketToCart() {
        driver.findElement(fleeceJacketAddToCartSelector).click();
    }

    public void addOnesieToCart() {
        driver.findElement(onesieAddToCartSelector).click();
    }

    public void addRedTShirtToCart() {
        driver.findElement(redTShirtAddToCartItemSelector).click();
    }

    public void clickOnShoppingCartSelector() {
        driver.findElement(shoppingCartSelector).click();
    }


}
