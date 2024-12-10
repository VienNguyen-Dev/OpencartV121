package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchProductResultPage extends BasePage {
	public WebDriverWait wait;

	public SearchProductResultPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@id=\"content\"]//h1")
	WebElement pageHeader;

	@FindBy(xpath = "//*[@id='content']/div[3]//img")
	List<WebElement> listProducts;

	public boolean isSearchProductPageExists() {
		try {
			return pageHeader.getText().contains("Search -");
		} catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

	public boolean isProductExists(String productName) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(listProducts));
			for (WebElement p : listProducts) {
				if (p.getAttribute("title").equals(productName)) {
					return true;
				}
			}

		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public ProductPage selectProduct(String productName) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(listProducts));
			for (WebElement product : listProducts) {
				if (product.getAttribute("title").equals(productName)) {
					wait.until(ExpectedConditions.elementToBeClickable(product));
					product.click();
					
					return new ProductPage(driver);
				
				}
			}
			System.out.println("Not found product" + productName);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error selecting product " + e.getMessage());
		}
		return null;
	}

}
