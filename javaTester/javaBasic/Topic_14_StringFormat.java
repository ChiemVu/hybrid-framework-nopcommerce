package javaBasic;

public class Topic_14_StringFormat {
	public static String DYNAMIC_SIDEBAR_LINK_BY_PAGE_NAME = "xpath=//div[@class='listbox']//a[text()='%s']";
	public static String DYNAMIC_LINK_BY_PAGE_NAME = "xpath=//div[@class='%s']//a[text()='%s']";
	public static void main(String[] args) {
		clickToLink(DYNAMIC_SIDEBAR_LINK_BY_PAGE_NAME, "Customer info");
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "listbox", "Addresses");
	}
	public static void clickToLink(String dynamicLink, String...params) {
		String locator = String.format(dynamicLink,(Object[]) params);
		System.out.println("click to: "+ locator);
	}
}
