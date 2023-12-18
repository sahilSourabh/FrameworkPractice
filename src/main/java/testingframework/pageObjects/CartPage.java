package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> orderList;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> listOfOrders() {
		
		return orderList;
		
	}
	
	public boolean verifyProductDisplayed(String productName) {
		
		boolean itemMatch = listOfOrders().stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return itemMatch;
		
	}
	
	public CheckOutPage gotCheckOutPage() {
		
		checkout.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
		
	}

}
