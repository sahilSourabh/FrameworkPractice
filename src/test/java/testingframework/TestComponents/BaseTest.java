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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testingframework.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	
	
	public WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub

//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		options.addArguments("headless");
//		options.addArguments("window-size=1440,900");
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\testingframework\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}

		driver.manage().window().setSize(new Dimension(1440,900));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;

	}
	
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}

}
