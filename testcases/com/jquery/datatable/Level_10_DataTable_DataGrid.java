package com.jquery.datatable;

import java.util.List;

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
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	
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
	
	//@Test
	public void Table_03_Get_Data() {
		//Viết hàm đọc dữ liệu của file country.txt ra
		//Lưu vào 1 List<String> = expected value = expectedAllCountryValues
		
		//homePage.refreshCurrentPage(driver);
		actualAllCountryValues = homePage.getValueCountryEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	
	@Test
	public void Table_04_Action_At_Any_Row() {
		homePage.clickToLoadButton();
		
		
		homePage.enterToTextboxAtRowNumberByColumnName("Company", "1", "ABZHD");
		homePage.enterToTextboxAtRowNumberByColumnName("Contact Person", "1", "TUAN");
		homePage.enterToTextboxAtRowNumberByColumnName("Order Placed", "1", "12");
		
		//dropdown
		homePage.selectDropdownAtRowNumberByColumnName("Country", "1", "Germany");
		homePage.sleepInSecond(2);
		homePage.selectDropdownAtRowNumberByColumnName("Country", "1", "Taiwan");
		homePage.sleepInSecond(2);
		
		//checkbox
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "2");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "3");
		
		//uncheck
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "4");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "5");
		
		//Click to icon
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		
		homePage.clickToIconByRowNumber("3", "Move Up");
		
		homePage.clickToIconByRowNumber("8", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("7", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("6", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
}
