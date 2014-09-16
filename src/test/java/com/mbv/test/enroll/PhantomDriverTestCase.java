package com.mbv.test.enroll;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.mbv.test.util.SeleniumUtils;

public class PhantomDriverTestCase {

	final static Log LOG = LogFactory.getLog(PhantomDriverTestCase.class);
	
	DrupalLoginPage loginPage = new DrupalLoginPage();
	EnrollExcelUploadPage uploadPage = new EnrollExcelUploadPage();
	ImportEnrollReviewPage reviewPage = new ImportEnrollReviewPage();
	
	DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
	PhantomJSDriver driver = new PhantomJSDriver(capabilities);
	
//	@Test
	public void testSearchReturnsResults() throws IOException {
		// Create instance of PhantomJS driver
		driver.get("http://localhost/drupal-7.26/");
		
		loginPage.setUsername("admin");
		loginPage.setPassword("Aesax5099");
		
		boolean isLogged = loginPage.login(driver);
		
		if(isLogged) {
			System.out.println("Logged in successfully!!!");
		} else {
			System.out.println("Logged in failed!!!");
		}
		
		driver.get("http://localhost/drupal-7.26/mca_enroll/import/excel");
		
		uploadPage.upload(driver);
		
		reviewPage.setMemo("test-task-02");
		reviewPage.setOrgID("DMCL");
		reviewPage.setSelectGroup("admin:427573d6-6571-4ff7-844f-ba6d95eab534");
		reviewPage.setStatus(ImportEnrollReviewPage.STATUS_AUTHORIZED);
		
		final String reviewURL = driver.getCurrentUrl();
		
		reviewPage.approve(driver);
		
		try {
			new WebDriverWait(driver, 10).until(new Predicate<WebDriver>() {
				
				public boolean apply(WebDriver wdriver) {
					LOG.info("current url: " + wdriver.getCurrentUrl() + " - " + " reviewURL: " + reviewURL);
					
					if(!wdriver.getCurrentUrl().equals(reviewURL))
						return true;
					
					return false;
				}
			});
		} catch (TimeoutException e) {
			// capture screenshot
			File screenshot = ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("timeout.png"));
		}
		
		// capture screenshot
		File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("final.png"));
	}
	
	@Test
	public void testSearchEnrollmentResults() throws IOException {
		driver.get("http://localhost/drupal-7.26/");
		
		loginPage.setUsername("admin");
		loginPage.setPassword("Aesx5099");
		
		boolean isLogged = loginPage.login(driver);
		
		if(isLogged) {
			System.out.println("Logged in successfully!!!");
		} else {
			System.out.println("Logged in failed!!!");
		}
		
		EnrollmentRequestPage enrollemtPage = new EnrollmentRequestPage();
		//enrollemtPage.setStatusFilter("pending");
		enrollemtPage.setMobileFilter("0986858724");
		
		enrollemtPage.searchAndSelectPendingRequest(driver);
		
		SeleniumUtils.capture(driver, "result.png");
	}
}
