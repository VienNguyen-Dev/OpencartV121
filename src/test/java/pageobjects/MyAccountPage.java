package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {
	WebDriverWait wait;

	public MyAccountPage(WebDriver driver) {

		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

	public LogoutPage clickLogout() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
			btnLogout.click();
			return new LogoutPage(driver);
		} catch (Exception e) {
			System.out.println("Error occur while clicking on Logout button" + e.getMessage());
			return null;
		}

	}
}
