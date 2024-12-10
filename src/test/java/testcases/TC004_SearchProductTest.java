package testcases;

import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SearchProductResultPage;
import testbase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

	@Test(groups = {"Master"})
	public void searchProductTest() {
		
		logger.info("*************Starting Test TC004_Search ************");
		
		try {
			logger.info("Nagigate to Home Page....");
			HomePage hp = new HomePage(driver);
			String productName ="Mac";
			logger.info("Enter product name into Search text box field: "+ productName );
			hp.setSearchInput(productName);
			logger.info("Click on 'Search' button to initialize search product");
			SearchProductResultPage spPage = hp.clickSearchButton();
			logger.info("Navigate to Search Product Result Page");
			
			logger.info("Validate Search Product Result Page Exist .........");
			
			boolean productNameStatus = spPage.isSearchProductPageExists();
			Assert.assertEquals(productNameStatus, true);
			
			logger.info("Continue... Validate product name display on the Product Search Result page");
			String expectedProductName="MacBook";
			boolean isProductDisplayed = spPage.isProductExists(expectedProductName);
			logger.info("Vefiry if product " + expectedProductName + "displayed in the search result page");
			
			Assert.assertTrue(isProductDisplayed, "Product "+ expectedProductName + " not found in  search results");
			logger.info("Validate product "+ expectedProductName + " found in search results. Test Pass");
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*************Finished Test TC004_Search***********");
	}
	
}
