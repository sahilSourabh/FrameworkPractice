package testingframework.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testingframework.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub
		
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\testingframework\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless")) {
				
//				options.addArguments("--start-maximized");
//				options.addArguments("window-size=1440,900");
				options.addArguments("headless");
				
				}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;

	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.quit();
	}

}
