package Android_Automation.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;

	public static ExtentReports getReporter() {
	    if (extent == null) {
	        // Create folder with timestamp
	        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
	        String reportDir = System.getProperty("user.dir") + "/ReportGenerator/" + "ExtentReports"+ timestamp;
	        
	        // Ensure directory exists
	        new java.io.File(reportDir).mkdirs();

	        // Report file path
	        String reportPath = reportDir + "/ExtentReport.html";

	        // Spark reporter
	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        spark.config().setReportName("Automation Test Report");
	        spark.config().setDocumentTitle("Test Execution Results");
	        // ✅ Add logo (top-left or wherever you want) using CSS
	        spark.config().setCss(
	                ".nav-logo > img { " +
	                "   width: 180px !important;" +   // increase width
	                "   height: auto !important;" +   // auto keeps proportions
	                "   margin-center: 10px;" +
	                "}"
	        );

	        // ✅ Add logo into the report body using JS (appears in the top section)
	        spark.config().setJs(
	                "document.addEventListener('DOMContentLoaded', function() {" +
	                "  var logoDiv = document.createElement('div');" +
	                "  logoDiv.className = 'logo';" +
	                "  logoDiv.innerHTML = '<img src=\"file:///C:/Users/dinesh/eclipse-workspace/Appium_Framework/src/test/resources/Simform_Logo.png\" />';" +
	                "  var nav = document.querySelector('.nav-logo');" +   // insert in navbar
	                "  if(nav){ nav.innerHTML = logoDiv.innerHTML; }" +
	                "});"
	        );


	        // Attach reporter
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("Tester", "Dinesh");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("Plateform", "Android");
	        extent.setSystemInfo("build version", "V1.0.0");

	        System.out.println("✅ Report will be generated at: " + reportPath);
	    }
	    return extent;
	}
}
