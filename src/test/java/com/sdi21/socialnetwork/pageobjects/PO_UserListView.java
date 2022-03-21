package com.sdi21.socialnetwork.pageobjects;
import org.junit.jupiter.api.Assertions;
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

    public static void search(WebDriver driver, String searchText) {
        WebElement searchBar = driver.findElement(By.name("searchText"));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(searchText);
        driver.findElement(By.id("searchButton")).click();
    }

    public static void checkUsersList(WebDriver driver, int locale){
        driver.navigate().to("localhost:8090/user/list");
        String checkText = PO_HomeView.getP().getString("label.name", locale);
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        checkText = PO_HomeView.getP().getString("label.surname", locale);
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
}
