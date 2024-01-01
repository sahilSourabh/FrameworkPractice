package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingframework.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver; 
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(css="tbody tr th")
//	List<WebElement> idList;
	
	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement> ordersList;
	
	public boolean verifyDisplayedOrders(String productName )
	{
		boolean itemMatch = ordersList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return itemMatch;
	}
	
//	public List<WebElement> getIdList() {
//		
//		return idList;
//	}
//	
//	public boolean findOrderId(String orderId) {
//		
//		boolean idmatch = getIdList().stream().anyMatch(s->s.getText().equals(orderId));
//		return idmatch;
//	}
	
	

}
