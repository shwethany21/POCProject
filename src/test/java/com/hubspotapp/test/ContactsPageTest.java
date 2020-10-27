package com.hubspotapp.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.base.BaseTest;
import com.hubspotapp.pages.ContactsPage;
import com.hubspotapp.pages.HomePage;
import com.hubspotapp.pages.LoginPage;
import com.hubspotapp.pojo.Credentials;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ExcelUtil;

public class ContactsPageTest extends BaseTest{


	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	Properties prop;
	ContactsPage contactsPage;
	Credentials credentails;

	@BeforeClass
	public void homePageSetUp() {
		credentails = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentails);
		contactsPage = new ContactsPage(driver);
	}

	@Test
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title is : " + title);
		//Assert.assertEquals(title, Appconstants.CONTACTS_PAGE_TITLE);
	}

	@Test
	public void verifyCreateNewContactsTest() {
		contactsPage.createNewContacts();
		String text = contactsPage.getContactName();
		System.out.println("Created contact is : " + text);
		//Assert.assertEquals(text, ("" + " " + " "));
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		Object data [][] = ExcelUtil.getTestData(Appconstants.TESTDATA_SHEET_NAME);
		return data;
	}

	@Test
	public void createNewContactsList(String email, String firstname, String lastname, String jobtitle, String phonenumber) {
		contactsPage.createNewContactsList(email, firstname, lastname, jobtitle, phonenumber);
	}


	@AfterTest()
	public void tearDown() {
		driver.quit();
	}


}
