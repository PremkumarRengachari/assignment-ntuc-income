package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import implementation.ApiImplementation;
import implementation.GitHubUiImpl;
import org.testng.annotations.Test;
import utils.CommonUtil;

import java.io.IOException;
import java.util.List;

public class GitHubSteps extends CommonUtil {

    @Test(enabled = true)
    public void apiTest() {
        ExtentReports report = getReport("NTUC - Made yours - API test");
        ExtentTest test = report.createTest("Git hub - user details");

        ApiImplementation apiUserDtls = new ApiImplementation(api_base_url,user_name);
        int statusCodeFromResponse = apiUserDtls.getStatusCodeFromResponse();
        if(statusCodeFromResponse == 200) {
            test.log(Status.PASS,"User details fetched  successfully from Git hub api");
            test.log(Status.INFO, String.valueOf(statusCodeFromResponse));
            System.out.println("User Id :: "+ apiUserDtls.getUserId() + " - User name :: "+apiUserDtls.getUserName()+" - Created at :: "+apiUserDtls.getCreatedAt());
            test.log(Status.INFO,"User Id :: "+ apiUserDtls.getUserId());
            test.log(Status.INFO,"User name :: "+ apiUserDtls.getUserName());
            test.log(Status.INFO," Created at :: "+ apiUserDtls.getCreatedAt());
        } else {
            test.log(Status.FAIL,"User details NOT fetched  from Git hub api");
            test.log(Status.INFO, String.valueOf(statusCodeFromResponse));
        }

        ApiImplementation apiRepoDtls = new ApiImplementation(api_base_url,user_name,repo_sub_url);
        int statusCodeFromRepoDetails = apiRepoDtls.getStatusCodeFromResponse();
        if(statusCodeFromRepoDetails == 200) {
            test.log(Status.PASS,"Repo details fetched  successfully from Git hub api");
            test.log(Status.INFO, String.valueOf(statusCodeFromResponse));

            List<Object> repoNames = apiRepoDtls.getRepoNames();
            for(Object object : repoNames) {
                String name = object.toString();
                String starCount = apiRepoDtls.getStarCount(name);
                String releaseCount = apiRepoDtls.getReleaseCount(name);
                releaseCount = releaseCount.equals("null") ? "0" : releaseCount;
                String msg = String.format("Repo name :: %s  - Star count :: %s - Release count :: %s",name,starCount,releaseCount);
                System.out.println(msg);
                test.log(Status.INFO,msg);
            }
        } else {
            test.log(Status.FAIL,"Repo details NOT fetched  from Git hub api");
            test.log(Status.INFO, String.valueOf(statusCodeFromResponse));
        }
        endReport(report);
    }

    @Test (enabled = true)
    public void webUiTest() throws IOException {

        ExtentReports report = getReport("NTUC - Made yours - Web UI test");
        ExtentTest test = report.createTest("Git hub - validation of stars count for the user id and repo name");

        GitHubUiImpl gitHubUi = new GitHubUiImpl();
        gitHubUi.searchGithubUserId(user_name+"/"+repo_name);
        test.log(Status.INFO,"Searched with given input");
        test.addScreenCaptureFromPath(takeScreenshot());
        gitHubUi.clickRepo(user_name,repo_name);
        test.log(Status.INFO,"Clicked the repo");
        test.addScreenCaptureFromPath(takeScreenshot());
        String startCountUI = gitHubUi.getStartCount();
        ApiImplementation api = new ApiImplementation(api_base_url,user_name,repo_sub_url);
        int statusCodeFromResponse = api.getStatusCodeFromResponse();
        if(statusCodeFromResponse == 200) {
            test.log(Status.PASS,"Repo details fetched  successfully from Git hub api");
            test.log(Status.INFO, String.valueOf(statusCodeFromResponse));
            String starCountApi = api.getStarCount(repo_name);
            test.log(Status.INFO,"get star count from the repo");
            test.addScreenCaptureFromPath(takeScreenshot());
            String msg = "%s - Verification of star count of given repo - Actual from UI %s - Expected from API - %s";
            String convertToUnits = convertToUnits(starCountApi);
            if(startCountUI.equals(convertToUnits)) {
                test.log(Status.PASS, String.format(msg,"Passed",startCountUI,starCountApi));
            } else {
                test.log(Status.FAIL, String.format(msg,"Failed",startCountUI,starCountApi));
            }
        }

        endReport(report);
    }
}
