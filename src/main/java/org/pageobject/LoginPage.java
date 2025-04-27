package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public final By usernameFieldSelector = By.cssSelector("input[placeholder='Username']");
    public final By passwordFieldSelector = By.cssSelector("input[placeholder='Password']");
    public final By loginButton = By.cssSelector("input.submit-button");
    public final By errorMessageSelector = By.cssSelector("[data-test='error']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUsernameField(String username) {
        driver.findElement(usernameFieldSelector).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordFieldSelector).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessageSelector).getText();
    }
}
