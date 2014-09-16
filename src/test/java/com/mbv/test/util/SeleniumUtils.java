package com.mbv.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumUtils {

	final static Log LOG = LogFactory.getLog(SeleniumUtils.class);

	/**
	 * References:
	 * http://blogs.burnsidedigital.com/2008/11/jquery-ajax-loading-and
	 * -selenium-respec-testing/
	 * 
	 * @param {@link WebDriver}
	 * @return True if no active connection present at the time
	 */
	public static boolean isAjaxConnectionPending(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Object activeConnection = js.executeScript("return jQuery.active");

		if (activeConnection instanceof Number) {
			Long active = ((Number) activeConnection).longValue();

			if (active == 0) {
				return true;
			}
		}

		LOG.info("active connection: " + activeConnection);

		return false;
	}

	public static void capture(WebDriver driver, String path) {
		// capture screenshot
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(path));
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
