package com.hubspotapp.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTestInvalidData extends BaseTest{

	@DataProvider
	public Object[][] getInvalidLoginData() {
		Object[][] data = {{"Test@gmail.com", "Test@123"},
				{"Test", "Test@123"},
				{"", "Test@123"},
				{"Test@gmail.com", ""},
				{"", ""}};
		return data;
	}

	@Test(dataProvider = "getInvalidLoginData")
	public void VerifyLoginWithInvalidData(String email, String pwd) {
		loginPage.doLoginWithInvalidData(email, pwd);
		//		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

}
