package testingframework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testingframework.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReportsNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//test.log(Status.PASS, "Test Passed Successfully");
		extentTest.get().log(Status.PASS, "Test Passed Successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		//Building a ScreenShot Utility
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String path= null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
