package testingframework.FrameworkPractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.CheckOutPage;
import testingframework.pageObjects.ConfirmationPage;
import testingframework.pageObjects.LandingPage;
import testingframework.pageObjects.OrdersPage;
import testingframework.pageObjects.ProductCatalogue;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/client/";
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@123";
		String productName = "ACE SWAGS";
		String countryInput = "ind";
		String countryName = "India";
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// options.addArguments("headless");

		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		SoftAssert softAssert = new SoftAssert();

		// 1.landing page
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo(url);
		landingpage.loginDetails(username, pwd);
		ProductCatalogue productcatalogue = landingpage.login();

		// 2.Product catalogue
		productcatalogue.getProductList();
		WebElement prod = productcatalogue.getProductByName(productName);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", prod);
		Thread.sleep(2000);

		productcatalogue.addProductToCart(productName);

		WebElement cartButton = driver.findElement(By.xpath("//button[contains(@routerlink,'/cart')]"));
		js.executeScript("arguments[0].scrollIntoView(true)", cartButton);
		Thread.sleep(2000);
		CartPage cartpage = productcatalogue.goTocartPage();

		// 3.Cartpage
		cartpage.listOfOrders();
		boolean itemMatch = cartpage.verifyProductDisplayed(productName);
		// boolean itemMatch = cartpage.findOrder("ACE");
		System.out.println("Items matched: " + itemMatch);

		softAssert.assertTrue(itemMatch, "Incorrect item match - ");

		CheckOutPage checkoutpage = cartpage.gotCheckOutPage();

		// 4.Checkout Page
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);

		checkoutpage.inputCountryDetails(countryInput);
		checkoutpage.selectCountry(countryName);

		ConfirmationPage confirmationpage = checkoutpage.submitOrder();

		// Confirmation page

		String message = confirmationpage.getConfirmationMessage();
		System.out.println(message);
		String orderID = confirmationpage.getOrderId();
		// String orderID = "667738833dgff";
		System.out.println("OrderId: " + orderID);
		OrdersPage orderspage = confirmationpage.gotoOrdersPage();

		// Orders page
		orderspage.getIdList();
		boolean idmatch = orderspage.findOrderId(orderID);
		softAssert.assertTrue(idmatch, "OrderID is Incorrect - ");

		Thread.sleep(2000);
		driver.quit();

		softAssert.assertAll();

	}

}
