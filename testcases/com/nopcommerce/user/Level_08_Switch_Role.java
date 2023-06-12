package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) { 
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Vu";
		lastName ="Chiem";
		userEmailAddress = "vtc" + generateFakeNumber() + "@gmail.com";
		System.out.println(userEmailAddress);
		userPassword ="123456";
		adminEmaillAddress = "admin@yourstore.com";
		adminPassword = "admin";
		
		userRegisterPage = userHomePage.openUserRegisterPage();
		
		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputEmailTextbox(userEmailAddress);
		userRegisterPage.inputPasswordTextbox(userPassword);
		userRegisterPage.inputConfirmPasswordTextbox(userPassword);		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openUserLoginPage();
		userHomePage = userLoginPage.LoginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		userMyAccountPage = userHomePage.openMyAccountPage();
		userHomePage = userMyAccountPage.clickToLogoutAtUserPage(driver);
		
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.LoginAsAdmin(adminEmaillAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardPageDisplayed());
		Assert.assertFalse(adminDashboardPage.isIconAjaxUndisplay());
		adminLoginPage = adminDashboardPage.clickToLogoutAtAdminPage(driver);
		Assert.assertTrue(adminLoginPage.isLoginButtonAtAdminPageDisplayed());
	}

	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.openUserLoginPage();
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	private WebDriver driver;
	private String firstName, lastName, userEmailAddress, userPassword, adminEmaillAddress, adminPassword;
	private UserRegisterPageObject userRegisterPage;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserMyAccountPageObject userMyAccountPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
}
