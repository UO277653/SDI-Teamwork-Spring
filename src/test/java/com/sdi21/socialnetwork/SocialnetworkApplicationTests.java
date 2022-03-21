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

	// Jonas
// 	static String Geckodriver = "C:\\Users\\Alejandro\\Desktop\\SDI-2022\\software\\software\\geckodriver-v0.27.0-win64\\geckodriver.exe";

  	// Adrian
	//static String Geckodriver = "C:\\Users\\adria\\OneDrive\\Escritorio\\UNIVERSIDAD\\AÑO 3\\SEMESTRE 2\\Sistemas Distribuidos e Internet\\Laboratorio\\Lab5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";


	//Sara
	static String Geckodriver = "D:\\UNI\\3º\\2º cuatri\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

	//Diego
	//static String Geckodriver = "C:\\Users\\dimar\\Desktop\\sdi\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

	//Ari
	//static String Geckodriver = "C:\\Users\\UO270119\\Desktop\\IIS (definitiva)\\3º - Tercero\\Segundo cuatri\\Sistemas Distribuidos e Internet\\Lab\\[materiales]\\5. Selenium\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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
		PO_SignUpView.signup(driver, "sarap@uniovi.es", "Paco", "Perez", "123456", "123456");

		String checkText = PO_NavView.getP().getString("welcome.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 1. Registro de usuario con datos inválidos:
	 * 		Campos vacíos (email, nombre, apellidos)
	 */
	@Test
	@Order(2)
	void prueba2() {
		PO_SignUpView.signup(driver, "", "", "", "123456", "123456");

		String checkText = PO_NavView.getP().getString("signup.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText , result.get(0).getText());
	}


	/**
	 * 1. Registro de usuario con datos inválidos
	 * 		repetición de contraseña inválida
	 */
	@Test
	@Order(3)
	void prueba3() {
		PO_SignUpView.signup(driver, "sara@uniovi.com", "Paco", "Perez", "123456", "122222");

		String checkText = PO_NavView.getP().getString("Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
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
		PO_SignUpView.signup(driver, "admin@email.com", "Paco", "Perez", "123456", "123456");

		String checkText = PO_NavView.getP().getString("Error.signup.email.duplicate", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 2. Inicio de sesión con datos válidos
	 * 		Administrador
	 */
	@Test
	@Order(5)
	public void prueba5(){
		PO_LoginView.login(driver, "admin@email.com", "admin");

		String checkText = PO_NavView.getP().getString("label.users", PO_Properties.getSPANISH());
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
		PO_LoginView.login(driver, "user01@email.com", "user01");

		String checkText = PO_NavView.getP().getString("label.users", PO_Properties.getSPANISH());
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
		PO_LoginView.login(driver, "", "");

		String checkText = PO_NavView.getP().getString("login.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 2. Inicio de sesión con datos válidos
	 * 		Email conrrecto pero contraseña incorrecta
	 */
	@Test
	@Order(8)
	public void prueba8(){
		PO_LoginView.login(driver, "user01@email.com", "user02");

		//Vuelve a mostrar el login
		String checkText = PO_NavView.getP().getString("login.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 3. Fin de sesión
	 * Salir sesion y comprobar que vuleve a redirigir a login
	 */
	@Test
	@Order(9)
	public void prueba9(){
		PO_LoginView.login(driver, "user01@email.com", "user01");

		String checkText = PO_NavView.getP().getString("label.users", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());

		//Desconexion
		PO_LoginView.logout(driver);

		//Comprobamos que volvemos a la página de login
		checkText = PO_NavView.getP().getString("login.message", PO_Properties.getSPANISH());
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
		//Sin estar autenticado el boton no está presente
		List<WebElement> boton = driver.findElements(By.id("logoutBtn"));
		Assertions.assertEquals(0, boton.size());

		//Nos identificamos
		PO_LoginView.login(driver, "user01@email.com", "user01");

		//Ahora el botón si está presente
		boton = driver.findElements(By.id("logoutBtn"));
		Assertions.assertEquals(1, boton.size());
	}

	/**
	 * 4. Listado de usuarios del sistema: admin
	 * Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema
	 */
	@Test
	@Order(11)
	void PRUEBA11() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		List<WebElement> userMenu = SeleniumUtils.waitLoadElementsBy(driver, "id", "userDropdown", PO_View.getTimeout());
		userMenu.get(0).click();
		List<WebElement> userOptions = SeleniumUtils.waitLoadElementsBy(driver, "id", "listUsersOption", PO_View.getTimeout());
		userOptions.get(0).click();
		int elementos = 0;
		for(int i = 0; i<4; i++){
			elementos += PO_UserListView.countUsersOnPage(driver, i);
		}

		// TERMINAR CON ASSERT
		Assertions.assertEquals(20, elementos);
	}

	/**
	 * 5. Admin: borrado múltiple de usuarios
	 * Borrar primer usuario
	 */
	@Test
	@Order(12)
	void PRUEBA12() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list");
		List<WebElement> tableElements = driver.findElements(By.cssSelector("#tableUsers tbody tr"));
		WebElement firstChild = tableElements.get(1);
		List<WebElement> removeElement = driver.findElements(By.name("userid3"));
		removeElement.get(0).click();
		PO_UserListView.delete(driver);
		tableElements = driver.findElements(By.cssSelector("#tableUsers tbody tr"));
		WebElement newFirstChild = tableElements.get(1);

		// TERMINAR CON ASSERT
		Assertions.assertNotEquals(firstChild, newFirstChild); // El usuario se ha borrado
	}

	/**
	 * 5. Admin: borrado múltiple de usuarios
	 * Borrar último usuario
	 */
	@Test
	@Order(13)
	void PRUEBA13() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list?page=3");
		List<WebElement> elementToRemove = driver.findElements(By.name("userid181"));
		Assertions.assertTrue(!elementToRemove.isEmpty());
		elementToRemove.get(0).click();
		PO_UserListView.delete(driver);
		List<WebElement> removedElement = driver.findElements(By.name("userid181"));

		// TERMINAR CON ASSERT
		Assertions.assertTrue(removedElement.isEmpty());
	}

	/**
	 * 5. Admin: borrado múltiple de usuarios
	 * Borrar tres usuarios
	 */
	@Test
	@Order(14)
	void PRUEBA14() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/user/list?page=2");
		List<WebElement> checkBoxes = driver.findElements(By.cssSelector("#tableUsers tbody tr td input"));
		checkBoxes.get(0).click();
		checkBoxes.get(1).click();
		checkBoxes.get(2).click();

		PO_UserListView.delete(driver);

		int elementos = 0;
		elementos += PO_UserListView.countUsersOnPage(driver, 0);
		elementos += PO_UserListView.countUsersOnPage(driver, 1);
		elementos += PO_UserListView.countUsersOnPage(driver, 2);
		elementos += PO_UserListView.countUsersOnPage(driver, 3);

		List<WebElement> lastPage = driver.findElements(By.id("lastPage"));
		lastPage.get(0).click();

		// TERMINAR CON ASSERT
		Assertions.assertEquals(16, elementos);
		Assertions.assertEquals("http://localhost:8090/user/list?page=3", driver.getCurrentUrl());
	}

	/**
	 * 6. Usuario: listado de usuarios
	 * Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema, excepto
	 * el propio usuario y aquellos que sean Administradores
	 */
	@Test
	@Order(15)
	void prueba15() {
		PO_LoginView.login(driver, "user01@email.com", "user01");
		PO_PrivateView.goToUsersList(driver);

		int elementos = 0;
		elementos += PO_UserListView.countUsersOnPage(driver, 0);
		elementos += PO_UserListView.countUsersOnPage(driver, 1);
		elementos += PO_UserListView.countUsersOnPage(driver, 2);
		elementos += PO_UserListView.countUsersOnPage(driver, 3);

		// all users but the deleted ones and the admin and logged in users
		Assertions.assertEquals(14, elementos);
	}

	/**
	 * 7. Buscar usuarios
	 * Búsqueda campo vacío
	 */
	@Test
	@Order(16)
	void prueba16() {
		PO_LoginView.login(driver, "user01@email.com", "user01");
		PO_PrivateView.goToUsersList(driver);

		PO_UserListView.search(driver,"");
		List<WebElement> users = driver.findElements(By.cssSelector("#tableUsers tbody tr"));
		Assertions.assertEquals(5, users.size());
	}

	/**
	 * 7. Buscar usuarios
	 * Búsqueda texto que no existe
	 */
	@Test
	@Order(17)
	void prueba17() {
		PO_LoginView.login(driver, "user01@email.com", "user01");
		PO_PrivateView.goToUsersList(driver);

		PO_UserListView.search(driver,"ZXCVBNM");
		List<WebElement> users = driver.findElements(By.cssSelector("#tableUsers tbody tr"));
		Assertions.assertEquals(0, users.size());
	}

	/**
	 * 7. Buscar usuarios
	 * Búsqueda texto correcto
	 */
	@Test
	@Order(18)
	void prueba18() {
		PO_LoginView.login(driver, "user01@email.com", "user01");
		PO_PrivateView.goToUsersList(driver);

		PO_UserListView.search(driver,"default");
		List<WebElement> users = driver.findElements(By.cssSelector("#tableUsers tbody tr"));
		Assertions.assertEquals(3, users.size());
	}

	/**
	 * 8. Enviar invitación de amistad
	 * Desde el listado de usuarios de la aplicación, enviar una invitación de amistad a un usuario.
	 * Comprobar que la solicitud de amistad aparece en el listado de invitaciones
	 */
	@Test
	@Order(19)
	void PRUEBA19() {

		//loguearse como user01 (no admin, admin no puede)
		PO_LoginView.login(driver, "user01@email.com", "user01");

		//ir a user/list
		driver.navigate().to("localhost:8090/user/list");

		//pulsar en + Añadir amigo que redirige a request/send/id (user05)
		List<WebElement> addButton = driver.findElements(By.id("addFriendBtn"));
		addButton.get(2).click();

		//logout user01
		PO_LoginView.logout(driver);

		//login con user05 (que no ha sido borrado previamente en el test anterior)
		PO_LoginView.fillLoginForm(driver, "user05@email.com", "user05");

		//ir a request/list
		driver.navigate().to("localhost:8090/request/list");

		int requests = 0;
		requests += PO_RequestListView.countRequestsOnPage(driver, 0);

		//pulsar en Aceptar que redirige a request/accept/id  (no es necesario pero facilita los tests siguientes)
		List<WebElement> acceptButton = driver.findElements(By.id("acceptFriendBtn"));
		acceptButton.get(0).click();

		Assertions.assertEquals(1, requests);
	}

	/**
	 * 8. Enviar invitación de amistad
	 * Desde el listado de usuarios de la aplicación, enviar una invitación de amistad a un usuario al que ya le
	 * habíamos enviado la invitación previamente. No debería dejarnos enviar la invitación. Se podría ocultar el
	 * botón de enviar invitación o notificar que ya había sido enviada previamente.
	 */
	@Test
	@Order(20)
	void PRUEBA20() {
		//loguearse como user01
		PO_LoginView.login(driver, "user01@email.com", "user01");

		//ir a user/list
		driver.navigate().to("localhost:8090/user/list");

		//pulsar en + Añadir amigo que redirige a request/send/id (user06)
		List<WebElement> addButton = driver.findElements(By.id("addFriendBtn"));
		addButton.get(2).click(); //get(2).click correspondería a user05 y va a intentar hacer click en su botón de addFriend
		// pero no puede porque está añadido como friend como resultado del test anterior, por tanto va a clickar en el user06

		//volver a user/list
		driver.navigate().to("localhost:8090/user/list");

		//comprobamos si la invitación está pendiente, no podemos enviar una nueva, aparece "Pending..." como texto
		String checkText = PO_NavView.getP().getString("label.request.pending", PO_Properties.getSPANISH());
		SeleniumUtils.textIsPresentOnPage(driver, checkText);

	}

	/**
	 * 9. Listar invitaciones de amistad
	 * Mostrar el listado de invitaciones de amistad recibidas.
	 * Comprobar con un listado que contenga varias invitaciones recibidas.
	 */
	@Test
	@Order(21)
	void PRUEBA21() {
		//loguearse como user08
		PO_LoginView.login(driver, "user08@email.com", "user08");

		//ir a request/list
		driver.navigate().to("localhost:8090/request/list");

		// contar invitaciones de amistad: en este momento ninguna
		int requests = 0;
		requests += PO_RequestListView.countRequestsOnPage(driver, 0);
		Assertions.assertEquals(0, requests);

		//logout user08
		PO_LoginView.logout(driver);

		/////////////// USER 09 /////////////////////

		//loguearse como user09
		PO_LoginView.login(driver, "user09@email.com", "user09");

		//ir a user/list a la page donde está el user08
		driver.navigate().to("localhost:8090/user/list?page=1");

		//pulsar en + Añadir amigo (user08)
		List<WebElement> addButton = driver.findElements(By.id("addFriendBtn"));
		addButton.get(1).click();

		//logout user09
		PO_LoginView.logout(driver);

		/////////////// USER 10 /////////////////////

		//loguearse como user10
		PO_LoginView.login(driver, "user10@email.com", "user10");

		//ir a user/list a la page donde está el user08
		driver.navigate().to("localhost:8090/user/list?page=1");

		//pulsar en + Añadir amigo (user08)
		addButton = driver.findElements(By.id("addFriendBtn"));
		addButton.get(1).click(); //seguramente no sea este click

		//logout user10
		PO_LoginView.logout(driver);
		/////////////////// VOLVEMOS A USER08 ////////////////////

		//loguearse como user08 de nuevo
		PO_LoginView.login(driver, "user08@email.com", "user08");

		//ir a request/list
		driver.navigate().to("localhost:8090/request/list");

		// contar invitaciones de amistad: debería haber 2
		requests += PO_RequestListView.countRequestsOnPage(driver, 0);
		Assertions.assertEquals(2, requests);
	}

	/**
	 * 10. Aceptar una invitación
	 * Sobre el listado de invitaciones recibidas. Hacer clic en el botón/enlace de una de ellas y comprobar
	 * que dicha solicitud desaparece del listado de invitaciones.
	 */
	@Test
	@Order(22)
	void PRUEBA22() {
		//loguearse como user15
		PO_LoginView.login(driver, "user15@email.com", "user15");

		//ir a user/list a la page donde está el user14
		driver.navigate().to("localhost:8090/user/list?page=1");

		//pulsar en + Añadir amigo (user14)
		List<WebElement> addButton = driver.findElements(By.id("addFriendBtn"));
		addButton.get(4).click();

		//logout user15
		PO_LoginView.logout(driver);

		//loguearse como user14
		PO_LoginView.login(driver, "user14@email.com", "user14");

		//ir a request/list
		driver.navigate().to("localhost:8090/request/list");

		//pulsar en Aceptar que redirige a request/accept/id
		List<WebElement> acceptButton = driver.findElements(By.id("acceptFriendBtn"));
		acceptButton.get(0).click();

		SeleniumUtils.textIsPresentOnPage(driver, "¡Aceptada!");
	}


	/**
	 * 11. Listado de amigos
	 * Mostrar el listado de amigos de un usuario
	 */
	@Test
	@Order(23)
	void prueba23() {
		PO_LoginView.login(driver, "user14@email.com", "user14");
		driver.findElement(By.id("friendList")).click();

		List<WebElement> friends = driver.findElements(By.cssSelector("#tableFriends tbody tr"));
		Assertions.assertEquals(1, friends.size());
	}

	/**
	 * 12. Crear nueva publicación
	 * Datos válidos
	 */
	@Test
	@Order(24)
	void PRUEBA24(){
		PO_LoginView.login(driver, "nopublications@email.com", "123456");

		driver.navigate().to("localhost:8090/publication/listown");
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		Assertions.assertTrue(publications == 0);
		driver.navigate().to("localhost:8090/publication/add");
		PO_PublicationView.fillAddPublicationForm(driver, "Dancing on the club", "Having fun with the besties, ;) ");

		driver.navigate().to("localhost:8090/publication/listown");
		publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		Assertions.assertTrue(publications == 1);

	}

	/**
	 * 12. Crear nueva publicación
	 * Datos inválidos
	 */
	@Test
	@Order(25)
	void PRUEBA25(){
		PO_LoginView.login(driver, "user07@email.com", "user07");
		driver.navigate().to("localhost:8090/publication/add");

		PO_PublicationView.fillAddPublicationForm(driver, " ", " ");

		String checkText = PO_PublicationView.getP().getString("Error.createPublication.text.invalid", PO_Properties.getSPANISH())
				+ "\n"+ PO_PublicationView.getP().getString("Error.createPublication.title.invalid", PO_Properties.getSPANISH());

		List<WebElement> list = driver.findElements(By.id("wrongTextMessage"));
		String result = list.get(0).getText();
		Assertions.assertEquals(checkText , result );

	}

	/**
	 * 13. Listado de publicaciones
	 * Mostrar listado de publicaciones de un usuario (comprobar que se muestran todas)
	 */
	@Test
	@Order(26)
	void PRUEBA26(){
		PO_LoginView.login(driver, "user07@email.com", "user07");

		driver.navigate().to("localhost:8090/publication/listown");
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		publications += PO_PublicationView.countPubliactionsOnPage(driver, 1);
		publications += PO_PublicationView.countPubliactionsOnPage(driver, 2);

		Assertions.assertEquals( 10, publications);
	}

	/**
	 * 14. Listado de publicaciones de un amigo
	 * Mostrar publicaciones de un usuario amigo
	 */
	@Test
	@Order(27)
	void PRUEBA27(){
		PO_LoginView.login(driver, "user06@email.com", "user06");

		//9 = id of user07
		driver.navigate().to("localhost:8090/publication/list/" + 9 );
		int publications = PO_PublicationView.countPubliactionsOnPage(driver, 0);
		publications += PO_PublicationView.countPubliactionsOnPage(driver, 1);
		Assertions.assertEquals( 10, publications);

	}

	/**
	 * 14. Listado de publicaciones de un amigo
	 * A través de URL, intentar listar publicaciones de un usuario no amigo
	 * Error de autenticación
	 */
	@Test
	@Order(28)
	void PRUEBA28(){
		PO_LoginView.login(driver, "user06@email.com", "user06"); // This user has 10 accepted publications and 1 censored
		driver.navigate().to("localhost:8090/publication/list/" + 9 );

		String checkText = PO_NavView.getP().getString("welcome.message", PO_Properties.getSPANISH());
		List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
		Assertions.assertEquals(checkText, result.get(0).getText());
	}

	/**
	 * 15. Internacionalización
	 * Cuatro páginas en inglés y español
	 */
	@Test
	@Order(29)
	public void prueba29(){
		PO_LoginView.login(driver, "user01@email.com", "user01"); //Fill the form, now loged in spanish

		PO_UserListView.checkUsersList(driver, PO_Properties.getSPANISH());
		PO_FriendsListView.checkFriendList(driver, PO_Properties.getSPANISH());
		PO_PublicationListView.checkOwnPublications(driver, PO_Properties.getSPANISH());
		PO_PublicationView.checkAddPublication(driver, PO_Properties.getSPANISH());

		PO_NavView.changeLang(driver, "btnEnglish"); //Change to English

		//English
		PO_UserListView.checkUsersList(driver, PO_Properties.getENGLISH());
		PO_FriendsListView.checkFriendList(driver, PO_Properties.getENGLISH());
		PO_PublicationListView.checkOwnPublications(driver, PO_Properties.getENGLISH());
		PO_PublicationView.checkAddPublication(driver, PO_Properties.getENGLISH());
	}





	/**
	 * 16. Seguridad
	 * Intentar acceder sin estar autenticado a la opción de listado de usuarios.
	 * Se deberá volver al formulario de login
	 */
	@Test
	@Order(30)
	public void Prueba30(){
		if(!driver.findElements(By.id("logoutBtn")).isEmpty()) {
			driver.findElement(By.id("logoutBtn")).click(); // Nos aseguramos de que no hay ningun usuario autenticado
		}
		driver.navigate().to("localhost:8090/user/list");
		Assertions.assertEquals("http://localhost:8090/login", driver.getCurrentUrl());
	}

	/**
	 * 16. Seguridad
	 * Intentar acceder sin estar autenticado a la opción de listado de invitaciones de amistad recibida de un usuario estándar.
	 * Se deberá volver al formulario de login.
	 */
	@Test
	@Order(31)
	public void Prueba31(){
		if(!driver.findElements(By.id("logoutBtn")).isEmpty()) {
			driver.findElement(By.id("logoutBtn")).click(); // Nos aseguramos de que no hay ningun usuario autenticado
		}
		driver.navigate().to("http://localhost:8090/request/list");
		Assertions.assertEquals("http://localhost:8090/login", driver.getCurrentUrl());
	}

	/**
	 * 16. Seguridad
	 * Como usuario normal intentar acceder a una opcion solo disponible para administradores
	 * Mensaje de accion prohibida
	 */
	@Test
	@Order(32)
	public void Prueba32(){
		PO_LoginView.login(driver, "nofriends@email.com", "123456");
		driver.navigate().to("http://localhost:8090/logger/list");

		Assertions.assertTrue(driver.findElement(By.id("loginForm")).isDisplayed());
	}

	/**
	 * 16. Seguridad
	 * Como administrador ver los loggs
	 */
	@Test
	@Order(33)
	public void Prueba33(){
		PO_LoginView.login(driver, "usuarioquenoexiste", "noexiste");
		PO_LoginView.fillLoginForm(driver, "otroquenoexiste", "noexiste");

		PO_SignUpView.signup(driver, "sarap2@uniovi.es", "Paco", "Perez", "123456", "123456");

		PO_LoginView.logout(driver);

		PO_SignUpView.signup(driver, "sarap3@uniovi.es", "Paco", "Perez", "123456", "123456");

		PO_LoginView.logout(driver);

		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

		PO_LoginView.logout(driver);

		PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
		driver.navigate().to("http://localhost:8090/logger/list");
		Assertions.assertTrue(PO_LoggerView.verifyThatThereAreNOfLogMessages(driver, 2));

	}

	/**
	 * 16. Seguridad
	 * Estando autenticado como usuario administrador, ir a visualización de logs, pulsar el botón/enlace borrar logs
	 * y comprobar que se eliminan los logs de la base de datos.
	 */
	@Test
	@Order(34)
	public void Prueba34(){
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("http://localhost:8090/logger/list");

		Assertions.assertTrue(driver.findElements(By.cssSelector("#loggerList tbody tr")).size() > 0);
		driver.findElement(By.id("deleteLogsBtn")).click();
		Assertions.assertTrue(driver.findElements(By.cssSelector("#loggerList tbody tr")).size() == 0);
	}


	/**
	 * 19. Moderación de publicaciones
	 * Como administrador, cambiar el estado de una publicación y comprobar que el estado ha cambiado.
	 */
	@Test
	@Order(37)
	void PRUEBA37() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list");
		Assertions.assertEquals("Aceptada", PO_PublicationListView.getPublicationState(driver, "state18"));
		driver.findElements(By.id("moderatePublication18")).get(0).click();
		Assertions.assertEquals("Moderada", PO_PublicationListView.getPublicationState(driver, "state18"));
	}

	/**
	 * 19. Moderación de publicaciones
	 * Como usuario estándar, comprobar que no aparece en el listado propio de publicaciones una publicación censurada.
	 */
	@Test
	@Order(38)
	void PRUEBA38() {
		PO_LoginView.login(driver, "user10@email.com", "user10");
		// This user has 10 accepted publications and 1 censored (added in the generatePublications method in InsertSampleDataService)

		driver.navigate().to("localhost:8090/publication/listown?page=0");
		List<WebElement> elements = driver.findElements(By.cssSelector("#publicationsTable tbody tr"));
		Assertions.assertEquals(5, elements.size());
		driver.navigate().to("localhost:8090/publication/listown?page=1");
		elements = driver.findElements(By.cssSelector("#publicationsTable tbody tr"));
		Assertions.assertEquals(5, elements.size());
		// There are 2 pages of publications for this user, each one with 5 publications, which are the 10 accepted

	}

	/**
	 * 19. Moderación de publicaciones
	 * Como usuario estándar, comprobar que, en el listado de publicaciones de un amigo, no aparece una publicación moderada.
	 */
	@Test
	@Order(39)
	void PRUEBA39() {
		PO_LoginView.login(driver, "user01@email.com", "user01"); // We log as user01

		List<WebElement> addFriendBtns = driver.findElements(By.id("addFriendBtn"));
		addFriendBtns.get(0).click(); // We send a friend request to user02 (who has a moderated publication, and 10 accepted)
		PO_LoginView.logout(driver);

		PO_LoginView.login(driver, "user03@email.com", "user03"); // We log as user02
		driver.navigate().to("http://localhost:8090/request/list");
		driver.findElement(By.cssSelector("#tableRequests tbody tr td a")).click(); // User 2 accepts the request
		PO_LoginView.logout(driver);
		PO_LoginView.login(driver, "user01@email.com", "user01");
		driver.navigate().to("http://localhost:8090/friend/list");
		driver.findElement(By.cssSelector("#tableFriends tbody tr td a")).click();

		int nPublication = 0;
		nPublication += driver.findElements(By.cssSelector("#publicationsTable tbody tr")).size();
		driver.navigate().to("http://localhost:8090/publication/list/4?page=1");
		nPublication += driver.findElements(By.cssSelector("#publicationsTable tbody tr")).size(); // Last page

		Assertions.assertEquals(10, nPublication); // Only the 10 accepted publications are shown
	}

	/**
	 * 19. Moderación de publicaciones
	 * Como usuario estándar, intentar acceder la opción de cambio del estado de una publicación. En ese caso
	 * redireccionar al usuario hacia el formulario de login.
	 */
	@Test
	@Order(40)
	void PRUEBA40() {
		PO_LoginView.login(driver, "default@email.com", "123456");

		driver.navigate().to("localhost:8090/publication/moderate/4"); // Trying to change a publication
		WebElement passwordField = driver.findElement(By.id("password"));
		Assertions.assertTrue(passwordField.isDisplayed()); // We verify that the password field (which is part of the login page), is displayed
	}

	/**
	 * 19. Moderación de publicaciones
	 * Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que corresponde con el listado publicaciones.
	 */
	@Test
	@Order(41)
	void PRUEBA41() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=", driver.getCurrentUrl());
	}

	/**
	 * 16. Moderación de publicaciones
	 * Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se muestra la página que
	 * corresponde, con la lista de publicaciones vacía.
	 */
	@Test
	@Order(42)
	void PRUEBA42() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("thisdoesnotexist");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=thisdoesnotexist", driver.getCurrentUrl());
		Assertions.assertEquals(0, driver.findElements(By.cssSelector("publicationsTable tbody tr")).size()); // No elements are shown
	}

	/**
	 * 16. Moderación de publicaciones
	 * Hacer una búsqueda con un texto específico y comprobar que se muestra la página que corresponde,
	 * con la lista de publicaciones en los que el texto especificado sea parte de título, estado o del email.
	 */
	@Test
	@Order(43)
	void PRUEBA43() {
		PO_LoginView.login(driver, "admin@email.com", "admin");

		driver.navigate().to("localhost:8090/publication/list"); // Trying to change a publication
		driver.findElement(By.name("searchTextPub")).sendKeys("user10@email.com");
		driver.findElement(By.id("searchBtn")).click();

		Assertions.assertEquals("http://localhost:8090/publication/list?searchTextPub=user10%40email.com", driver.getCurrentUrl());
		Assertions.assertEquals(5, driver.findElements(By.cssSelector("#publicationsTable tbody tr")).size());
	}
}
