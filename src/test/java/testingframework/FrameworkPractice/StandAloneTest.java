package testingframework.FrameworkPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.CheckOutPage;
import testingframework.pageObjects.ConfirmationPage;
import testingframework.pageObjects.LandingPage;
import testingframework.pageObjects.ProductCatalogue;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String url = "https://rahulshettyacademy.com/client/";
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@123";
		String productName = "ACE SWAGS";
		String countryInput="ind";
		String countryName= "India";
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
		WebDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		SoftAssert softAssert = new SoftAssert();
		
		//1.landing page
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo(url);
		landingpage.loginDetails(username, pwd);
		landingpage.login();

		//2.Product catalogue
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		WebElement prod = productcatalogue.findItems(productName);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", prod);
		Thread.sleep(2000);
		
		productcatalogue.addProductToCart(productName);
		
		WebElement cartButton = driver.findElement(By.xpath("//button[contains(@routerlink,'/cart')]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		js.executeScript("arguments[0].scrollIntoView(true)", cartButton);
		Thread.sleep(2000);
		productcatalogue.goTocart();

		//3.Cartpage
		CartPage cartpage = new CartPage(driver);
		
		cartpage.listOfOrders();
		boolean itemMatch = cartpage.findOrder(productName);
		System.out.println(itemMatch);
		
		softAssert.assertTrue(itemMatch,"Incorrect item match - ");
			
		cartpage.gotCheckOut();
		
		//4.Checkout Page
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);
		
		
		checkoutpage.countryDetails(countryInput);
		checkoutpage.getCountry(countryName);
		checkoutpage.selectCountry(countryName);
		
		checkoutpage.gotoConfirmationPage();
		
		//Confirmation page
		ConfirmationPage confirmationpage = new ConfirmationPage (driver);
		String message = confirmationpage.getConfirmationMessage();
		System.out.println(message);
		
		String orderID = confirmationpage.getOrderId();
		System.out.println("OrderId: "+orderID);
		
		driver.findElement(By.xpath("//button[contains(@routerlink,'/myorders')]")).click();
		
		//Orders page
		List<WebElement> idList = driver.findElements(By.cssSelector("tbody tr th"));
		
		boolean idmatch = idList.stream().anyMatch(s->s.getText().equals(orderID));
		//boolean idmatch = idList.stream().anyMatch(s->s.getText().equals("6778678fghghjgj"));
		softAssert.assertTrue(idmatch,"OrderID is Incorrect - ");
		
		
		
		Thread.sleep(2000);
		driver.quit();
		
		softAssert.assertAll();

		

	}

}
