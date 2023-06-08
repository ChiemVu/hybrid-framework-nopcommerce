package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.MyAccountPageUI;

public class AddressPageObject extends BasePage{
	private WebDriver driver;
	public AddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToNewsletterCheckbox() {
		// TODO Auto-generated method stub
		
	}
	public boolean isMyAccountPageDisplayed() {
		waitForElementVisible(driver, MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
		return isElementDisplayed(driver, MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
	}
}
