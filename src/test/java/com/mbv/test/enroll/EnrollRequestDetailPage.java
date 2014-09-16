package com.mbv.test.enroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollRequestDetailPage {
	
	String status;
	
	public void setStatus(WebDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		Select statusSelect = new Select(webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='status']"))));
		
		statusSelect.selectByValue(status);
		
		WebElement buttonSave = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("#edit-save")));
		
		buttonSave.click();
	}
}
