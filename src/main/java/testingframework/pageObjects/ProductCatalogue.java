package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> items;
	
	@FindBy(css=".card-body button:last-of-type")
	WebElement addtoCart;
	
	@FindBy(xpath="//button[contains(@routerlink,'/cart')]")
	WebElement cartButton;
	
	By CartAdd = By.cssSelector(".card-body button:last-of-type");
	By productLocator = By.cssSelector("b");
	
	
	
	public WebElement findItems(String productName) throws InterruptedException {
		
		WebElement product = items.stream()
		.filter(s -> s.findElement(productLocator).getText().equalsIgnoreCase(productName)).findFirst()
		.orElse(null);
		
		return product;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		findItems(productName).findElement(CartAdd).click();
	}
	
	public void goTocart () {
		
		cartButton.click();
	}

}
