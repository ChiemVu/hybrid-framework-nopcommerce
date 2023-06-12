package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserBackInStockSubscriptionsPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserDownloadableProductPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserOrderPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) { 
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Vu";
		lastName ="Chiem";
		existingEmail = "vtc" + generateFakeNumber() + "@gmail.com";
		password ="123456";
	}

	@Test
	public void Login_01_REgister_To_System() {
		registerPage = homePage.openUserRegisterPage();
		
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
		loginPage = homePage.openUserLoginPage();
		
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
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;
	private UserAddressPageObject addressPage;
	private UserOrderPageObject orderPage;
	private UserDownloadableProductPageObject downloadableProductPage;
	private UserBackInStockSubscriptionsPageObject backInStockSubscriptionsPage;
	private UserRewardPointsPageObject rewardPointPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
}
