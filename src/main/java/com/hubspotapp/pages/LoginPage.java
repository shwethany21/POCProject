package com.hubspotapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ElementActions;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementActions elementAction;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementAction = new ElementActions(this.driver);
	}

	By emailAddress = By.id("username");
	By pwd = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink =By.xpath("//i18n-string[text()='Sign up']");
	By signUpLinkText=By.xpath("(//div[@class='signup-link']//i18n-string)[1]");
	By forgotPwdLink =By.xpath("//i18n-string[text()='Forgot my password']");
	By loginErrorMessage = By.xpath("//i18n-string[contains(text(),\"The email address you've entered \")]");

	public boolean doSignUpLinkIsDisplayed() {
		elementAction.waitForElementPresent(signUpLink);
		return elementAction.doIsDisplayed(signUpLink);
	}

	public boolean doSignUpLinkTextIsDisplayed() {
		elementAction.waitForElementPresent(signUpLinkText);
		return elementAction.doIsDisplayed(signUpLinkText);
	}

	public String doGetLoginPageTitle() {
		elementAction.waitForIsTitle(Appconstants.LOGIN_PAGE_TITLE);
		return elementAction.doGetTitle();
	}

	public boolean doForgotPasswordIsDisplayed() {
		elementAction.waitForElementPresent(forgotPwdLink);
		return elementAction.doIsDisplayed(forgotPwdLink);
	}

	public HomePage doLogin(Credentials credentails) {
		elementAction.waitForElementPresent(emailAddress);
		System.out.println("Credentails are " + credentails.getUsername()+ " " + credentails.getPassword());
		elementAction.doSendKeys(emailAddress, credentails.getUsername());
		elementAction.doSendKeys(pwd, credentails.getPassword());
		elementAction.doClick(loginButton);
		return new HomePage(driver);
	}

	public void doLoginWithInvalidData(String username , String password) {
		elementAction.waitForElementPresent(emailAddress);
		System.out.println("Credentails are " + username+ " " + password);
		elementAction.getElement(emailAddress).clear();
		elementAction.doSendKeys(emailAddress, username);
		elementAction.getElement(pwd).clear();
		elementAction.doSendKeys(pwd, password);
		elementAction.doClick(loginButton);
	}

	public boolean checkLoginErrorMessage() {
		elementAction.waitForElementPresent(loginErrorMessage);
		return elementAction.doIsDisplayed(loginErrorMessage);
	}
}
