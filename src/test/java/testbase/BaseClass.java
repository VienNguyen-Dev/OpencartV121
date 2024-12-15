package testbase;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	public TakesScreenshot ts;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws Exception {

		logger = LogManager.getLogger(this.getClass());
		FileReader fr = new FileReader(new File(".//src//test//resources//config.properties"));
		p = new Properties();
		p.load(fr);

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			String hubUrl = "http://localhost:4444";

			DesiredCapabilities cap = new DesiredCapabilities();
			// check os

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS");
				return;
			}

			// check browser

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "adge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL(hubUrl), cap);
		} 
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;

			default:
				System.out.println("Invalid browser name....");
				return;
			}

		}

//		String url = p.getProperty("appURL");
//		String url = p.getProperty("appURL2");
		String url = p.getProperty("appURL3");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() throws Exception {
		driver.quit();
	}

	public String randomeString() {

		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}

	public String randomeNumber() {

		String generateNumber = RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}

	public String randomeStringAndNumber() {
		String generateString = RandomStringUtils.randomAlphabetic(4);
		String generateNumber = RandomStringUtils.randomNumeric(4);

		return (generateString + "@" + generateNumber);
	}

	public String captureScreen(String tname) {
		// TODO Auto-generated method stub
		ts = (TakesScreenshot) driver;
		String timeStamp = new SimpleDateFormat("yyyyMMddhmmss").format(new Date());
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}
}
