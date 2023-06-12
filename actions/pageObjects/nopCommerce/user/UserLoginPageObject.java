package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.User.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	public UserLoginPageObject(WebDriver driver) {
		this.driver =  driver;
	}
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		//return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	public void inputEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, invalidEmail);		
	}
	public void inputPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);	
	}
	public String getEmailErrorMessageTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	public String getEmailUnsuccessfullMessage() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MESSAGE);
	}
	
	public UserHomePageObject LoginAsUser(String userEmailAddress, String userPassword) {
		inputEmailTextbox(userEmailAddress);
		inputPasswordTextbox(userPassword);
		return clickToLoginButton();
	}

}
