package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.ExcelUtility;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void validate_login() throws IOException {

		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		MyAccountPage maccPage = new MyAccountPage(driver);

		logger.info("********Starting Test TC002_LoginTest ************");
		try {

			logger.info("Click on Account Link");
			hp.clickMyAccount();
			logger.info("Click on Login Link");
			hp.clickLogin();

			logger.info("Provide infomation login.........");

			
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickButtonLogin();

			// Validate login
			boolean targetPage = maccPage.isMyAcountPageExist();
//			Assert.assertEquals(targetPage, true, "Login Fail");
				Assert.assertTrue(targetPage);
		} catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		logger.info("******** Finished Test TC002_LoginTest **********");

	}
}
