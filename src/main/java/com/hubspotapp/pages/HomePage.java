package com.hubspotapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspotapp.base.BasePage;
import com.hubspotapp.util.Appconstants;
import com.hubspotapp.util.ElementActions;

public class HomePage extends BasePage{
	WebDriver driver;
	ElementActions elementAction ;

	public HomePage(WebDriver driver){
		this.driver = driver;
		elementAction = new ElementActions(this.driver);
	}

	By header = By.xpath("//h1[text()='Sales Dashboard']");
	By accontMenu = By.id("account-menu");
	By accountName = By.xpath("//div[@class='user-info-name']");
	By contacts = By.id("nav-primary-contacts-branch");
	By contactsSubmenu = By.id("nav-secondary-contacts");
	
	
	public String getHomePageTitle() {
		elementAction.waitForIsTitle(Appconstants.HOME_PAGE_TITLE);
		String title = elementAction.doGetTitle();
		return title;
	}
  
	public String GetHomePageHeader() {
		String headertext = elementAction.doGetText(header);
		return headertext;
	}
	
	public void clickOnAccountMenu() {
		elementAction.doClick(accontMenu);
	}
	
	public String getAccountName() {
		clickOnAccountMenu();
		elementAction.doIsDisplayed(accountName);
		String accountname = elementAction.doGetText(accountName);
		return accountname;
	}
	
	public void clickOnContactsMenu() {
		elementAction.doClick(contacts);
	}
	public void clickOnContactsSubmenu() {
		elementAction.waitForElementPresent(contactsSubmenu);
		elementAction.doClick(contactsSubmenu);
	}
	
}
