package com.mbv.test.enroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollExcelUploadPage {
	
	String templatePath;
	
	public void upload(WebDriver driver) {
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		WebElement fileUploadEle = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-excel-file")));
		
		fileUploadEle.sendKeys("/home/hoangphan/Downloads/template.xls");
		
		WebElement uploadBtn = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-upload")));
		
		uploadBtn.click();
		
		// check change URL
		WebElement dyntaskForm = webDriverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#dyntask-task-form")));
	}
	
}
