package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.AccountRegistrationPage;
import pageobjects.CheckoutPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.LogoutPage;
import pageobjects.MyAccountPage;
import pageobjects.ProductPage;
import pageobjects.SearchProductResultPage;
import pageobjects.ShoppingCartPage;
import testbase.BaseClass;

//* 
//* Steps:
//* 1) Perform Account Registration
//* 2) Logout after successful registration
//* 3) User Login with the registered email
//* 4) Search product and add to cart
//* 5) Verify Shopping Cart contents
//* 6) Perform Checkout process  // feature not available since it is demo site
//*/
public class TC006_EndToEndTest extends BaseClass {

	@Test(groups = "Master")
	public void executeEndtoEnd() throws InterruptedException {
		logger.info("Navigation in the URL application");

		// Using Soft Assert to execute end to end test

		SoftAssert softAssert = new SoftAssert();
//		Step1: Perform the registration
		String email = performRegistration(softAssert);

//		Step2: Perform logout
		performLogout(softAssert);
		
		//Step3: Perform login with registered email
		performLogin(email, softAssert);
		
		//Step4: Perform search product and add to cart
		addProductTocart(softAssert);
		
		//Step5: Verify Shopping cart contents
		verifyShoppingCart(softAssert);
		
		//Step6: Perform checkout process
		performCheckout(softAssert);
		
		

	}

	private void performCheckout(SoftAssert softAssert) throws InterruptedException {
		logger.info("Starting perform checkout prcess");
		ShoppingCartPage shcPage = new ShoppingCartPage(driver);
		logger.info("Click on 'Checkout' button and navigate to Checkout Page");
		CheckoutPage checkoutPage = shcPage.clickBtnCheckout();
		checkoutPage.chooseCheckoutOption("Guest Checkout");
		logger.info("Click on Countinue");
		checkoutPage.clickCountinue();
		//Provide payment information 
		logger.info("Provide payment information");
		fillPaymentInformation();
		logger.info("Click on Countinue in step Payment Detail");
		checkoutPage.clickOnCountinueDetail();
		Thread.sleep(2000);
		logger.info("Click on checkbox Condition& policy in Payment Method");
		checkoutPage.clickOnContition();
		logger.info("Click on Countinue in Payment Method");
		checkoutPage.clickOnCountinuePaymentMethod();
		
		//Continue part is limit cause this is demo version
	}

	public void fillPaymentInformation() throws InterruptedException {
		CheckoutPage chkoutPage = new CheckoutPage(driver);
		chkoutPage.setFirstName("Test");
		Thread.sleep(1000);
		chkoutPage.setLastName("Test");
		Thread.sleep(1000);
		chkoutPage.setEmail("test@gmail.com");
		Thread.sleep(1000);
		chkoutPage.setPhone("948965037483");
		Thread.sleep(1000);
		chkoutPage.setCompany("Test");
		Thread.sleep(1000);
		chkoutPage.setAddress1("Test");
		Thread.sleep(1000);
		chkoutPage.setAddress2("Test");
		Thread.sleep(1000);
		chkoutPage.setCity("Ho Chi Minh");
		Thread.sleep(1000);
		chkoutPage.setPostCode("1234567");
		Thread.sleep(1000);
		chkoutPage.selectCountryOption("Viet Nam");
		Thread.sleep(1000);
		chkoutPage.selectStateOption("Ho Chi Minh City");
		Thread.sleep(1000);
	}

