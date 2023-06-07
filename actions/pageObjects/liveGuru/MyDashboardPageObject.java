package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage{
	private WebDriver driver;
	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyDashboardPageDisplayed() {
		waitForElementVisible(driver, MyDashboardPageUI.MY_DASHBOARD_PAGE_TITLE);		
		return isElementDisplayed(driver, MyDashboardPageUI.MY_DASHBOARD_PAGE_TITLE);
	}
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickToAccountButton() {
		waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_BUTTON);
	}

	public HomePageObject openHomePage() {
		waitForElementClickable(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
}
