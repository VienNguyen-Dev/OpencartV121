package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String firstname) {
		txtFirstname.sendKeys(firstname);

	}

	public void setLastName(String lastname) {

		txtLastname.sendKeys(lastname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}
	
	public void setTelephone(String phone) {
txtTelephone.sendKeys(phone);
	}
	
	

	public void setPassword(String password) {
		txtPassword.sendKeys(password);

	}
	
	public void setConfirmPassword(String confPassword) {
		txtConfirmPassword.sendKeys(confPassword);
	}

	public void clickCheckPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		
		//There are many solutions for we execute action click button Continue (using click(), submit(), Action, JavaScriptExecutor...)
		btnContinue.click();
		
	}

	public String getConfirmtionMsg() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {

			return e.getMessage();
		}
	}

}
