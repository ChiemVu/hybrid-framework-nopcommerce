package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyAccountPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_06_Page_Generator_Manager_II extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) { 
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "Vu";
		lastName ="Chiem";
		invalidEmail="123@cv@2";		
		existingEmail = "vtc" + generateFakeNumber() + "@gmail.com";
		password ="123456";
		notFoundEmail = "vtcv45@gmail.com";
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(existingEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputEmailTextbox(invalidEmail);
		loginPage.inputPasswordTextbox(password);		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageTextbox(), "Wrong email");	
	}

	@Test
	public void Login_03_Not_Found_Email() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputEmailTextbox(notFoundEmail);
		loginPage.inputPasswordTextbox(password);		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
		

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputEmailTextbox(existingEmail);		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");		
	}

	@Test
	public void Login_05_existing_Email_incorrect_Password() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputEmailTextbox(existingEmail);
		loginPage.inputPasswordTextbox("123655");
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailUnsuccessfullMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_valid_Email_Password() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputEmailTextbox(existingEmail);
		loginPage.inputPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		myAccountPage = homePage.clickToMyAccountLink();
		myAccountPage.clickToNewsletterCheckbox();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, password;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
}
