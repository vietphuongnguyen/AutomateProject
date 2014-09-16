package com.mbv.test.enroll;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DrupalLoginPage {

	final static Log LOG = LogFactory.getLog(DrupalLoginPage.class);
	
	String username;
	String password;
	
	public boolean login(WebDriver driver) throws IOException {
		
		WebDriverWait webWait = new WebDriverWait(driver, 5);
		
		WebElement userTxt = webWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-name")));

		userTxt.sendKeys(username);
		
		WebElement passTxt = webWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-pass")));
		
		passTxt.sendKeys(password);
		
		WebElement loginBtn = webWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#edit-submit")));
			
		loginBtn.click();
		
		try {
			WebElement myAccountLink = webWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#secondary-menu-links > li.menu-2.first > a")));
			
			if(!myAccountLink.getText().equals("My account")) {
				throw new NotFoundException("my account link cannot be found");
			}
		} catch (NotFoundException e) {
			LOG.error(e.getMessage(), e);
			
			// capture screenshot
			 File screenshot = ((TakesScreenshot) driver)
                     .getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(screenshot, new File("login-failed.png"));
			 
			 return false;
		}
		
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
