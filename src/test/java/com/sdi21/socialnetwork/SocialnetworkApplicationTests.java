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
	//static String Geckodriver = "C:\\Users\\adria\\OneDrive\\Escritorio\\UNIVERSIDAD\\AÑO 3\\SEMESTRE 2\\Sistemas Distribuidos e Internet\\Laboratorio\\Lab5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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
/*	@Test
	@Order(1)
	void prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "sarap@uniovi.es", "Paco", "Perez", "123456", "123456");

		String checkText = PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 1. Registro de usuario con datos inválidos:
	 * 		Campos vacíos (email, nombre, apellidos)
	 *//*
	@Test
	@Order(2)
	void prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "", "", "", "123456", "123456");

		String checkText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText , result.get(0).getText());
	}


	*//**
	 * 1. Registro de usuario con datos inválidos
	 * 		repetición de contraseña inválida
	 *//*
	@Test
	@Order(3)
	void prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_SignUpView.fillForm(driver, "sara@uniovi.com", "Paco", "Perez", "123456", "122222");

		String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 1. Registro de usuario con datos inválidos
	 * 		email existente
	 *//*
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

	*//**
	 * 2. Inicio de sesión con datos válidos
	 * 		Administrador
	 *//*
	@Test
	@Order(5)
	public void prueba5(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		String checkText = PO_HomeView.getP().getString("label.userList", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 2. Inicio de sesión con datos válidos
	 * 		Usuario
	 *//*
	@Test
	@Order(6)
	public void prueba6(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		String checkText = PO_HomeView.getP().getString("label.userList", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 2. Inicio de sesión con datos inválidos
	 * 		Usuario estandar, email y contraseña vacíos
	 *//*
	@Test
	@Order(7)
	public void prueba7(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "", "");

		String checkText = PO_HomeView.getP().getString("login.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 2. Inicio de sesión con datos válidos
	 * 		Email conrrecto pero contraseña incorrecta
	 *//*
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

	*//**
	 * 3. Fin de sesión
	 * Salir sesion y comprobar que vuleve a redirigir a login
	 *//*
	@Test
	@Order(9)
	public void prubea9(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		String checkText = PO_HomeView.getP().getString("label.userList", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());

		//Desconexion
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		//Comprobamos que volvemos a la página de login
		checkText = PO_HomeView.getP().getString("login.message", PO_Properties.getSPANISH());
		result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	*//**
	 * 3. Fin de sesión
	 * Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado
	 *//*
	@Test
	@Order(10)
	public void prueba10(){
		//Sin estar autenticado el boton no está presente
		List<WebElement> boton = driver.findElements(By.id("logoutBtn"));
		Assertions.assertEquals(0, boton.size());

		//Nos identificamos
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		//Ahora el botón si está presente
		boton = driver.findElements(By.id("logoutBtn"));
		Assertions.assertEquals(1, boton.size());
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
		Assertions.assertEquals(15, elementos);
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


*/
	@Test
	void PRUEBA24(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "nopublications@email.com", "123456");

		driver.navigate().to("localhost:8090/publication/listown");
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		Assertions.assertTrue(publications == 0);
		driver.navigate().to("localhost:8090/publication/add");
		PO_PublicationView.fillAddPublicationForm(driver, "Dancing on the club", "Having fun with the besties, ;) ");

		driver.navigate().to("localhost:8090/publication/listown");
		publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		Assertions.assertTrue(publications == 1);

	}

	@Test
	void PRUEBA25(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "default@email.com", "123456");
		driver.navigate().to("localhost:8090/publication/add");

		PO_PublicationView.fillAddPublicationForm(driver, " ", " ");

		String checkText = PO_PublicationView.getP().getString("Error.createPublication.text.invalid", PO_Properties.getSPANISH())
				+ "\n"+ PO_PublicationView.getP().getString("Error.createPublication.title.invalid", PO_Properties.getSPANISH());

		List<WebElement> list = driver.findElements(By.id("wrongTextMessage"));
		String result = list.get(0).getText();
		Assertions.assertEquals(checkText , result );

	}

	@Test
	void PRUEBA26(){
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

		driver.navigate().to("localhost:8090/publication/listown");
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		publications += PO_PublicationView.countPubliactionsOnPage(driver, 1);
		publications += PO_PublicationView.countPubliactionsOnPage(driver, 2);

		Assertions.assertTrue( publications == 10);
	}

	@Test
	@Order(37)
	void PRUEBA37() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list");
		Assertions.assertEquals("Aceptada", PO_PublicationListView.getPublicationState(driver, "state19"));
		driver.findElements(By.id("moderatePublication19")).get(0).click();
		Assertions.assertEquals("Moderada", PO_PublicationListView.getPublicationState(driver, "state19"));
	}

	@Test
	@Order(38)
	void PRUEBA38() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "default@email.com", "123456"); // This user has 10 accepted publications and 1 censored

		driver.navigate().to("localhost:8090/publication/listown?page=0");
		List<WebElement> elements = driver.findElements(By.cssSelector("#publicationsTable tbody tr"));
		Assertions.assertEquals(5, elements.size());
		driver.navigate().to("localhost:8090/publication/listown?page=1");
		elements = driver.findElements(By.cssSelector("#publicationsTable tbody tr"));
		Assertions.assertEquals(5, elements.size());
		// There are 2 pages of publications for this user, each one with 5 publications, which are the 10 accepted

	}

	@Test
	@Order(39)
	void PRUEBA39() {

	}

	@Test
	@Order(40)
	void PRUEBA40() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "default@email.com", "123456");

		driver.navigate().to("localhost:8090/publication/moderate/4"); // Trying to change a publication
		WebElement passwordField = driver.findElement(By.id("password"));
		Assertions.assertTrue(passwordField.isDisplayed()); // We verify that the password field (which is part of the login page), is displayed
	}

	@Test
	@Order(41)
	void PRUEBA41() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=", driver.getCurrentUrl());
	}

	@Test
	@Order(42)
	void PRUEBA42() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("thisdoesnotexist");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=thisdoesnotexist", driver.getCurrentUrl());
		Assertions.assertEquals(0, driver.findElements(By.cssSelector("publicationsTable tbody tr")).size()); // No elements are shown
	}

	@Test
	@Order(43)
	void PRUEBA43() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("default@email.com");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=default%40email.com", driver.getCurrentUrl());
		Assertions.assertEquals(5, driver.findElements(By.cssSelector("#publicationsTable tbody tr")).size());
	}
}
