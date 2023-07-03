package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) { 
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);		
	}

	//@Test
	public void Table_01_Paging() {	
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActive("18"));
		
		homePage.openPagingByPageNumber("1");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActive("1"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActive("7"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActive("20"));
	}		

	//@Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLaber("Country", "Southern Asia");
		homePage.enterToHeaderTextboxByLaber("Females", "16538000");
		homePage.enterToHeaderTextboxByLaber("Males", "18179000");
		homePage.enterToHeaderTextboxByLaber("Total", "34718000");
		homePage.sleepInSecond(3);		
	}
	
	@Test
	public void Table_03_Get_Data() {
		//homePage.refreshCurrentPage(driver);
		homePage.getValueEachRowAtAllPage();
		//homePage.getValueEachRowAtAllPage();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
}
