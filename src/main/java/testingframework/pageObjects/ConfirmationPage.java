package testingframework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingframework.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td h1")
	WebElement confirmationText;
	
	@FindBy(css="label[class='ng-star-inserted']")
	WebElement orderId;
	
	public String getConfirmationMessage() {
		
		return confirmationText.getText();
	}
	
	public String getOrderId() {
		
		String orderID = orderId.getText().split(" ")[1];
		return orderID;
	}

}
