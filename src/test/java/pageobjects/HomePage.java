package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
WebDriverWait await;
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.await = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement txtboxSearch;



	public void clickMyAccount() {
		// TODO Auto-generated method stub
		lnkMyAccount.click();

	}

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;

	public void clickRegister() {
		// TODO Auto-generated method stub
		lnkRegister.click();
	}

	public void clickLogin() {
		// TODO Auto-generated method stub
		lnkLogin.click();

	}
	public void setSearchInput(String searchProductName) {
		// TODO Auto-generated method stub
		txtboxSearch.sendKeys(searchProductName);
		
	}

	public SearchProductResultPage clickSearchButton() {
		// TODO Auto-generated method stub
		try {
			await.until(ExpectedConditions.elementToBeClickable(btnSearch));
			btnSearch.click();
			return new SearchProductResultPage(driver);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception occur while clicking Search button" + e.getMessage());
			return null;
		}

	}

}
