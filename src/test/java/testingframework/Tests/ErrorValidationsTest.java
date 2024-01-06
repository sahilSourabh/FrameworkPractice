package testingframework.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import testingframework.TestComponents.BaseTest;
import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups="ErrorHandling")
	public void LoginErrorValidation() {
		
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@12";
		
		landingpage.loginApplication(username, pwd);
		String message = landingpage.getLandingpageErrorMessage();
		System.out.println(message);
		Assert.assertEquals("Incorrect email or password", message);
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@123";
		String productName = "ACE SWAGS";
		
		ProductCatalogue productcatalogue = landingpage.loginApplication(username, pwd);
		productcatalogue.getProductList();
		productcatalogue.getProductByName(productName);
		productcatalogue.scrollIntoProductView(productName);
		
		productcatalogue.addProductToCart(productName);
		productcatalogue.scrollToCartButton();
		
		CartPage cartpage = productcatalogue.goTocartPage();
		cartpage.listOfOrders();
		boolean itemMatch = cartpage.verifyProductDisplayed("ACE");
		System.out.println("Items matched: " + itemMatch);

		Assert.assertTrue(itemMatch,"Incorrect Product name :");

		
	}

}
