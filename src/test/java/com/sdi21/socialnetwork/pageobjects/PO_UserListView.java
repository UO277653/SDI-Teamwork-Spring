package com.sdi21.socialnetwork.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_UserListView {

    static public int countUsersOnPage(WebDriver driver, int page){

        driver.navigate().to("localhost:8090/user/list?page=" + page);
        return driver.findElements(By.cssSelector("#tableMarks tbody tr")).size();

    }

    public static void delete(WebDriver driver) {
        List<WebElement> removeButton = driver.findElements(By.id("deleteBtn"));
        removeButton.get(0).click();
    }
}
