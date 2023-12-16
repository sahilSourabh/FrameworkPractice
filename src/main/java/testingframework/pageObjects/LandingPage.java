package testingframework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement mail;
	
	@FindBy(id="userPassword")
	WebElement pwd;
	
	@FindBy(css="#login")
	WebElement login;
	
	public void loginDetails(String username, String password) {
		
		mail.sendKeys(username);
		pwd.sendKeys(password);
		
	}
	public void login () {
		login.click();
		
	}
	
	public void goTo(String url) {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	


	}

