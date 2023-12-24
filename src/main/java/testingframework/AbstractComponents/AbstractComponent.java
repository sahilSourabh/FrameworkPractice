package testingframework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@routerlink,'/cart')]")
	WebElement cartButton;

	@FindBy(xpath = "//button[contains(@routerlink,'/myorders')]")
	WebElement orderButton;

	public void waitForElementToAppear(By FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToBeInvisible(WebElement FindEle) throws InterruptedException {

		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(FindEle));
	}

	public CartPage goTocartPage() {

		cartButton.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

	public OrdersPage gotoOrdersPage() {

		orderButton.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
	}

}
