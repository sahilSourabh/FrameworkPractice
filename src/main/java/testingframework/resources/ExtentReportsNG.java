package testingframework.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports getReportObject() {
		
		File path = new File(System.getProperty("user.dir")+"//reports//index.html");
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Automation Tests");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("User", "Ace");
		
		return extent;
		
		
	}
	
	

}
