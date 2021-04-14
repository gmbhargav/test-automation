package com.qa.automation.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Drivers {
    WebDriver selectedDriver;
    public WebDriver launchDriver(String desiredBrowser) {
        switch (desiredBrowser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                selectedDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                selectedDriver = new FirefoxDriver();
                break;
            default:
                throw new NotImplementedException("Desired Browser is not Found");
        }
        selectedDriver.manage().window().maximize();
        selectedDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return selectedDriver;
    }

    public void quitDriver(){
        selectedDriver.quit();
    }
}
