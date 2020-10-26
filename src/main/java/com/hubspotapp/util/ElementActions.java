package com.hubspotapp.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hubspotapp.base.BasePage;

public class ElementActions extends BasePage{
	public WebDriver driver;
	WebDriverWait wait;
	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Appconstants.PAGE_LOAD_TIME_OUT);
	}
	/**
	 * This method will return the WebElement and need to pass the By locator
	 * @param locator
	 * @return WebElement element
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = getDriver().findElement(locator);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

	public void doSendKeys(By locator, String value ) {
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
		
	}

	public String doGetText(By locator) {
		String text = getElement(locator).getText();
		return text;
	}

	public  String doGetTitle() {
		String title = getDriver().getTitle();
		return title;
	}

	public boolean doIsDisplayed(By locator ) {
		return getElement(locator).isDisplayed();
	}

	public void waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForVisibilityOf(By locator) {
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public Boolean waitForIsTitle(String title ) {
		return  wait.until(ExpectedConditions.titleIs(title));
	}

}
