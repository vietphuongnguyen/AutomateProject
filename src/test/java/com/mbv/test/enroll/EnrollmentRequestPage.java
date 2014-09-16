package com.mbv.test.enroll;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mbv.test.util.SeleniumUtils;

public class EnrollmentRequestPage {

	final static Log LOG = LogFactory.getLog(EnrollmentRequestPage.class);
	
	String statusFilter;
	String organiztionFilter;
	String employeeFilter;
	String mobileFilter;
	
	String url;
	
	public void search(WebDriver driver) {
		driver.get(url);
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		SeleniumUtils.capture(driver, "search.png");
		
		if(StringUtils.isNotBlank(statusFilter)) {
			Select statusSelect = new Select(webDriverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#edit-status"))));
			
			statusSelect.selectByValue(statusFilter);
		}
		
		if(StringUtils.isNotBlank(organiztionFilter)) {
			WebElement ele = webDriverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-org-short-name")));
			ele.sendKeys(organiztionFilter);
		}
		
		if(StringUtils.isNotBlank(employeeFilter)) {
			WebElement ele = webDriverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-org-user-id")));
			ele.sendKeys(employeeFilter);
		}
		
		if(StringUtils.isNotBlank(mobileFilter)) {
			WebElement ele = webDriverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-mobile")));
			ele.sendKeys(mobileFilter);
		}
		
		WebElement submit = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("#edit-submit-mca-enroll-request")));
		submit.click();
	}
	
	public void searchAndSelectPendingRequest(WebDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		this.search(driver);
		
		List<WebElement> tableRow = webDriverWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.view-id-mca_enroll_request tr")));
		
		LOG.info("found " + tableRow.size() + " recorded!");
		
		for (WebElement row : tableRow) {
			WebElement statusCol = row.findElement(By.cssSelector(".views-field-status"));
			
			LOG.info("status: " + statusCol.getText());
			if(statusCol.getText().equals("pending") || StringUtils.isBlank(statusCol.getText())) {
				row.click();
				break;
			}
		}
	}

	public String getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}

	public String getOrganiztionFilter() {
		return organiztionFilter;
	}

	public void setOrganiztionFilter(String organiztionFilter) {
		this.organiztionFilter = organiztionFilter;
	}

	public String getEmployeeFilter() {
		return employeeFilter;
	}

	public void setEmployeeFilter(String employeeFilter) {
		this.employeeFilter = employeeFilter;
	}

	public String getMobileFilter() {
		return mobileFilter;
	}

	public void setMobileFilter(String mobileFilter) {
		this.mobileFilter = mobileFilter;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
