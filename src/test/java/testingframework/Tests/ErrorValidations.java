package testingframework.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testingframework.TestComponents.BaseTest;
import testingframework.pageObjects.CartPage;
import testingframework.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test
	public void LoginErrorValidation() {
		
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@12";
		
		landingpage.loginApplication(username, pwd);
		String message = landingpage.getLandingpageErrorMessage();
		System.out.println(message);
		Assert.assertEquals("Incorrect email or password.", message);
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		
		String username = "ace_kazuki@gmail.com";
		String pwd = "Acekazuki@123";
		String productName = "ACE SWAGS";
		
		ProductCatalogue productcatalogue = landingpage.loginApplication(username, pwd);

		WebElement prod = productcatalogue.getProductByName(productName);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", prod);
		Thread.sleep(2000);

		productcatalogue.addProductToCart(productName);

		WebElement cartButton = driver.findElement(By.xpath("//button[contains(@routerlink,'/cart')]"));
		js.executeScript("arguments[0].scrollIntoView(true)", cartButton);
		Thread.sleep(2000);
		CartPage cartpage = productcatalogue.goTocartPage();

		//boolean itemMatch = cartpage.verifyProductDisplayed(productName);
		boolean itemMatch = cartpage.verifyProductDisplayed("ACE");
		System.out.println("Items matched: " + itemMatch);

		Assert.assertTrue(itemMatch, "Incorrect item match - ");

		
	}

}
