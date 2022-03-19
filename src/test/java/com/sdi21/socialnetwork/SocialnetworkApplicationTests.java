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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SocialnetworkApplicationTests {

	/*
	    NO USAR XPATH
	 */
	static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

	//Adrian
//	static String Geckodriver = "C:\\Users\\adria\\OneDrive\\Escritorio\\UNIVERSIDAD\\AÑO 3\\SEMESTRE 2\\Sistemas Distribuidos e Internet\\Laboratorio\\Lab5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

	//Sara
	//static String Geckodriver = "D:\\UNI\\3º\\2º cuatri\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

	//Diego
	static String Geckodriver = "C:\\Users\\dimar\\Desktop\\sdi\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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

	/**
	 * 1. Registro de usuario con datos válidos
	 */
	@Test
	@Order(1)
	void prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "sarap@uniovi.es", "Paco", "Perez", "123456", "123456");

		String checkText = PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		email vacío
	 */
	@Test
	@Order(2)
	void prueba2_1() {
		//TODO
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "", "Paco", "Perez", "123456", "123456");
//		id="surname-error"

		List<WebElement> result = driver.findElements(By.id("surname-error") );
//		List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.email.empty", PO_Properties.getSPANISH() );
		String checkText = PO_HomeView.getP().getString("Error.signup.email.empty", PO_Properties.getSPANISH());
		Assertions.assertEquals(checkText , result.get(0).getText());
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		nombre vacío
	 */
	@Test
	void prueba2_2() {
		//TODO
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		apellidos vacíos
	 */
	@Test
	void prueba2_3() {
		//TODO
	}

	/**
	 * 1. Registro de usuario con datos inválidos
	 * 		repetición de contraseña inválida
	 */
	@Test
	@Order(3)
	void prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "sara@uniovi.com", "Paco", "Perez", "123456", "122222");

		String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 1. Registro de usuario con datos inválidos
	 * 		email existente
	 */
	@Test
	@Order(4)
	void prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "admin@email.com", "Paco", "Perez", "123456", "123456");

		List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH() );
		//Comprobamos el error de email repetido.
		String checkText = PO_HomeView.getP().getString("Error.signup.email.duplicate", PO_Properties.getSPANISH());
		Assertions.assertEquals(checkText , result.get(0).getText());
	}

	/**
	 * 2. Inicio de sesión con datos válidos
	 * 		Administrador
	 */
	@Test
	@Order(5)
	public void prueba5(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		String checkText = "Lista de usuarios:";
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 2. Inicio de sesión con datos válidos
	 * 		Usuario
	 */
	@Test
	@Order(6)
	public void prueba6(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		String checkText = "Lista de usuarios:";
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 2. Inicio de sesión con datos inválidos
	 * 		Usuario estandar, email y contraseña vacíos
	 */
	@Test
	@Order(7)
	public void prueba7(){
		//TODO
	}

	/**
	 * 2. Inicio de sesión con datos válidos
	 * 		Email conrrecto pero contraseña incorrecta
	 */
	@Test
	@Order(8)
	public void prueba8(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user02");

		//Vuelve a mostrar el login
		String checkText = PO_HomeView.getP().getString("login.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 3. Fin de sesión
	 * Salir sesion y comprobar que vuleve a redirigir a login
	 */
	@Test
	@Order(9)
	public void prubea9(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		String checkText = "Lista de usuarios:";
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());

		//Desconexion
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		//Comprobamos que volvemos a la página de login
		checkText = PO_HomeView.getP().getString("login.message", PO_Properties.getSPANISH());
		result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 3. Fin de sesión
	 * Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado
	 */
	@Test
	@Order(10)
	public void prueba10(){
		//TODO
	}

	@Test
	@Order(11)
	void PRUEBA11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		List<WebElement> userMenu = SeleniumUtils.waitLoadElementsBy(driver, "id", "userDropdown", PO_View.getTimeout());
		userMenu.get(0).click();
		List<WebElement> userOptions = SeleniumUtils.waitLoadElementsBy(driver, "id", "listUsersOption", PO_View.getTimeout());
		userOptions.get(0).click();

		int elementos = 0;

		elementos += PO_UserListView.countUsersOnPage(driver, 0);
		elementos += PO_UserListView.countUsersOnPage(driver, 1);
		elementos += PO_UserListView.countUsersOnPage(driver, 2);

		// TERMINAR CON ASSERT
		Assertions.assertEquals(13, elementos);
	}

	@Test
	@Order(12)
	void PRUEBA12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list");
		List<WebElement> tableElements = driver.findElements(By.cssSelector("#tableMarks tbody tr"));
		WebElement firstChild = tableElements.get(0);
		List<WebElement> removeElement = driver.findElements(By.name("userid1"));
		removeElement.get(0).click();
		PO_UserListView.delete(driver);
		tableElements = driver.findElements(By.cssSelector("#tableMarks tbody tr"));
		WebElement newFirstChild = tableElements.get(0);

		// TERMINAR CON ASSERT
		Assertions.assertNotEquals(firstChild, newFirstChild);
	}

	@Test
	@Order(13)
	void PRUEBA13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list?page=2");
		List<WebElement> elementToRemove = driver.findElements(By.name("userid15"));
		Assertions.assertTrue(!elementToRemove.isEmpty());
		elementToRemove.get(0).click();
		PO_UserListView.delete(driver);
		List<WebElement> removedElement = driver.findElements(By.name("userid15"));

		// TERMINAR CON ASSERT
		Assertions.assertTrue(removedElement.isEmpty());
	}

	@Test
	@Order(14)
	void PRUEBA14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list?page=2");
		List<WebElement> checkBoxes = driver.findElements(By.cssSelector("#tableMarks tbody tr td input"));
		checkBoxes.forEach((checkBox) -> checkBox.click());

		PO_UserListView.delete(driver);

		int elementos = 0;
		elementos += PO_UserListView.countUsersOnPage(driver, 0);
		elementos += PO_UserListView.countUsersOnPage(driver, 1);

		List<WebElement> lastPage = driver.findElements(By.id("lastPage"));
		lastPage.get(0).click();

		// TERMINAR CON ASSERT
		Assertions.assertEquals(10, elementos);
		Assertions.assertEquals("http://localhost:8090/user/list?page=1", driver.getCurrentUrl()); // Nos aseguramos de que la segunda era la última página
	}



	@Test
	void PRUEBA24(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "Default", "123456");
		driver.navigate().to("localhost:8090/publication/add");

		PO_PublicationView.fillAddPublicationForm(driver, "Dancing on the club", "Having fun with the besties, ;) ");

		driver.navigate().to("localhost:8090/publication/listown");
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		Assertions.assertTrue(publications == 3);

	}

	@Test
	void PRUEBA25(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "Default", "123456");
		driver.navigate().to("localhost:8090/publication/add");

		PO_PublicationView.fillAddPublicationForm(driver, " ", " ");

		PO_View.getP().getString("Error.createPublication.title.invalid", PO_Properties.getSPANISH());
		PO_View.getP().getString("Error.createPublication.text.invalid", PO_Properties.getSPANISH());
	}
}
