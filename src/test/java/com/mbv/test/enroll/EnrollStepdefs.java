package com.mbv.test.enroll;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EnrollStepdefs {

	public String DRUPAL_URL = "http://10.120.29.99:180";
	
	@Given("^login drupal system with username and password$")
	public void login_drupal_system_with_username_and_password()
			throws Throwable {
		
	}

	@When("^upload excel file$")
	public void upload_excel_file() throws Throwable {
		throw new PendingException();
	}

	@Then("^input Define a task to process, Organization ID, Credit Group$")
	public void input_Define_a_task_to_process_Organization_ID_Credit_Group()
			throws Throwable {
		throw new PendingException();
	}

	@Then("^change status to Authorized$")
	public void change_status_to_Authorized() throws Throwable {
		throw new PendingException();
	}

	@Then("^click button Save task$")
	public void click_button_Save_task() throws Throwable {
		throw new PendingException();
	}

	@Then("^All accounts in excel file are created have status pending$")
	public void All_accounts_in_excel_file_are_created_have_status_pending()
			throws Throwable {
		throw new PendingException();
	}

	@When("^Choose an account and change status to Waiting for user$")
	public void Choose_an_account_and_change_status_to_Waiting_for_user()
			throws Throwable {
		throw new PendingException();
	}

	@Then("^New account is created with status inactive in MCA_ADMIN sytem$")
	public void New_account_is_created_with_status_inactive_in_MCA_ADMIN_sytem()
			throws Throwable {
		throw new PendingException();
	}

	@When("^user send sms with systax: \"([^\"]*)\" to (\\d+)$")
	public void user_send_sms_with_systax_to(String arg1, int arg2)
			throws Throwable {
		throw new PendingException();
	}

	@Then("^sms respone :\"([^\"]*)\"$")
	public void sms_respone_(String arg1) throws Throwable {
		throw new PendingException();
	}

	@When("^user send sms with syntax: \"([^\"]*)\"$")
	public void user_send_sms_with_syntax(String arg1) throws Throwable {
		throw new PendingException();
	}

	@Then("^system response: \"([^\"]*)\"$")
	public void system_response(String arg1) throws Throwable {
		throw new PendingException();
	}
}
