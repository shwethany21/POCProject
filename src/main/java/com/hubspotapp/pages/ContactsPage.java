package com.hubspotapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ElementActions;

public class ContactsPage extends BasePage {
	WebDriver driver;
	ElementActions elementActions;
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(this.driver);
	}

	By contactsHeader = By.xpath("//span[@class='private-dropdown__item__label']");
	By createContact = By.xpath("//span[text()='Create contact']");
	By email = By.id("UIFormControl-33");
	By firstName = By.id("UIFormControl-35");
	By lastName = By.id("UIFormControl-39");
	By jobTitle =By.id("UIFormControl-47");
	By phoneNumber = By.id("UIFormControl-49");
	By contactName = By.className("CompanyContactEditableTitle__Title-sc-10nh44k-0 Lqvae");
	By createNewContact = By.xpath("(//span[text()='Create contact'])[position()=2]");
	By backtoContacts = By.xpath("(//i18n-string[text()='Contacts'])[2]");

	public String getContactsHeader() {
		return elementActions.doGetText(contactsHeader);
	}

	public String getContactsPageTitle() {
		elementActions.waitForIsTitle(Appconstants.CONTACTS_PAGE_TITLE);
		return elementActions.doGetTitle();
	}

	public void createNewContacts() {
		elementActions.doClick(createContact);
		elementActions.waitForElementPresent(email);
		elementActions.doSendKeys(email, "shwetha@gmail.com");
		elementActions.doSendKeys(firstName, "shwetha");
		elementActions.doSendKeys(lastName, "NY");
		elementActions.doSendKeys(jobTitle, "QA");
		elementActions.doSendKeys(phoneNumber, "9712345566");
		elementActions.doClick(createNewContact);
	}

	public ContactsPage createNewContactsList(String emailID, String fn, String ln , String jobtile, String Ph ) {
		elementActions.doClick(createContact);
		elementActions.waitForElementPresent(email);
		elementActions.doSendKeys(email, emailID);
		elementActions.doSendKeys(firstName, fn);
		elementActions.doSendKeys(lastName, fn);
		elementActions.doSendKeys(jobTitle, jobtile);
		elementActions.doSendKeys(phoneNumber, Ph);
		elementActions.doClick(createNewContact);
		return new ContactsPage(driver);
	}
	
	public String getContactName() {
		return elementActions.doGetText(contactName);
	}




}
