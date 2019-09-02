package automation.pages;

import java.util.HashMap;
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

public class PersonalDetailsPage extends BasePage{

	public PersonalDetailsPage(WebDriver driver, AutoLogger handler) {
		super(driver);
		PageFactory.initElements(driver, this);
		super.handler = handler;
		handler.setCurrentPageClass(this.getClass());
	}
	
	@FindBy(id = "PD_txtFatherName")
	private WebElement fatherNameTextBox;
	
	@FindBy(id = "PD_ddlNationality")
	private WebElement natinalityDropDown;
	
	@FindBy(id = "PD_ddlBloodGroup")
	private WebElement bloodGroupDropDown;
	
	@FindBy(id = "PD_ddlMaritalStatus")
	private WebElement maritalStatusDropDown;
	
	@FindBy(id = "PD_txtSpouseName")
	private WebElement spouseNameTextBox;
	
	@FindBy(id = "PD_ddlMotherTongue")
	private WebElement motherTongueDropDown;
	
	@FindBy(id = "PD_txtPANNumber")
	private WebElement panNumberTextBox;
	
	@FindBy(id = "PD_txtUANNumber")
	private WebElement uanNumberTextBox;
	
	@FindBy(id = "PD_txtAadharCardNumber")
	private WebElement aadharCardNumberTextBox;
	
	@FindBy(id = "PD_lstPlaceofBirthState")
	private WebElement birthPlaceStateDropDown;
	
	@FindBy(id = "PD_lstPlaceofBirthCity")
	private WebElement birthPlaceCityDropDown;
	
	@FindBy(id = "PD_txtPassportNumber")
	private WebElement passportNumberTextBox;
	
	@FindBy(id = "PD_txtPassportExpiryDate")
	private WebElement passportExpiryDateCalender;
	
	@FindBy(id = "PD_SaveId")
	private WebElement saveDetailsButton;
	
	@FindBy(id = "PD_txtNameOnPassport")
	private WebElement nameOnPassportTextBox;
	
	@FindBy(xpath = "/html/body/div[1]/div/nav/ul/li[2]/a")
	private WebElement personalDetailsTab;
	
	@FindBy(css = "div[class='progress-bar progress-bar-info'] > .progress-value")
	private WebElement personalDetailsProgressBar;
	
	@FindBy(css = "div[class='progress-bar progress-bar-success'] > .progress-value")
	private WebElement contactDetailsProgressBar;
	
	@FindBy(css = "div[class='progress-bar progress-bar-warning'] > .progress-value")
	private WebElement educationalDetailsProgressBar;
	
	@FindBy(css = "div[class='progress-bar progress-bar-danger'] > .progress-value")
	private WebElement employmentDetailsProgressBar;
	
	public void clickOnPersonalDetailsTab(ExtentTest child) throws InterruptedException{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		personalDetailsTab.click();
		Thread.sleep(2000);
		Reporter.log("Step 2:Clicked on Personal Details Tab");
		child.log(LogStatus.INFO,"Clicked on Personal Details Tab");
	
		
	}
	
