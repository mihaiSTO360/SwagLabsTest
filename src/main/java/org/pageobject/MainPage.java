package org.pageobject;

import org.openqa.selenium.By;

public class MainPage {

    public final By shoppingCartSelector = By.cssSelector("shopping_cart_container");
    public final By addToCartSelector = By.cssSelector(".inventory_list .inventory_item:nth-child(1) button.btn_inventory");
//    nth-child(2) înseamnă "al doilea copil al părintelui său", deci funcționează doar dacă butoanele sunt
//    frați între ei (adică apar pe același nivel în DOM).
    public final By backpackItemSelector = By.xpath("//div[.='Sauce Labs Backpack']");
    public final By bikeLightItemSelector = By.xpath("//div[.='Sauce Labs Bike Light']");
    public final By boltTShirtItemSelector = By.xpath("//div[.='Sauce Labs Bolt T-Shirt']");
    public final By fleeceJacketItemSelector = By.xpath("//div[.='Sauce Labs Fleece Jacket']");
    public final By onesieItemSelector = By.xpath("//div[.='Sauce Labs Onesie']");
    public final By redTShirtItemSelector = By.xpath("//div[.='Test.allTheThings() T-Shirt (Red)']");


}
