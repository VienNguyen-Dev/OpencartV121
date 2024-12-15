package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
	WebDriverWait wait;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//input[@value='guest']")
	WebElement guestCheckoutOpt;

	@FindBy(xpath = "//input[@id='button-account']")
	WebElement btnContinue;

	@FindBy(xpath = "//input[@id='input-payment-firstname']")
	WebElement txtFirstName;
	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	WebElement txtLastName;
	@FindBy(xpath = "//input[@id='input-payment-email']")
	WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-payment-telephone']")
	WebElement txtTelephone;
	@FindBy(xpath = "//input[@id='input-payment-company']")
	WebElement txtConpany;
	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	WebElement txtAddress1;
	@FindBy(xpath = "//input[@id='input-payment-address-2']")
	WebElement txtAddress2;
	@FindBy(xpath = "//input[@id='input-payment-city']")
	WebElement txtCity;
	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	WebElement txtPostCode;
	@FindBy(xpath = "//select[@id='input-payment-country']")
	WebElement selCountry;
	@FindBy(xpath = "//select[@id='input-payment-zone']")
	WebElement selState;

	@FindBy(xpath = "//input[@id='button-payment-method']")
	WebElement btnCountinuePaymentMethod;
	@FindBy(xpath = "//input[@id='button-guest']")
	WebElement btnCountinueDetail;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chboxContition;

	public void setFirstName(String fname) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtFirstName));
			txtFirstName.sendKeys(fname);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting firstname: " + fname);
		}
	}

	public void setLastName(String lname) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtLastName));
			txtLastName.sendKeys(lname);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting lastname: " + lname);
		}

	}

	public void setEmail(String email) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtEmail));
			txtEmail.sendKeys(email);
		} catch (Exception e) {
			System.out.println("Error setting email: " + email);
		}
	}

	public void setPhone(String phone) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtTelephone));
			txtTelephone.sendKeys(phone);
		} catch (Exception e) {
			System.out.println("Error setting phone: " + phone);
		}
	}

	public void setCompany(String company) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtConpany));
			txtConpany.sendKeys(company);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting company: " + company);
		}

	}

	public void setAddress1(String addr1) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtAddress1));
			txtAddress1.sendKeys(addr1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting address1: " + addr1);
		}

	}

	public void setAddress2(String addr2) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtAddress2));
			txtAddress2.sendKeys(addr2);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting addres2: " + addr2);
		}

	}

	public void setCity(String city) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtCity));
			txtCity.sendKeys(city);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting City: " + city);
		}

	}

	public void setPostCode(String pCode) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtPostCode));
			txtPostCode.sendKeys(pCode);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting Post Code: " + pCode);
		}

	}

	public void selectCountryOption(String country) {

		try {
			wait.until(ExpectedConditions.visibilityOf(selCountry));
			 new Select(selCountry).selectByVisibleText(country);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting country: " + country);
		}
	}

	public void selectStateOption(String state) {
		try {
			wait.until(ExpectedConditions.visibilityOf(selState));
			 new Select(selState).selectByVisibleText(state);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setting State: " + state);
		}

	}

	public void chooseCheckoutOption(String option) {
		if (option.equals("Guest Checkout")) {
			wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutOpt));
			guestCheckoutOpt.click();
		}
	}

	public void clickCountinue() {
		btnContinue.click();
	}

	public void clickOnCountinueDetail() {
		btnCountinueDetail.click();
	}

	public void clickOnCountinuePaymentMethod() {
		btnCountinuePaymentMethod.click();
	}

	public void clickOnContition() {
		chboxContition.click();
	}

}