	private void verifyShoppingCart(SoftAssert softAssert) throws InterruptedException {
		logger.info("Starting Verify Shopping cart contents");
		logger.info("Verify items in shopping cart");
		ProductPage proPage = new ProductPage(driver);
		logger.info("Click on shopping cart from confirm message is displayed");
		proPage.clickItemNavigateToCart();
		
		logger.info("Click on 'View Cart' link and navigate to Shopping Cart Page");
		ShoppingCartPage shcPage = proPage.clickViewCartLink();
		Thread.sleep(3000);
		//Validate total price in the shopping cart page
		String actualTotalPrice = shcPage.getTotalPrice();
		String expectedTotalPrice = p.getProperty("totalPrice");
		logger.info("Total price in shopping cart is: "+ actualTotalPrice);
		softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "Error occur while calculation total price");
		
	}

	private void addProductTocart(SoftAssert softAssert) throws InterruptedException {

		logger.info("Starting search product and Add to cart");
		HomePage hp = new HomePage(driver);
		String productName = p.getProperty("searchProductName");
		logger.info("Enter product name into search Text box with" + productName);
		hp.setSearchInput(productName);
		logger.info("Click on 'Search' button");
		SearchProductResultPage spPage = hp.clickSearchButton();
		logger.info("Navigat to Search Result Page");
		//Validate search result page visible 
		boolean searchResultPageVisible = spPage.isSearchProductPageExists();
		softAssert.assertTrue(searchResultPageVisible, "Search Result Page don't exists");
		logger.info("Search Result Page don't exists");
		
		logger.info("Validate Product visible in the Search Result Page");
		boolean productVisible = spPage.isProductExists(productName);
//		softAssert.assertTrue(productVisible, productName+ " Product don't visible in the page ");
//		logger.info(productVisible ? "Product is displayed in the page" : "Product don't exists");
//		
//		logger.info("Select Product with " + productName);
		ProductPage proPage = null;
		//Check if product name displayed in the Search Result page and add it into shopping cart
		if(productVisible) {
			 proPage = spPage.selectProduct(productName);
			 logger.info("Navigate to Product Page");
			 logger.info("Provide Product quantity and add product into shopping cart");
			 proPage.setProductQuantity(p.getProperty("productQuantity"));
			 logger.info("Click on 'Add To Cart' button");
			 proPage.clickButtonAddToCart();
		}
		//Validate add product into shopping cart
		Thread.sleep(3000);
		logger.info("Product add to cart "+ proPage.checkConMsg());
		softAssert.assertTrue(proPage.checkConMsg(), "Add product "+ productName +"fail");
		
	
	}

	public void performLogin(String email, SoftAssert softAssert) {
		logger.info("Login into account with "+ email);
		HomePage hp = new HomePage(driver);
		logger.info("Click on 'My Account' Link");
		hp.clickMyAccount();
		logger.info("Navigate to Login Page");
		logger.info("Click on 'Login' Link");
		LoginPage lp = hp.clickLogin(); 
		logger.info("Provide account information");
		lp.setEmail(email);
		lp.setPassword("test123");
		logger.info("Click on 'Continue' button");
		//Validate 
		MyAccountPage maccPage = new MyAccountPage(driver);
		boolean accountPageVisible = maccPage.isMyAcountPageExist();
		logger.info("Validate login visible");
		softAssert.assertTrue(accountPageVisible, "Login fail with "+ email);
		logger.info(accountPageVisible ? "Loggin Successful. Navigate to Account Page": "Login Verification Fail.");
	}
	public void performLogout(SoftAssert softAssert) throws InterruptedException {
		
		logger.info("Page current is My Account Page");
		MyAccountPage maccPage = new MyAccountPage(driver);
		LogoutPage logoutPage = maccPage.clickLogout();
			logger.info("Click on 'Continue' button to return Home Page");
			HomePage postLogoutHomePage = logoutPage.clickContinue();
			//Validate 
			logger.info("Validate home page exists");
			boolean homePageVisible = postLogoutHomePage.isHomePageExists();
			softAssert.assertTrue(homePageVisible, "Logout Fail. Home Page is not displayed");
			logger.info(homePageVisible ? "Logout successful. Navigate to Home Page": "Logout verification Fail");
			Thread.sleep(3000);
			
	}

	public String performRegistration(SoftAssert softAssert) throws InterruptedException {
		HomePage hp = new HomePage(driver);
		logger.info("Navigation in the Home Page");
		logger.info("Click on My Account link");
		hp.clickMyAccount();
		logger.info("CLick on Register Link");
		AccountRegistrationPage accRePage = hp.clickRegister();
		logger.info("Navitation in the Register Page ");
		logger.info("Provide account information");
		
		
		String email = randomeString().toLowerCase() + "@gmail.com";
		String firstname = randomeString().toUpperCase();
		String lastname = randomeString().toUpperCase();		
		
		accRePage.setFirstName(firstname);
		accRePage.setLastName(lastname);
		accRePage.setEmail(email);
		accRePage.setTelephone("0958549837");
		accRePage.setPassword("test123");
		accRePage.setConfirmPassword("test123");

		logger.info("Click on Policy checkbox");
		accRePage.clickCheckPolicy();
		logger.info("Click on Continue button");
		accRePage.clickContinue();
		Thread.sleep(3000);
		// Validate confirm registration

		String confirmRegistration = accRePage.getConfirmtionMsg();
		logger.info("Validate confirm registration " + confirmRegistration);
		softAssert.assertEquals(confirmRegistration, "Your Account Has Been Created!");
		return email;
	}

}
