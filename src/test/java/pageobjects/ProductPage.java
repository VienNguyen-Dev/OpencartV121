package pageobjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
	WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//input[@id='input-quantity']")
	WebElement txtQuantity;

	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement btnAddToCart;

	@FindBy(xpath = "//div[contains(text(),'Success: You have added')]")
	WebElement successMsg;

	@FindBy(xpath = "//div[@id='cart']")
	WebElement btnItems;

	@FindBy(xpath = "//strong[normalize-space()='View Cart']")
	WebElement lnkShoppingCart;
	
	@FindBy(xpath = "//button[2]")
	WebElement btnCompareProduct;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement txtConfirmMessage;
	
	@FindBy(xpath = "//a[normalize-space()='product comparison']")
	WebElement lnkProductCompare;
	
	

	public void setProductQuantity(String quantity) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtQuantity));
			txtQuantity.clear();
			txtQuantity.sendKeys(quantity);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting product quantity " + e.getMessage());
		}
	}

	public void clickButtonAddToCart() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));
			btnAddToCart.click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while click on 'Add To Cart' button " + e.getMessage());
		}
	}

	public boolean checkConMsg() {

		try {
			wait.until(ExpectedConditions.visibilityOf(successMsg));
			return successMsg.isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickItemNavigateToCart() {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnItems));
			btnItems.click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Unable to click Item button "+ e.getMessage());
		}

	}

	
	public ShoppingCartPage clickViewCartLink() {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(lnkShoppingCart));
			lnkShoppingCart.click();
			return new ShoppingCartPage(driver);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Unable to click shopping cartLink "+ e.getMessage());
			return null;
		}
	}
	
	public void clickCompareProductButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCompareProduct));
		btnCompareProduct.click();
	}
	
	public ProductComparePage clickOnCompareProductLink() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lnkProductCompare));
			lnkProductCompare.click();
			return new ProductComparePage(driver);
		} catch (Exception e) {
			System.out.println("Enable to Click on Compare Product Link" + e.getMessage());
			return null;
		}
	}
	
	public boolean checkConfirmMessage() {

		try {
			wait.until(ExpectedConditions.visibilityOf(txtConfirmMessage));
			return txtConfirmMessage.isDisplayed();
		} catch (Exception e) {
			System.out.println("Text confirm message error" + e.getMessage());
			return false;
		}
	}
}
