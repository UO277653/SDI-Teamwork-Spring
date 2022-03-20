package com.sdi21.socialnetwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PublicationListView {
    public static String getPublicationState(WebDriver driver, String state) {

        return driver.findElement(By.id(state)).getText();
    }
}
