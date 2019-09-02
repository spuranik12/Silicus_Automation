package automation.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.common.BaseTest;
import automation.pages.LoginPage;
import automation.pages.PersonalDetailsPage;

public class PersonalDetailsTest extends BaseTest{
	
	PersonalDetailsPage personalDetails;
	@BeforeClass
	public void personalDetails(){
		parent = report.startTest("Personal Detail Page","Personal Detail Test");
	}
	
	@Test
	public void updatePersonalDetailsTest() throws InterruptedException{
		//Arrangement
		//testCaseName = "Update personal details";
		child = report.startTest("Update Personal Details with valid Input");
		parent.appendChild(child);
		new LoginPage(eventDriver, handler).login(properties.getProperty("username"), properties.getProperty("password"),child);
		personalDetails = new PersonalDetailsPage(eventDriver, handler);
		
		//Act
		personalDetails.clickOnPersonalDetailsTab(child);
		Map<String, String> userpersonalDetails = personalDetails.getPersonalDetails();
		personalDetails.fillPersonalDetails(userpersonalDetails,child);
		
		//Assert
		Assert.assertTrue(personalDetails.verifyUpdatedPersonalDetails(userpersonalDetails));
		//testlink.setResult(TestLinkAPIResults.TEST_PASSED);
	}
	
	@Test
	public void verifyUserAllDetailsAreFilled() throws InterruptedException{
		//Arrangement
		//testCaseName = "Verify all user details are filled 100%";
		child = report.startTest("Verify Login functaionlity with valid credentials");
		parent.appendChild(child);
		new LoginPage(eventDriver, handler).login(properties.getProperty("username"), properties.getProperty("password"),child);
		personalDetails = new PersonalDetailsPage(eventDriver, handler);
		
		//Act
		personalDetails.clickOnPersonalDetailsTab(child);
		
		//Assert
		Assert.assertTrue(personalDetails.verifyFilledUserdetails());
		//testlink.setResult(TestLinkAPIResults.TEST_PASSED);
	}
}