	public void fillPersonalDetails(Map<String, String> personalDetails,ExtentTest child) throws InterruptedException{
		Thread.sleep(1000);
		actions.sendKeys(fatherNameTextBox, personalDetails.get("FatherName"));
		Thread.sleep(1000);
		Reporter.log("Step 1: FatherName enterd:"+personalDetails.get("FatherName"));
		child.log(LogStatus.INFO,"UserName enterd:"+personalDetails.get("FatherName"));
		actions.selectDropdownByVisibleText(natinalityDropDown, personalDetails.get("Nationality"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(bloodGroupDropDown, personalDetails.get("BloodGroup"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(maritalStatusDropDown, personalDetails.get("MaritalStatus"));
		Thread.sleep(1000);
		actions.sendKeys(spouseNameTextBox, personalDetails.get("SpouseName"));
		Thread.sleep(1000);
		Reporter.log("Step 2: spouseNameText enterd:"+personalDetails.get("SpouseName"));
		child.log(LogStatus.INFO,"UserName enterd:"+personalDetails.get("SpouseName"));
		actions.selectDropdownByVisibleText(motherTongueDropDown, personalDetails.get("MotherTongue"));
		Thread.sleep(1000);
		actions.sendKeys(panNumberTextBox, personalDetails.get("PanNumber"));
		Thread.sleep(1000);
		Reporter.log("Step 3: PanNumber enterd:"+personalDetails.get("PanNumber"));
		child.log(LogStatus.INFO,"PanNumber enterd:"+personalDetails.get("PanNumber"));
		actions.sendKeys(aadharCardNumberTextBox, personalDetails.get("AadharCardNumber"));
		Thread.sleep(1000);
		Reporter.log("Step 4: aadharCardNumber enterd:"+personalDetails.get("AadharCardNumber"));
		child.log(LogStatus.INFO,"aadharCardNumber enterd:"+personalDetails.get("AadharCardNumber"));
		actions.sendKeys(passportNumberTextBox, personalDetails.get("PassPortNumber"));
		Thread.sleep(1000);
		Reporter.log("Step 4: passportNumber enterd:"+personalDetails.get("PassPortNumber"));
		child.log(LogStatus.INFO,"passportNumber enterd:"+personalDetails.get("PassPortNumber"));
		actions.sendKeys(nameOnPassportTextBox, personalDetails.get("NameOnPassword"));
		Thread.sleep(1000);
		Reporter.log("Step 4: aadharCardNumber enterd:"+personalDetails.get("NameOnPassword"));
		child.log(LogStatus.INFO,"UserName enterd:"+personalDetails.get("NameOnPassword"));
		actions.selectDropdownByVisibleText(birthPlaceStateDropDown, personalDetails.get("BirthState"));
		Thread.sleep(1000);
		actions.selectDropdownByVisibleText(birthPlaceCityDropDown, personalDetails.get("BirthCity"));
		Thread.sleep(1000);
		actions.getWebElementReInitialized(driver, saveDetailsButton);
		Thread.sleep(1000);
		actions.clickOnElementWhenElementNotClickable(saveDetailsButton);
		Reporter.log("Step 5: Clicked on Save Button");
		child.log(LogStatus.INFO,"Clicked on Save Button");
		Thread.sleep(2000);
	}
	
	public Map<String, String> getPersonalDetails() throws InterruptedException{
		Thread.sleep(1000);
		Map<String, String> personalDetails = new HashMap<String, String>();
		personalDetails.put("FatherName", "testFName");
		personalDetails.put("Nationality", "India");
		personalDetails.put("BloodGroup", "A+");
		personalDetails.put("SpouseName", "testSName");
		personalDetails.put("MaritalStatus", "Married");
		personalDetails.put("MotherTongue", "Marathi");
		personalDetails.put("AadharCardNumber", "112367899778");
		personalDetails.put("UANNumber", "767878784");
		personalDetails.put("PassPortNumber", "KMH5640JK3+");
		personalDetails.put("NameOnPassword", "testPName");
		personalDetails.put("ExpiryDateOnPassport", "18/12/2020");
		personalDetails.put("BirthState", "Maharashtra");
		personalDetails.put("BirthCity", "Pune");
		personalDetails.put("PanNumber", "76787876");
		Thread.sleep(1000);
		return personalDetails;
	}
	
	public boolean verifyUpdatedPersonalDetails(Map<String, String> userpersonalDetails) throws InterruptedException{
		Thread.sleep(1000);
		actions.clickOnElementWhenElementNotClickable(personalDetailsTab);
		Thread.sleep(2000);
		return userpersonalDetails.get("FatherName").equalsIgnoreCase(fatherNameTextBox.getAttribute("value").trim());
	}
	
	public boolean verifyFilledUserdetails() throws InterruptedException{
		Thread.sleep(4000);
		String log = handler.getLogs();
		if(personalDetailsProgressBar.getText().equalsIgnoreCase("100%") && educationalDetailsProgressBar.getText().equalsIgnoreCase("100%")
				&& contactDetailsProgressBar.getText().equalsIgnoreCase("100%") &&  employmentDetailsProgressBar.getText().equalsIgnoreCase("100%")){
			log = log + "\n All details are filled, hence all progress bars are 100%";
			handler.setLogs(log);
			return true;
		}
		return false;
	}

}
