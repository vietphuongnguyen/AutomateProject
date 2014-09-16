package com.mbv.test.enroll;

import org.junit.Assert;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mbv.test.sms.SmsServiceImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EnrollStepdefs {

	public String DRUPAL_URL = "http://10.120.29.99:180";
	
	DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
	PhantomJSDriver driver = new PhantomJSDriver(capabilities);
	
	SmsServiceImpl smsService = new SmsServiceImpl();
	
	@Given("^login drupal system with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void login_drupal_system_with_username_and_password(String username, String password) throws Throwable {
	    DrupalLoginPage page = new DrupalLoginPage();
	    page.setUrl(DRUPAL_URL);
	    page.setUsername(username);
	    page.setPassword(password);
	    
	    Assert.assertTrue(page.login(driver));
	}

	@When("^upload excel file in path \"([^\"]*)\"$")
	public void upload_excel_file_in_path(String path) throws Throwable {
	    EnrollExcelUploadPage page = new EnrollExcelUploadPage();
	    page.setUrl(DRUPAL_URL + "/mca_enroll/import/excel");
	    page.setTemplatePath(path);
	    
	    page.upload(driver);
	}
	
	@Then("^approve task to process with name \"([^\"]*)\", Organization ID \"([^\"]*)\", Credit Group \"([^\"]*)\"$")
	public void approve_task_to_process_with_name_Organization_ID_Credit_Group(String taskName, String org, String group) throws Throwable {
		ImportEnrollReviewPage page = new ImportEnrollReviewPage();
		page.setMemo(taskName);
		page.setOrgID(org);
		page.setSelectGroup(group);
		page.setStatus(ImportEnrollReviewPage.STATUS_AUTHORIZED);
		
		page.approve(driver);
	}
	
	@Then("^All accounts in excel file are created have status pending$")
	public void All_accounts_in_excel_file_are_created_have_status_pending() throws Throwable {
		// test assertion
	}
	
	@When("^Choose an account with mobile \"([^\"]*)\" and change status to Waiting for user$")
	public void Choose_an_account_with_mobile_and_change_status_to_Waiting_for_user(String mobile) throws Throwable {
		EnrollmentRequestPage page = new EnrollmentRequestPage();
		page.setUrl(DRUPAL_URL + "/mca_enroll");
		page.setMobileFilter(mobile);
		
		page.searchAndSelectPendingRequest(driver);
	}
	
	String responseTk;
	@Then("^New account is created with status inactive in MCA_ADMIN sytem$")
	public void New_account_is_created_with_status_inactive_in_MCA_ADMIN_sytem() throws Throwable {
	    // test assertion
	}
	
	@When("^user send sms tk with systax: \"([^\"]*)\" with mobile \"([^\"]*)\"$")
	public void user_send_sms_tk_with_systax_with_mobile(String content, String mobile) throws Throwable {
		smsService.setMcaSmsUrl("http://10.120.29.99:8787/mca-service/api/sms/request");
		responseTk = smsService.sendMessage(mobile, content);
	}
	
	@Then("^sms respone :\"([^\"]*)\"$")
	public void sms_respone_(String response) throws Throwable {
		Assert.assertEquals(responseTk, response);
	}

	String responseXNDK;
	@When("^user send sms xndk with systax: \"([^\"]*)\" with mobile \"([^\"]*)\"$")
	public void user_send_sms_xndk_with_systax_with_mobile(String content, String mobile) throws Throwable {
		smsService.setMcaSmsUrl("http://10.120.29.99:8787/mca-service/api/sms/request");
		responseXNDK = smsService.sendMessage(mobile, content);
	}

	@Then("^system response: \"([^\"]*)\"$")
	public void system_response(String response) throws Throwable {
		Assert.assertEquals(responseXNDK, response);
	}
}
