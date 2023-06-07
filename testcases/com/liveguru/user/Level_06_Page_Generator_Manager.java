package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_06_Page_Generator_Manager extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "Vu";
		lastName = "Chiem";
		emailAddress = "vtc" + generateFakeNumber() +"@gmail.com";
		password = "123456";		
	}

	@Test
	public void User_01_Register_To_System() {
		loginPage = homePage.openToLoginPage();
		registerPage = loginPage.openToRegisterPage();
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailAddressTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		myDashboardPage = registerPage.openMyDashboardPage();
		Assert.assertEquals(myDashboardPage.getRegisterSuccessMessage(),"Thank you for registering with Main Website Store.");
		myDashboardPage.clickToAccountButton();
		homePage = myDashboardPage.openHomePage();		
	}
	
	@Test
	public void User_02_Login_To_System() {
		loginPage = homePage.openToLoginPage();
		loginPage.inputToEmailAddressTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		myDashboardPage = loginPage.clickToLoginButton();
		Assert.assertTrue(myDashboardPage.isMyDashboardPageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
	private String firstName, lastName, emailAddress, password;
}
