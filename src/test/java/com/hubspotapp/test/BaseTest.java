package com.hubspotapp.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.pages.HomePage;
import com.hubspotapp.pages.LoginPage;
import com.hubspotapp.pojo.Credentials;

public class BaseTest extends BasePage {
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	Properties prop;
	Credentials credentails;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop= basePage.init_prop();
		driver = BasePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}


	
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}

}
