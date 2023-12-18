package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

	WebDriver driver; 
	
	public OrdersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr th")
	List<WebElement> idList;
	
	public List<WebElement> getIdList() {
		
		return idList;
	}
	
	public boolean findId(String orderId) {
		
		boolean idmatch = getIdList().stream().anyMatch(s->s.getText().equals(orderId));
		return idmatch;
	}
	
	

}
