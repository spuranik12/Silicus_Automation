package automation.common;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected BrowserActions actions;
	protected WebDriver driver;
	protected AutoLogger handler;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		actions = new BrowserActions(driver);
	}
	
}
