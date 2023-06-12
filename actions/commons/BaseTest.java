package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath +"\\browserDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("headless_firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath +"\\browserDriver\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);			
		} else if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath +"\\browserDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("headless_chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath +"\\browserDriver\\chromedriver.exe");	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath +"\\browserDriver\\\\msedgedriver.exe");
			driver = new EdgeDriver();			
		}else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.USER_PAGE_URL);
		//driver.get("https://live.guru99.com/");
		return driver;
	}	

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
