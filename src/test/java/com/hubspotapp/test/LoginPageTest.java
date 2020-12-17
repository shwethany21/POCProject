package com.hubspotapp.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.hubspotapp.listener.TestAllureListener;

import com.hubspotapp.base.BaseTest;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("EPIC 100: Design Login Page Feature for Hub Spot Application")
@Feature("US 101: Design Login Page module with title, sign up and login form")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1 )
	public void verifyLoginPageTitle() {
		String title = loginPage.doGetLoginPageTitle();
		System.out.println("Login Page title is : " + title);
		Assert.assertEquals(title, Appconstants.LOGIN_PAGE_TITLE);
	}

	@Description("Verify Sign Up link is dispalyed")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =2 )
	public void verifySignUpLinkIsDisplayed() {
		Assert.assertTrue(loginPage.doSignUpLinkIsDisplayed());
	}

	@Description("Verify Sign Up link text is dispalyed")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =3 )
	public void verifySignUpLinkTextIsDisplayed() {
		Assert.assertTrue(loginPage.doSignUpLinkTextIsDisplayed());
	}

	@Description("Verify Forgot Password link is dispalyed")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =4 )
	public void verifyForgotPasswordLinkIsDisplayed() {
		Assert.assertTrue(loginPage.doForgotPasswordIsDisplayed());
	}

	@Description("Verify user is able to login with valid credentials and verify the account name")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority =5 )
	public void verifyLogin() {
		credentails = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentails);
		String accountnameis = homePage.getAccountName();
		Assert.assertEquals(accountnameis, prop.getProperty("accountname"));
	}

}
