package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductComparePage;
import pageobjects.ProductPage;
import pageobjects.SearchProductResultPage;
import testbase.BaseClass;
//Test Case
//1. Search Product
//2. Select Product with Product Name
//3. Click on Product Compare button
//4. Validate add product to compare 
//5. Navigate to Product Compare Page
//6. Navigate the exists of Product Compare page and Product Compare with proper product name in this page
public class TC007_CompareProductTest extends BaseClass {

	@Test(groups = "Master")
	public void testCompareProduct() {
		logger.info("Starting Testing TC007_ProductComparePage");
		logger.info("navigate to URL Application");
		HomePage hp = new HomePage(driver);
		String productName = p.getProperty("searchProductName");
		logger.info("Enter product name into Search Textbox "+ productName);
		hp.setSearchInput(productName);
		logger.info("Click on Search button");
		logger.info("Navigate to Search Result page");
		SearchProductResultPage sResultPage = hp.clickSearchButton();
		logger.info("Validate Product with " + productName + "is displayed in the Search Result page");
		Assert.assertEquals(sResultPage.isProductExists(productName), true, productName + "is not exists");
		logger.info("Select Product proper with "+ productName);
		ProductPage proPage = sResultPage.selectProduct(productName);
		logger.info("Click on Compare this product button");
		proPage.clickCompareProductButton();
		logger.info("Validate Product add successful to Product Compare page");
		
		Assert.assertEquals(proPage.checkConfirmMessage(), true);
		ProductComparePage proComparePage = proPage.clickOnCompareProductLink();
		logger.info("Validate exists Product Page");
		Assert.assertEquals(proComparePage.checkExistProductComparePage(), true);
		logger.info("Validate Product Name is displayed in the Product Compare Page");
		Assert.assertEquals(proComparePage.checkisDisPlayedProductName(), productName);
		logger.info("Finish Testing TC_007ProductComparePage");
	}
}
