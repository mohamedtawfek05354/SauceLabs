package org.example.loginPage;

import org.example.reusePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver mydriver) {
        super(mydriver);
    }
    private final By usernameField=By.id("user-name");
    private final By passwordField=By.id("password");
    private final By loginButton=By.id("login-button");
    public void enterUserName(String username){
        BasePageDriver.findElement(usernameField).clear();
        BasePageDriver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password){
        BasePageDriver.findElement(passwordField).clear();
        BasePageDriver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin(){
        BasePageDriver.findElement(loginButton).click();
    }

}
