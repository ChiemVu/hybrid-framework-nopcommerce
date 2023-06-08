package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commons.BasePage;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_03_Page_Object_02_Login extends BasePage {
	private WebDriver driver;
	private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		loginPage = new LoginPageObject(driver);
		homePage = new HomePageObject(driver);
		
		firstName = "Vu";
		lastName ="Chiem";
		invalidEmail="123@cv@2";
		
		existingEmail = "vtc" + generateFakeNumber() + "@gmail.com";
		password ="123456";
		notFoundEmail = "vtcv45@gmail.com";
		System.out.println("Register_03-Step 01: Click to Register link");
		homePage.openRegisterPage();
		registerPage = new RegisterPageObject(driver);
		
		System.out.println("Register_03-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(existingEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		System.out.println("Register_03-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_03-Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01-Step 01: Click to Login link");
		homePage.openLoginPage();
		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_01-Step 02: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01-Step 03: Verify error message display");
		Assert.assertEquals(loginPage.getEmailErrorMessageTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02-Step 01: Click to Login link");
		homePage.openLoginPage();		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_02-Step 02: Input to required field");
		loginPage.inputEmailTextbox(invalidEmail);
		loginPage.inputPasswordTextbox(password);
		
		System.out.println("Login_02-Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_02-Step 04: Verify error message display");
		Assert.assertEquals(loginPage.getEmailErrorMessageTextbox(), "Wrong email");
	
	}

	@Test
	public void Login_03_Not_Found_Email() {
		System.out.println("Login_03-Step 01: Click to Login link");
		homePage.openLoginPage();		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_03-Step 02: Input to required field");
		loginPage.inputEmailTextbox(notFoundEmail);
		loginPage.inputPasswordTextbox(password);
		
		System.out.println("Login_03-Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_03-Step 04: Verify error message display");
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
		

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Login_04-Step 01: Click to Login link");
		homePage.openLoginPage();		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_04-Step 02: Input to required field");
		loginPage.inputEmailTextbox(existingEmail);
		
		System.out.println("Login_04-Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_04-Step 04: Verify error message display");
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}

	@Test
	public void Login_05_existing_Email_incorrect_Password() {
		System.out.println("Login_05-Step 01: Click to Login link");
		homePage.openLoginPage();		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_05-Step 02: Input to required field");
		loginPage.inputEmailTextbox(existingEmail);
		loginPage.inputPasswordTextbox("123655");
		
		System.out.println("Login_05-Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_05-Step 04: Verify error message display");
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	@Test
	public void Login_06_valid_Email_Password() {
		System.out.println("Login_06-Step 01: Click to Login link");
		homePage.openLoginPage();		
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login_06-Step 02: Input to required field");
		loginPage.inputEmailTextbox(existingEmail);
		loginPage.inputPasswordTextbox(password);
		
		System.out.println("Login_06-Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		
		System.out.println("Login_06-Step 04: Verify error message display");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
