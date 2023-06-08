package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.AddressPageObject;
import pageObjects.nopCommerce.BackInStockSubscriptionsPageObject;
import pageObjects.nopCommerce.ChangePasswordPageObject;
import pageObjects.nopCommerce.DownloadableProductPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyAccountPageObject;
import pageObjects.nopCommerce.MyProductReviewsPageObject;
import pageObjects.nopCommerce.OrderPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.RewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) { 
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "Vu";
		lastName ="Chiem";
		existingEmail = "vtc" + generateFakeNumber() + "@gmail.com";
		password ="123456";
	}

	@Test
	public void Login_01_REgister_To_System() {
		registerPage = homePage.openRegisterPage();
		
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputEmailTextbox(existingEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Login_02_Login_To_System() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputEmailTextbox(existingEmail);
		loginPage.inputPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void Login_03_My_Account() {		
		myAccountPage = homePage.openMyAccountPage();
		Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed());
	}
		

	@Test
	public void Login_04_Switch_Page() {
		addressPage = myAccountPage.openAddressPage(driver);
		orderPage = addressPage.openOrderPage(driver);
		downloadableProductPage = orderPage.openDownloadableProductPage(driver);
		backInStockSubscriptionsPage = downloadableProductPage.openBackInStockSubscriptionsPage(driver);
		rewardPointPage = backInStockSubscriptionsPage.openRewardPointsPage(driver);
		changePasswordPage = rewardPointPage.openChangePasswordPage(driver);
		myProductReviewsPage = changePasswordPage.openMyProductReviewsPage(driver);
		myAccountPage = myProductReviewsPage.openMyAccountPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	private String firstName, lastName, existingEmail, password;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private AddressPageObject addressPage;
	private OrderPageObject orderPage;
	private DownloadableProductPageObject downloadableProductPage;
	private BackInStockSubscriptionsPageObject backInStockSubscriptionsPage;
	private RewardPointsPageObject rewardPointPage;
	private ChangePasswordPageObject changePasswordPage;
	private MyProductReviewsPageObject myProductReviewsPage;
}
