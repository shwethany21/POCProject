package com.hubspotapp.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public static WebDriver driver;
	public Properties prop;
	static OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();

	}


	/**
	 * This method is to initialize the driver and launch the URL from the properties file
	 * @param prop
	 * @return driver
	 */
	public static WebDriver init_driver(Properties prop) {
		String browserName =prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);

		/*boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"));*/
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		/*if(isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
//				co.addArguments("--incognito");
			}}*/
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			/*if(isHeadless) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			}else {
				driver = new FirefoxDriver();
			}}*/
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
			/*driver = new SafariDriver();*/
		} else {
			System.out.println(browserName + " browser name does not match, please pass the correct browser");
		}
		/*driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver;*/
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	/**
	 * This method is to initiate the configuration properties form the prop.properties file
	 * @return  prop
	 */
	public Properties init_prop()   {
		prop = new Properties();
		String env = null;
		String path = null; 
		FileInputStream fis;
		try {
			env = System.getProperty("env");
			if(env==null) {
				path = ".\\src\\main\\java\\com\\hubspotapp\\config\\config.properties";
			} else {
				switch (env) {
				case "qa":
					path = "\\src\\main\\java\\com\\hubspotapp\\config\\config.qa.properties";
					break;
				default:
					System.out.println("Envernoment not passed");
					break;
				}
			}
			
				fis = new FileInputStream(path);
				prop.load(fis);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} return prop;
		
		}


		public String getScreenshot() {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis()+".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return path;
		}


	}
