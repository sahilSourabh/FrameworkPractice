package testingframework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testingframework.TestComponents.BaseTest;
import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.CheckOutPage;
import testingframework.pageObjects.ConfirmationPage;
import testingframework.pageObjects.OrdersPage;
import testingframework.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String username = "ace_kazuki@gmail.com";
	String pwd = "Acekazuki@123";
	String productName = "ACE SWAGS";

	@Test(dataProvider = "getData")
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		
		
		String countryInput = "ind";
		String countryName = "India";

		SoftAssert softAssert = new SoftAssert();

		// 1.landing page
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("username"), input.get("password"));

		// 2.Product catalogue
		productcatalogue.getProductList();
		productcatalogue.getProductByName(input.get("product"));
		productcatalogue.scrollIntoProductView(input.get("product"));

		productcatalogue.addProductToCart(input.get("product"));
		productcatalogue.scrollToCartButton();

		CartPage cartpage = productcatalogue.goTocartPage();

		// 3.Cartpage
		cartpage.listOfOrders();
		boolean itemMatch = cartpage.verifyProductDisplayed(input.get("product"));
		// boolean itemMatch = cartpage.verifyProductDisplayed("ACE");
		System.out.println("Items matched: " + itemMatch);

		Assert.assertTrue(itemMatch, "Incorrect item match - ");

		CheckOutPage checkoutpage = cartpage.gotCheckOutPage();

		// 4.Checkout Page
		checkoutpage.scrollingWindow();
		checkoutpage.inputCountryDetails(countryInput);
		checkoutpage.selectCountry(countryName);

		ConfirmationPage confirmationpage = checkoutpage.submitOrder();

		// 5.Confirmation page
		String message = confirmationpage.getConfirmationMessage();
		System.out.println(message);
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(2000);
//		String orderID = confirmationpage.getOrderId();
//		//String orderID = "667738833dgff";
//		System.out.println("OrderId: " + orderID);
//		OrdersPage orderspage = confirmationpage.gotoOrdersPage();

		// 6.Orders page
//		orderspage.getIdList();
//		boolean idmatch = orderspage.findOrderId(orderID);
//		Assert.assertTrue(idmatch, "Order is not valid - ");

		softAssert.assertAll();

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void orderHistoryTest() throws InterruptedException {

		ProductCatalogue productcatalogue = landingpage.loginApplication(username, pwd);
		OrdersPage orderspage = productcatalogue.gotoOrdersPage();
		Thread.sleep(2000);
		;
		boolean orderMatch = orderspage.verifyDisplayedOrders(productName);
		// System.out.println(orderMatch);
		Assert.assertTrue(orderMatch);
		Thread.sleep(2000);
	}

	public String getScreenshot(String testcaseName) throws IOException {

		TakesScreenshot ss = (TakesScreenshot) driver;
		File srcFile = ss.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "//reports" + testcaseName + ".png");
		FileUtils.copyFile(srcFile, destFile);

		return (System.getProperty("user.dir") + "//reports" + testcaseName + ".png");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String,String> map1 = new HashMap<>();
//		map1.put("username", "AvosD@gmail.com");
//		map1.put("password", "Avos@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map2 = new HashMap<>();
//		map2.put("username", "ace_kazuki@gmail.com");
//		map2.put("password", "Acekazuki@123");
//		map2.put("product", "ACE SWAGS");
//		
//		return new Object[][] { {map1}, {map2}};

//		return new Object[][] { {"AvosD@gmail.com","Avos@1234","ADIDAS ORIGINAL"}, {"ace_kazuki@gmail.com","Acekazuki@123","ACE SWAGS"}};
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\testingframework\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
