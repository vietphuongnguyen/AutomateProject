package com.mbv.test.enroll;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.mbv.test.util.SeleniumUtils;

public class ImportEnrollReviewPage {

	final static Log LOG = LogFactory.getLog(ImportEnrollReviewPage.class);

	String memo;
	String orgID;
	String selectGroup;
	String status;

	public void approve(WebDriver driver) {

		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

		WebElement memoTxt = webDriverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("input#edit-memo")));

		memoTxt.sendKeys(memo);

		WebElement orgTxt = webDriverWait
				.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("input#edit-mca-enroll-org-und-0-short-name")));

		orgTxt.sendKeys(orgID);

		// blur trigger blur event on text for ajax loading
		JavascriptExecutor js = (JavascriptExecutor) driver;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("var x = jQuery('input#edit-mca-enroll-org-und-0-short-name');");
		stringBuilder.append("x.blur();");
		js.executeScript(stringBuilder.toString());

		// wait for ajax connection terminated
		webDriverWait.until(new Predicate<WebDriver>() {

			public boolean apply(WebDriver driver) {
				return SeleniumUtils.isAjaxConnectionPending(driver);
			}
		});

		WebElement selectElement = webDriverWait.until(ExpectedConditions
				.presenceOfElementLocated(By
						.cssSelector("select[name^='mca_enroll_group']")));

		List<WebElement> options = selectElement.findElements(By
				.cssSelector("option"));

		for (WebElement option : options) {
			LOG.info("option: " + option.getText());
			if (option.getAttribute("value").equals(selectGroup)) {
				option.click();
				break;
			}
		}

		Select select = new Select(selectElement);
		select.selectByValue(selectGroup);

		SeleniumUtils.capture(driver, "selected.png");

		try {
			selectElement.isDisplayed();
			WebElement saveTask = webDriverWait.until(ExpectedConditions
					.elementToBeClickable(By
							.cssSelector("input#edit-submit")));

			saveTask.click();

			SeleniumUtils.capture(driver, "submit.png");
		} catch (Exception e) {
			LOG.info("fucking ajax replaced element");
			selectElement = webDriverWait.until(ExpectedConditions
					.presenceOfElementLocated(By
							.cssSelector("select[name^='mca_enroll_group']")));
			
			select = new Select(selectElement);
			select.selectByValue(selectGroup);
			
			WebElement saveTask = webDriverWait.until(ExpectedConditions
					.elementToBeClickable(By
							.cssSelector("input#edit-submit")));

			saveTask.click();
			
			SeleniumUtils.capture(driver, "submit.png");
		}
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getSelectGroup() {
		return selectGroup;
	}

	public void setSelectGroup(String selectGroup) {
		this.selectGroup = selectGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
