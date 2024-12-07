package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
WebDriver driver;

public BasePage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//we can approach by 2 ways (without PageFatorya and PageFatory)
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
}
