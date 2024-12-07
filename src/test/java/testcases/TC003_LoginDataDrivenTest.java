package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven") // getting data provider from different
																				// class
	public void verify_login_datadriven(String email, String password, String exp) throws InterruptedException {
		logger.info("*** Starting Test TC003_LoginDataDrivenTest ************");
		try {
		// Home Page
		HomePage hp = new HomePage(driver);
		logger.info("CLick on MyAccount Link");
		hp.clickMyAccount();
		logger.info("CLick on Login Link");
		hp.clickLogin();

		// Login Page
		logger.info("Provide data provider");
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickButtonLogin();

		// My account page
		MyAccountPage maccPage = new MyAccountPage(driver);
		boolean targetPage = maccPage.isMyAcountPageExist();

		// verify taget page
		// 1. Data valid --> login success -> test pass -> logout
						// --> login fail -> test failed
		//2) Data invalid --> login fail -> test pass
						//-> login success -> test  fail -> logout
		// Nếu kết quả mong đợi là hợp lệ và kết quả kiểm thử là pass => Test pass và
		// logout
		logger.info("Validation target page.....");
		if (exp.equalsIgnoreCase("Valid")) { // So sánh hai chuỗi không phân biệt
			if (targetPage == true) {
				Assert.assertTrue(true);
				maccPage.clickLogout();
			}else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				maccPage.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(false);
			}
		}
		
	}
		catch(Exception e) {
			Assert.fail();
		}
Thread.sleep(3000);
		logger.info("*** Finished Test TC003_LoginDataDrivenTest ************");
	}
}
