package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductComparePage extends BasePage {
	public WebDriverWait wait;

	public ProductComparePage(WebDriver driver) {

		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//h1[normalize-space()='Product Comparison']")
	WebElement headerPage;

	@FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")
	WebElement productName;

	public boolean checkExistProductComparePage() {
		try {
			return headerPage.isDisplayed();
		} catch (Exception e) {
			System.out.println("Product Compare page is not exists");
			return false;
		}
	}

	public String checkisDisPlayedProductName() {

		try {
			wait.until(ExpectedConditions.visibilityOf(productName));
			return productName.getText();
		} catch (Exception e) {
			System.out.println("Product name is not displayed in the page");
			return null;
		}
	}

}
