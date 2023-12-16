package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {

		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input")
	WebElement inputfield;
	
	@FindBy(css=".ta-item span")
	List<WebElement> country;
	
	@FindBy(css=".btnn.action__submit")
	WebElement placeOrderButton;
	
	public void countryDetails(String countryInput) {
		
		inputfield.sendKeys(countryInput);
	}
	
	public WebElement getCountry(String countryName) {
		
		WebElement select = country.stream().filter(s->s.getText().equals(countryName)).findFirst().orElse(null);
		return select;
	}

	public void selectCountry(String countryName)  {
		
		getCountry(countryName).click();
	}
	
	public void gotoConfirmationPage() {
		
		placeOrderButton.click();
	}
	
	
	
}
