package automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import automation.utilities.PropertyDictionary;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;


public class BaseTest {
	protected Properties properties;
	Map<String, String> propertiesData = new HashMap<String, String>();
	protected WebDriver driver;
	protected EventFiringWebDriver eventDriver;
	protected AutoLogger handler;
	//Extent Report
	public ExtentReports report; 
	public  ExtentTest parent ;
	public  ExtentTest child ;
	private boolean isMobile;
	private AppiumDriver<WebElement> mDriver;
	DesiredCapabilities caps = new DesiredCapabilities();

	public Properties getPropertiesData(){
		try{
			properties = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/input data/config.properties");
			properties.load(inputStream);
			return properties;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			return properties;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return properties;
		}
	}

	public void launchBrowser() throws MalformedURLException{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("no-sandbox");
		driver = new ChromeDriver();
		eventDriver = new EventFiringWebDriver(driver);
		handler = new AutoLogger();
		eventDriver.register(handler);
		eventDriver.manage().window().maximize();
		eventDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		eventDriver.get(properties.getProperty("baseURL"));
	}

	public void Browser(String browser) throws MalformedURLException, Exception {

		switch (browser) {
		case "chrome":
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("ignore-certificate-errors");
			options.addArguments("no-sandbox");
			options.addArguments("disable-infobars");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("selenide.browser", "chrome");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.SEVERE);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			break;
		case "firefox":
			caps = DesiredCapabilities.firefox();
		case "appiumandroid":
			caps = DesiredCapabilities.android();
			caps.setCapability("version", "5.0.2");
			caps.setCapability("deviceName", "Android");
			caps.setCapability("platformName", "Android");
			caps.setCapability("browserName", "chrome");
			driver = new RemoteWebDriver(new URL(PropertyDictionary.map.get("gridUrl")), caps);
			isMobile = true;
			break;

		case "appiumsafari":
			caps = DesiredCapabilities.android();
			caps.setCapability("platformVersion", "9.3.3");
			caps.setCapability("platformName", "iOS");
			caps.setCapability("deviceName", "Silicus iPhone 6-3");
			caps.setCapability("platform", "MAC");
			caps.setCapability("automationName", "Appium");
			caps.setCapability("udid", "785894383e2f3b59f80f381c8820c5c76c620a2a");
			caps.setCapability("app", "com.lampsplus.app");
			caps.setCapability("safariAllowPopups", false);
			caps.setCapability("safariIgnoreFraudWarning", true);
			caps.setCapability("locationContextEnabled", true);
			caps.setCapability("webStorageEnabled", true);
			mDriver = new IOSDriver<WebElement>(new URL("http://10.55.0.137:4444/wd/hub"), caps);
			Set<String> contextNames = mDriver.getContextHandles();
			for (String contextName : contextNames) {
				if (contextName.contains("WEBVIEW")) {
					mDriver.context(contextName);
					this.driver = mDriver;
				}
			}
			
			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(mDriver);
			break;
		}
	}

	@BeforeSuite

	public void validateExtentReportFile (){
		File f = new File((System.getProperty("user.dir")+"/test-output/ExtentReport.html"));
		if(f.exists())
			f.delete();
	}

	@BeforeClass
	public void readPropertiesData() throws MalformedURLException{
		getPropertiesData();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",false);
	}

	public void quitBrowser(){
		if(eventDriver != null)
			eventDriver.quit();
	}

	@BeforeMethod
	public void getEnviromentReady() throws MalformedURLException{
		launchBrowser();
	}

	@AfterMethod
	public void quitBrowserAndUpdateTestlinkResult(ITestResult result) throws TestLinkAPIException{
		if (result.getStatus() == ITestResult.FAILURE) {
			child.log(LogStatus.FAIL, "Test Failed");
			child.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			child.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			child.log(LogStatus.PASS, "Test passed");
		}
		quitBrowser();
		report.endTest(child);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("****************************" + parent.getDescription());
		report.endTest(parent);
		report.flush();
		report.close();
	}

}
