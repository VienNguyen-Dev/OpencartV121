package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement msgHeading;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnLogout;

	public boolean isMyAcountPageExist() {
		// TODO Auto-generated method stub
		try {
			return msgHeading.isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public void clickLogout() {
		// TODO Auto-generated method stub
		btnLogout.click();

	}
}
