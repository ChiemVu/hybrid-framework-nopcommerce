package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.Admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void inputEmailTextbox(String adminEmaillAddress) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, adminEmaillAddress);		
	}
	
	public void inputPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}
	
	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public AdminDashboardPageObject LoginAsAdmin(String adminEmaillAddress, String adminPassword) {
		inputEmailTextbox(adminEmaillAddress);
		inputPasswordTextbox(adminPassword);
		return clickToLoginButton();
	}


	public boolean isLoginButtonAtAdminPageDisplayed() {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return isElementDisplayed(driver, AdminLoginPageUI.LOGIN_BUTTON);
	}	
}
