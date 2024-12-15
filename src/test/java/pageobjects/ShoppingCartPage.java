package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage {
	WebDriverWait wait;
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]")
	WebElement txtTotalPrice;
	
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	WebElement btnCheckout;
	
	public String getTotalPrice() {
		return txtTotalPrice.getText();
	}
	
	public CheckoutPage clickBtnCheckout() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnCheckout));
			btnCheckout.click();
			return new CheckoutPage(driver);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Unable to click Checkout button" + e.getMessage());
			return null;
		}
		
	}
}
