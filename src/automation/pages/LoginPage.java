package automation.pages;

import org.apache.poi.poifs.property.Child;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import automation.common.AutoLogger;
import automation.common.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(name = "userName")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(linkText = "PROFILE")
	WebElement verifyLoggedInUser;
	
	@FindBy(id = "UserName")
	WebElement wmUsernameField;
	
	@FindBy(id = "btnLogin")
	WebElement wmSignInBtn;
	
	@FindBy(id = "btnSubmit")
	WebElement wmLoginBtn;
	
	@FindBy(css = "strong")
	WebElement wmLoggedInUser;
	
	
	public LoginPage(WebDriver driver, AutoLogger handler){
		super(driver);
		PageFactory.initElements(driver, this);
		super.handler = handler;
		handler.setCurrentPageClass(this.getClass());
	}
	
	public void login(String username, String password,ExtentTest child) throws InterruptedException{
		actions.sendKeys(this.username, username);
		Thread.sleep(1000);
		Reporter.log("Step 1: UserName entered: "+username);
		child.log(LogStatus.INFO,"Step 1: UserName entered:"+username);
		actions.sendKeys(this.password, password);
		Thread.sleep(1000);
		Reporter.log("Step 1: Password entered: "+password);
		child.log(LogStatus.INFO,"Step 2 : Password entered:"+password);
		loginBtn.click();
		child.log(LogStatus.INFO,"Step 3 : Clicked on Login Button ");
		Thread.sleep(2000);
	}
	
	public boolean isUserLoggedIn() throws InterruptedException{
		Thread.sleep(3000);
		return actions.isDisplayed(verifyLoggedInUser);
	}
}
