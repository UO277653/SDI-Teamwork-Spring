package com.sdi21.socialnetwork;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class User_SignUp_Login_Logout {
	static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckodriver = "D:\\UNI\\3º\\2º cuatri\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox, Geckodriver);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckodriver);
		driver = new FirefoxDriver();
		return driver;
	}
	@BeforeEach
	public void setUp(){
		driver.navigate().to(URL);
	}

	//Después de cada prueba se borran las cookies del navegador
	@AfterEach
	public void tearDown(){

//		driver.manage().deleteAllCookies();
	}

	//Antes de la primera prueba
	@BeforeAll
	static public void begin() {}

	//Al finalizar la última prueba
	@AfterAll
	static public void end() {
		//Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}


	/**
	 * 1. Registro de usuario con datos válidos
	 */
	@Test
	void prueba1() {
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		email vacío
	 */
	@Test
	void prueba2_1() {
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		nombre vacío
	 */
	@Test
	void prueba2_2() {
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		apellidos vacíos
	 */
	@Test
	void prueba2_3() {
	}


	/**
	 * 1. Registro de usuario con datos inválidos
	 * 		repetición de contraseña inválida
	 */
	@Test
	void prueba3() {
	}

	/**
	 * 1. Registro de usuario con datos inválidos
	 * 		email existente
	 */
	@Test
	void prueba4() {
	}

}
