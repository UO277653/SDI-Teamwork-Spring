package com.sdi21.socialnetwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_PublicationView extends PO_NavView {

    static public void fillAddPublicationForm(WebDriver driver, String title, String text) {
        WebElement dni = driver.findElement(By.id("title"));
        dni.click();
        dni.clear();
        dni.sendKeys(title);


        WebElement password = driver.findElement(By.id("text"));
        password.click();
        password.clear();
        password.sendKeys(text);

        //Pulsar el boton de Alta.
        By boton = By.id("sendButton");
        driver.findElement(boton).click();

    }

    static public int countPubliactionsOnPage(WebDriver driver, int page){

        driver.navigate().to("localhost:8090/publication/listown?page=" + page );
        return driver.findElements(By.cssSelector("#publicationsTable tbody tr")).size();

    }
}