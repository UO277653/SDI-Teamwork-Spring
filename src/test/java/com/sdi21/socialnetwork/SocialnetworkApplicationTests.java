package com.sdi21.socialnetwork;

import com.sdi21.socialnetwork.pageobjects.*;
import com.sdi21.socialnetwork.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SocialnetworkApplicationTests {


	static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckodriver = "C:\\Users\\adria\\OneDrive\\Escritorio\\UNIVERSIDAD\\AÑO 3\\SEMESTRE 2\\Sistemas Distribuidos e Internet\\Laboratorio\\Lab5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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

	@AfterEach
	public void tearDown(){
		driver.manage().deleteAllCookies();
	}

	@BeforeAll
	static public void begin() {}

	@AfterAll
	static public void end() {
		driver.quit();
	}

	@Test
	@Order(11)
	void PRUEBA11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		// NO USAR XPATH
		List<WebElement> userMenu = SeleniumUtils.waitLoadElementsBy(driver, "id", "userDropdown", PO_View.getTimeout());

		userMenu.get(0).click();

		List<WebElement> userOptions = SeleniumUtils.waitLoadElementsBy(driver, "id", "listUsersOption", PO_View.getTimeout());

		userOptions.get(0).click();

		SeleniumUtils.waitLoadElementsBy(driver, "id", "tableMarks", PO_View.getTimeout());

		List<WebElement> tableElements = driver.findElements(By.cssSelector("#tableMarks tbody tr"));

		int elementos = 0;

		elementos += tableElements.size();

		PO_PrivateView.pagination(driver, 2);

		tableElements = driver.findElements(By.cssSelector("#tableMarks tbody tr"));

		elementos += tableElements.size();

		PO_PrivateView.pagination(driver, 3);

		tableElements = driver.findElements(By.cssSelector("#tableMarks tbody tr"));

		elementos += tableElements.size();

		// TERMINAR CON ASSERT
		Assertions.assertEquals(13, elementos);
	}

}
