package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage{
	private WebDriver driver;
	public MyAccountPageObject(WebDriver driver) {
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
