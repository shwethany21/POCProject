package com.hubspotapp.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.pages.HomePage;
import com.hubspotapp.pages.LoginPage;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ElementActions;

public class LoginPageTest extends BasePage {
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
		credentails = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority =1 , description ="Verify the LoginPage Title")
	public void verifyLoginPageTitle() {
		String title = loginPage.doGetLoginPageTitle();
		System.out.println("Login Page title is : " + title);
		Assert.assertEquals(title, Appconstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority =2 , description ="Verify Sign Up link is dispalyed")
	public void verifySignUpLinkIsDisplayed() {
		Assert.assertTrue(loginPage.doSignUpLinkIsDisplayed());
	}

	@Test(priority =3 , description ="Verify Sign Up link text is dispalyed")
	public void verifySignUpLinkTextIsDisplayed() {
		Assert.assertTrue(loginPage.doSignUpLinkTextIsDisplayed());
	}

	@Test(priority =4 , description ="Verify Forgot Password link is dispalyed")
	public void verifyForgotPasswordLinkIsDisplayed() {
		Assert.assertTrue(loginPage.doForgotPasswordIsDisplayed());
	}

	@Test(priority =5 , description ="Verify user is able to login with valid credentials and verify the account name")
	public void verifyLogin() {
		homePage = loginPage.doLogin(credentails);
		String accountnameis = homePage.getAccountName();
		Assert.assertEquals(accountnameis, prop.getProperty("accountname"));

	}

	@AfterTest()
	public void tearDown() {
		driver.quit();
	}


}
