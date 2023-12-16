package testingframework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td h1")
	WebElement confirmationText;
	
	@FindBy(css="label[class='ng-star-inserted']")
	WebElement orderId;
	
	@FindBy(xpath="//button[contains(@routerlink,'/myorders')]")
	WebElement orderButton;
	
	public String getConfirmationMessage() {
		
		return confirmationText.getText();
	}
	
	public String getOrderId() {
		
		String orderID = orderId.getText().split(" ")[1];
		return orderID;
	}

	public void gotoOrders() {
		
		orderButton.click();
		
	}
}
