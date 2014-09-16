package com.mbv.test.enroll;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources"},
		format = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" })
//, tags = {"@run"}
public class CucumberTestRunner {
	

}
