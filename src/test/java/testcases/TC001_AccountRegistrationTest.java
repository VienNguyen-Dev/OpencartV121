package testcases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.BasePage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() throws InterruptedException {
		logger.info("************** Starting TC001_AccountRegistrationTest ******");

		try {
			HomePage hp = new HomePage(driver);
			logger.info("Click on My Account Link");
			hp.clickMyAccount();
			logger.info("Click on Register Link");
			hp.clickRegister();
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Provide Customer Details....");
			regPage.setFirstName(randomeString().toUpperCase());
			regPage.setLastName(randomeString().toUpperCase());
			regPage.setEmail(randomeString().toLowerCase() + "@gmail.com");
			regPage.setTelephone(randomeNumber());

			String password = randomeStringAndNumber();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.clickCheckPolicy();
			regPage.clickContinue();

			logger.info("Validate expected message");
			String confMsg = regPage.getConfirmtionMsg();
			if (confMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test fail....");
				logger.debug("Debug log...");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			
			Assert.fail();
		}

		logger.info("Finished *********TC001_AccountRegistrationTest**********");
	}

}
