package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) { 
		driver = getBrowserDriver(browserName);
		
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
		firstName = "Vu";
		lastName ="Chiem";
		emailAddress = "vtc" + generateFakeNumber() + "@gmail.com";
		password ="123456";		
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_01-Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01-Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");		
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_02-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox("vtc@@123");
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		System.out.println("Register_02-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_02-Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_03-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAddress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		System.out.println("Register_03-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_03-Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}
		

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_04-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAddress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		System.out.println("Register_04-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_03-Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		System.out.println("Register_05-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_05-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAddress);
		registerPage.inputPasswordTextbox("123");
		registerPage.inputConfirmPasswordTextbox("123");
		
		System.out.println("Register_05-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_05-Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Register_06-Step 01: Click to Register link");
		homePage.openUserRegisterPage();
		
		System.out.println("Register_06-Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAddress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(emailAddress);
		
		System.out.println("Register_06-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_06-Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
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
