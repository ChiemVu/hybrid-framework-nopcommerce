package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver =  driver;
	}
	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new HomePageObject(driver);
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

}
