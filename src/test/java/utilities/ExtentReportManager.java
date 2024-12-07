package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.BaseClass;

public class ExtentReportManager implements ITestListener{
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;
String rpName;



@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.h.mm.ss").format(new Date());
		
		rpName = "Test-Report- "+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+rpName);
		sparkReporter.config().setReportName("opencart Automation Report");
		sparkReporter.config().setDocumentTitle("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Enviroment", "QA");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operation System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}



@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got sucessfully execute");
	}


@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	ITestListener.super.onFinish(context);
	extent.flush(); //Hop nhat ta ca thong tin trong bao cao
	
	String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports\\"+ rpName;
	File extentReport = new File(pathOfExtentReport);
	
	try {
	Desktop.getDesktop().browse(extentReport.toURI());	
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	//Write email and send to Leader after Test.
	try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+ "\\reports\\"+ rpName);
		//Create an email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("2231121522@ut.edu.vn", "Nguyenchivien168@"));
		email.setSSLOnConnect(true);
		email.setFrom("2231121522@ut.edu.vn");//sender
		email.setSubject("Test Result");
		email.setMsg("Please find attacted report.......");
		email.addTo("chivien107@gmail.com");//receiver
		email.attach(url, "extent report", "please check report.............");
		email.send();
	
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}



}
