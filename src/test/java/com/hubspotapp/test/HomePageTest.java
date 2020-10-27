package com.hubspotapp.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hubspotapp.base.BaseTest;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;

public class HomePageTest extends BaseTest {


	@BeforeClass
	public void setUp() {
		credentails = new Credentials(prop.getProperty("username"), prop.getProperty("password"));

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


}
