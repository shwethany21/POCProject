package com.hubspotapp.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.pages.ContactsPage;
import com.hubspotapp.pages.HomePage;
import com.hubspotapp.pages.LoginPage;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ElementActions;

public class HomePageTest extends BasePage {
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	Properties prop;
	ContactsPage contactsPage;
	Credentials credentails;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop= basePage.init_prop();
		driver = BasePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		credentails = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentails);
		contactsPage = new ContactsPage(driver);
	}

	@Test
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, Appconstants.HOME_PAGE_TITLE);
	}

	@Test
	public void verifyHomePageHeaderTest() {
		String homePageHeader = homePage.GetHomePageHeader();
		Assert.assertEquals(homePageHeader, Appconstants.HOME_PAGE_HEADER_TEXT);
	}

	@Test
	public void clickOnContactsLinkSybMenu() {
		homePage.clickOnContactsMenu();
		homePage.clickOnContactsSubmenu();
		String text = contactsPage.getContactsHeader();
		Assert.assertEquals(text, Appconstants.CONTACTS_PAGE_HEADER_TEXT);
	}



	@AfterTest()
	public void tearDown() {
		driver.quit();
	}

}
