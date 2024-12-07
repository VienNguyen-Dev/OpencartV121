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
	public void clickMyAccount() {
		// TODO Auto-generated method stub
		lnkMyAccount.click();

	}

	public void clickRegister() {
		// TODO Auto-generated method stub
		lnkRegister.click();
	}
	
	public void clickLogin() {
		// TODO Auto-generated method stub
		lnkLogin.click();

	}

}
