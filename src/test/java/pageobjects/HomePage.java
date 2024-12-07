package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);

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

	public void clickSearchButton() {
		// TODO Auto-generated method stub
		btnSearch.click();

	}

}
