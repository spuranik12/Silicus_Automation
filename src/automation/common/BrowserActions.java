package automation.common;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {
	WebDriver driver;

	public BrowserActions(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDisplayed(WebElement element){
		waitForElementToBeClickable(element);
		return element.isDisplayed();
	}
	
	public boolean isEnable(WebElement element){
		waitForElementToBeClickable(element);
		return element.isEnabled();
	}
	
	public void sendKeys(WebElement element, String text){
		waitForElementToBeClickable(element);
		//element.clear();
		element.sendKeys(text);
	}
	
	public void selectDropdownByVisibleText(WebElement element, String text){
		waitForElementToBeClickable(element);
		new Select(element).selectByVisibleText(text);
	}
	
	public void selectDropdownByIndex(WebElement element, int index){
		waitForElementToBeClickable(element);
		new Select(element).selectByIndex(index);
	}
	
	public void selectDropdownByValue(WebElement element, String value){
		waitForElementToBeClickable(element);
		new Select(element).selectByValue(value);
	}
	
	public void waitForElementToBeClickable(WebElement element){
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementRefreshed(WebDriver driver, WebElement element, int secondsToWait){
		try{
			this.waitForJavaScriptAndJqueryToLoad(driver, secondsToWait);
			element = getWebElementReInitialized(driver, element);
		}

		catch (Exception st){
		}
		return element;
	}
	
	public void waitForJavaScriptAndJqueryToLoad(WebDriver driver, long timeInSeconds){
		new WebDriverWait(driver, timeInSeconds).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
			return isJavaScriptRunning(driver);
			}
		});
	}
	
	public WebElement getWebElementReInitialized(WebDriver driver, WebElement element){
		return driver.findElement(this.decomposeWebElement(element));
	}
	
	public Boolean isJavaScriptRunning(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Boolean) js.executeScript("return jQuery.active == 0");
	}
	
	public By decomposeWebElement(WebElement element){
		
		By byObject;
		String locator = this.getLocator(element);
		String selector = this.getSelector(element);

		switch(locator.toLowerCase()){
			case "id":
				byObject = By.id(selector);
				break;
			case "css":
				byObject = By.cssSelector(selector);
				break;
			case "classname":
				byObject = By.className(selector);
				break;
			case "linktext":
				byObject = By.linkText(selector);
				break;
			case "tagname":
				byObject = By.tagName(selector);
				break;
			case "xpath":
				byObject = By.xpath(selector);
				break;
			case "partiallinktext":
				byObject = By.partialLinkText(selector);
				break;
			case "name":
				byObject = By.name(selector);
				break;
			default:
				throw new NoSuchElementException("No action defined for this type of web element or web element is incorrect");
		}

		return byObject;
	}
	
	public String getLocator(WebElement el){
		String[] thirdSplit = this.extractor(el)[0].split(" ");
		return thirdSplit[1].trim();
	}
	
	public String getSelector(WebElement el){
		return removeLastCharFromString(this.extractor(el)[1].trim());
	}

	public String[] extractor(WebElement el){
		String[] firstSplit= el.toString().split("->");
		String[] secondSplit =firstSplit[1].split(": ");
		return secondSplit;
	}
	
	public String removeLastCharFromString(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length()-1)==']') {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	public void waitForElementToBeInVisible(WebElement element){
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(element));
	}
	
	public boolean isWebelementVisible(WebElement element){
		return element.isDisplayed();
	}
	
	//Method can resove issue: Element not clickable
	
	public void clickOnElementWhenElementNotClickable(WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
}
