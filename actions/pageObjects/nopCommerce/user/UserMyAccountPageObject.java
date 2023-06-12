package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.User.MyAccountPageUI;

public class UserMyAccountPageObject extends BasePage{
	private WebDriver driver;
	public UserMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isMyAccountPageDisplayed() {
		waitForElementVisible(driver, MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
		return isElementDisplayed(driver, MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
	}
	public void clickToNewsletterCheckbox() {
		// TODO Auto-generated method stub
		
	}
}
