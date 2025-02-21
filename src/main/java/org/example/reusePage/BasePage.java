package org.example.reusePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static java.lang.invoke.MethodHandles.lookup;


public class BasePage {

    public static final Logger log = LogManager.getLogger(lookup().lookupClass());
    public static WebDriver BasePageDriver;


     WebDriverWait wait = new WebDriverWait(BasePageDriver, Duration.ofSeconds(10));

    public BasePage(WebDriver mydriver){
        BasePageDriver=mydriver;
    }

    public void navigateBack(WebDriver Driver) throws InterruptedException {
        Driver.navigate().back();
        Thread.sleep(2000);
    }
    public void Assertion_page_Title_Name(String Page_Name){
        String title =  BasePageDriver.findElement(By.xpath("//*[text()='"+Page_Name+"']")).getText();
        Assert.assertEquals(title,Page_Name);
        log.info("you are in the {} Page and this is correct", Page_Name);
        System.out.println("you are in the "+Page_Name+" Page and this is correct");
    }
}



















/*
* wait
* implict wait
* explict wait
* thread
*
*
* */





























