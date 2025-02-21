package org.example.ProductDetails;

import org.example.reusePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver mydriver) {
        super(mydriver);
    }
    private final By productDetailsPage=By.xpath("//*[@class='inventory_details']");
    private final By productNameDetails=By.className("inventory_details_name");
    private final By productDescDetails=By.className("inventory_details_desc");
    private final By productPriceDetails=By.className("inventory_details_price");
    private final By openMenu=By.xpath("//button[text()='Open Menu']");
    private final By allItems=By.id("inventory_sidebar_link");
    private final By addToCartButton = By.className("btn_inventory");
    private final By cartIcon = By.className("shopping_cart_badge");

    public void assertDetailsPage(){
        BasePageDriver.findElement(productDetailsPage).isDisplayed();
    }
    public void clickOnProduct(String productName){
        BasePageDriver.findElement(By.xpath("//div[text()='"+productName+"']")).click();
    }
    public String getProductName() {
        return BasePageDriver.findElement(productNameDetails).getText();
    }
    public String getProductDescription() {
        return BasePageDriver.findElement(productDescDetails).getText();
    }

    public String getProductPrice() {
        return BasePageDriver.findElement(productPriceDetails).getText();
    }

    public void clickOpenMenu() {
        BasePageDriver.findElement(openMenu).click();
    }

    public void clickAllItems() {
        BasePageDriver.findElement(allItems).click();
    }

    public void clickAddToCart() {
        BasePageDriver.findElement(addToCartButton).click();
    }

    public boolean isProductInCart() {
        WebElement Number = BasePageDriver.findElement(cartIcon);
        return Number.isDisplayed() && !Number.getText().isEmpty();
    }
}
