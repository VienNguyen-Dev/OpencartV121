package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductPage;
import pageobjects.SearchProductResultPage;
import testbase.BaseClass;

//1. Open application URL
//2. Enter product name into search text box
//3. Click on 'Search' button
//4. validate search product result page and product
//5. Select product to add 
//6. Set the desired quantity
//7. Add to product into cart
//8. Validate success message
public class TC005_AddToCartTest extends BaseClass {

	@Test(groups = "Master")
	public void testAddToCart() {

		logger.info("Navigation to the Application URL");

		try {
			logger.info("Starting Test TC005_AddToCartTest");
			HomePage hp = new HomePage(driver);
			logger.info("Navigation to Home Page");
			logger.info("Enter product name into search text box");

			String productName = p.getProperty("searchProductName");
			hp.setSearchInput(productName);
			logger.info("Click on 'Search' button");
			hp.clickSearchButton();

			logger.info("Waiting for navigation to Search Product Result Page");
			SearchProductResultPage spPage = hp.clickSearchButton();

			// Validate Search Product Result Page and product results
			if (spPage.isProductExists(productName)) {
				logger.info("Found the product in product results: " + productName);
				logger.info("Selecting product");
				ProductPage productPage = spPage.selectProduct(productName);
				logger.info("Selected product " + productName);

				productPage.setProductQuantity("2");
				logger.info("Set product quantity");

				productPage.clickButtonAddToCart();
				logger.info("Click on 'Add to Cart' for product " + productName);
				logger.info("Validating Success message");
				Assert.assertTrue(productPage.checkConMsg(), "Success message don't displayed");
				logger.info("Success message verified successful.");

			} else {
				logger.error("Product not found in search results " + productName);
				logger.debug("Product not found in search results");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("Finished Test TC005_AddToCartTest ");

	}
}
