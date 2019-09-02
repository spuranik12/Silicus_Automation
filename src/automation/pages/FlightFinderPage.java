package automation.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import automation.common.AutoLogger;
import automation.common.BasePage;

public class FlightFinderPage extends BasePage{
	
	@FindBy(css="[name='tripType']")
	private List<WebElement> tripTypesOptions;
	
	@FindBy(css="[value='oneway']")
	private WebElement oneWayType;
	
	@FindBy(css="[name='passCount']")
	private WebElement passengersDropdown;
	
	@FindBy(xpath="//select[@name='fromPort']")
	private WebElement departingFromDropdown;
	
	@FindBy(name="fromMonth")
	private WebElement departFromMonthDropdown;
	
	@FindBy(name="fromDay")
	private WebElement departFromDayDropdown;
	
	@FindBy(name="toPort")
	private WebElement arrivingInDropdown;
	
	@FindBy(css="[name='servClass']")
	private List<WebElement> serviceClassesOptions;
	
	@FindBy(name="airline")
	private WebElement airlineDropdown;
	
	@FindBy(name="findFlights")
	private WebElement continueBtn;
	
	@FindBy(css="[class='title'] b")
	private List<WebElement> listOftitles;
	
	@FindBy(css="[name='outFlight']")
	private List<WebElement> preferedFlights;
	
	public FlightFinderPage(WebDriver driver, AutoLogger handler)
	{
		super(driver);
		PageFactory.initElements(driver, this);
		super.handler = handler;
		handler.setCurrentPageClass(this.getClass());
	}
	
	public void flightSearchDetails(Map<String, String> details, ExtentTest child) throws InterruptedException
	{
		Thread.sleep(1000);
		int totalTripTypes = tripTypesOptions.size();
		for(int i=0; i<totalTripTypes; i++){
			String trip = tripTypesOptions.get(i).getAttribute("value");
			//System.out.println(text);
			if(trip.equalsIgnoreCase((details.get("Trip")))){
				tripTypesOptions.get(i).click();
			}
		}
		Thread.sleep(1000);
		Reporter.log("Step 4: Clicked on One way trip");
		child.log(LogStatus.INFO,"Step 4: Clicked on One way trip.");
		actions.selectDropdownByValue(passengersDropdown, details.get("Passengers"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(departingFromDropdown, details.get("Departing From"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(departFromMonthDropdown, details.get("Departing Month"));
		Thread.sleep(1000);
		actions.selectDropdownByValue(departFromDayDropdown, details.get("Departing Date"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(arrivingInDropdown, details.get("Arriving In"));
		Thread.sleep(1000);
		Reporter.log("Step 4: Selected all other details");
		child.log(LogStatus.INFO,"Step 4: Selected all other required details.");
	}
	
	public void flightSearchPreferences(Map<String, String> details, ExtentTest child) throws InterruptedException
	{
		Thread.sleep(1000);
		int totalServiceClasses = serviceClassesOptions.size();
		for(int i=0; i<totalServiceClasses; i++){
			String service = serviceClassesOptions.get(i).getAttribute("value");
			if(service.equalsIgnoreCase(details.get("Service Class"))) {
				serviceClassesOptions.get(i).click();
			}
		}
		Thread.sleep(1000);
		Reporter.log("Step 6: Clicked on service class");
		child.log(LogStatus.INFO,"Step 6: Clicked on service class.");
		actions.selectDropdownByVisibleText(airlineDropdown, details.get("Airline Preference"));
		Thread.sleep(1000);
		actions.clickOnElementWhenElementNotClickable(continueBtn);
		Thread.sleep(1000);
		Reporter.log("Step 7: Clicked on Continue Button");
		child.log(LogStatus.INFO,"Step 7: Clicked on Continue Button");
		Thread.sleep(2000);
	}
	
	public Map<String, String> getDetails() throws InterruptedException
	{
		Thread.sleep(1000);
		Map<String, String> details = new HashMap<String, String>();
		details.put("Trip", "oneway");
		details.put("Passengers", "2");
		details.put("Departing From", "London");
		details.put("Departing Month", "November");
		details.put("Departing Date", "10");
		details.put("Arriving In", "Paris");
		details.put("Service Class", "Business");
		details.put("Airline Preference", "Blue Skies Airlines");
		details.put("Title", "London to Paris");
		details.put("Preferred Flights", "Blue Skies Airlines");
		Thread.sleep(1000);
		return details;
	}
	
	public boolean verifyFlightSearch(Map<String, String> details, ExtentTest child) throws InterruptedException
	{
		Thread.sleep(2000);
		int totalTitles = preferedFlights.size();
		for(int i=0; i<totalTitles; i++)
		{
			String titleText=preferedFlights.get(i).getAttribute("value");
			if(titleText.contains(details.get("Preferred Flights")))
			{
				return true;
			}
			Thread.sleep(1000);
			Reporter.log("Step 8: Select flights page is displayed");
			child.log(LogStatus.INFO,"Step 8: Preferred Flights are displayed.");
		}
		
		return false;
	}

}
