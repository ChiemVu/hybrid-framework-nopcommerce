package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageUIs.nopCommerce.User.LoginPageUI;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	
	@FindBy( id = "Email-error")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessErrorMessasge;
	
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		//return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	public void inputEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, invalidEmail);		
	}
	public void inputPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);	
	}
	public String getEmailErrorMessageTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}
	public String getEmailUnsuccessfullMessage() {
		waitForElementVisible(driver, unsuccessErrorMessasge);
		return getElementText(driver, unsuccessErrorMessasge);
	}
	
	public UserHomePageObject LoginAsUser(String userEmailAddress, String userPassword) {
		inputEmailTextbox(userEmailAddress);
		inputPasswordTextbox(userPassword);
		return clickToLoginButton();
	}
	
}
