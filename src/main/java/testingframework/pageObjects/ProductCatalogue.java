package testingframework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingframework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".card-body button:last-of-type")
	WebElement addtoCart;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By CartAdd = By.cssSelector(".card-body button:last-of-type");
	By productLocator = By.cssSelector("b");
	By toastContainer = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		
		return products;
	}
	

	public WebElement getProductByName(String productName) throws InterruptedException {

		WebElement product = getProductList().stream()
				.filter(s -> s.findElement(productLocator).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		return product;

	}
	
	public void scrollIntoProductView(String productName) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", getProductByName(productName));
		Thread.sleep(2000);
	}

	public void addProductToCart(String productName) throws InterruptedException {

		getProductByName(productName).findElement(CartAdd).click();
		waitForElementToAppear(toastContainer);
		waitForElementToBeInvisible(spinner);
	}
	
	


}
