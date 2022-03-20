package com.sdi21.socialnetwork.pageobjects;

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

    static public void login(WebDriver driver, String dni, String password, String text){

        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, dni, password);
        //COmprobamos que entramos en la pagina privada de Alumno
        List<WebElement> result = PO_View.checkElementBy(driver, "text", text);
    }

    static public void logout(WebDriver driver, int locale){

        String loginText = PO_HomeView.getP().getString("signup.message", locale);
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }
}
