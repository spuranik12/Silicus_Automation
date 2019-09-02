package automation.utilities;

import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
/*import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIResults;*/

public class TestlinkUtils {

	String devKey;
	String serverURL;
	String testlinkProjectName;
	String testlinkPlanName;
	String testlinkBuildName;
	String notes;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TestlinkUtils(String devKey, String serverURL, String testlinkProjectName, String testlinkPlanName,
			String testlinkBuildName) {
		this.devKey = devKey;
		this.serverURL = serverURL;
		this.testlinkProjectName = testlinkProjectName;
		this.testlinkPlanName = testlinkPlanName;
		this.testlinkBuildName = testlinkBuildName;
	}

	/*public void reportResult(String TestProject, String TestPlan, String Testcase, String Build, String Notes, String Result) throws TestLinkAPIException, testlink.api.java.client.TestLinkAPIException{
		TestLinkAPIClient api = new TestLinkAPIClient(devKey, serverURL);
		api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, result != null? result :TestLinkAPIResults.TEST_FAILED);
	}*/	
}
