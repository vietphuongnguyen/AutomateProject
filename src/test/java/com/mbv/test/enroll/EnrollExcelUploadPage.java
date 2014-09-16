package com.mbv.test.enroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollExcelUploadPage {
	
	String templatePath;
	
	String url;
	
	public void upload(WebDriver driver) {
		driver.get(url);
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		WebElement fileUploadEle = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-excel-file")));
		
		fileUploadEle.sendKeys(templatePath);
		
		WebElement uploadBtn = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-upload")));
		
		uploadBtn.click();
		
		// check change URL
//		WebElement dyntaskForm = webDriverWait.until(
//				ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#dyntask-task-form")));
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
