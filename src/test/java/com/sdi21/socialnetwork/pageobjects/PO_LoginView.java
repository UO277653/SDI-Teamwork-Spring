package com.sdi21.socialnetwork.pageobjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_LoginView extends PO_NavView {

    static public void fillLoginForm(WebDriver driver, String email, String passwordp){
        WebElement actualDni = driver.findElement(By.name("username"));
        actualDni.click();
        actualDni.clear();
        actualDni.sendKeys(email);

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void login(WebDriver driver, String email, String password){
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, email, password);
        //COmprobamos que entramos en la pagina privada de Alumno
    }

    static public void logout(WebDriver driver){
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    public static void checkLogin(WebDriver driver, int locale){
        String checkText = PO_HomeView.getP().getString("login.message", locale);
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        checkText = PO_HomeView.getP().getString("label.password", locale) + ":";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
}
