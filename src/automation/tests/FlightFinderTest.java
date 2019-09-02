package automation.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.common.BaseTest;
import automation.pages.FlightFinderPage;
import automation.pages.LoginPage;

public class FlightFinderTest extends BaseTest {
	
	FlightFinderPage flightFinderPage;
	
	@BeforeClass
	public void FlightFinderPage()
	{
		parent = report.startTest("Flight Finder","Flight Finder Page Test");
	}
	
	@Test
	public void flightFinderTest() throws InterruptedException
	{
		//Arrangements
		child = report.startTest("Verify search flights functionality");
		parent.appendChild(child);
		new LoginPage(eventDriver, handler).login(properties.getProperty("username"), properties.getProperty("password"),child);
		flightFinderPage = new FlightFinderPage(eventDriver, handler);
		
		
		//Act
		Map<String, String> flightSearchDetails = flightFinderPage.getDetails();
		flightFinderPage.flightSearchDetails(flightSearchDetails, child);
		flightFinderPage.flightSearchPreferences(flightSearchDetails, child);
		
		//Assert
		Assert.assertTrue(flightFinderPage.verifyFlightSearch(flightSearchDetails,child));
	}
}
