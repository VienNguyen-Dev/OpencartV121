package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasePage {
	WebDriverWait wait;

	public LogoutPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;

	public HomePage clickContinue() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
			btnContinue.click();
			return new HomePage(driver);
		} catch (Exception e) {
			System.out.println("Error occur while clicking on Continue button" + e.getMessage());
			return null;
		}
	}
}
