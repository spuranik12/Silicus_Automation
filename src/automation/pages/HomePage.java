package automation.pages;

import org.openqa.selenium.WebDriver;

import automation.common.BasePage;

public class HomePage extends BasePage{
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
