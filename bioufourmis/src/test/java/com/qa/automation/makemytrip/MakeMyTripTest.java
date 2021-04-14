package com.qa.automation.makemytrip;

import com.qa.automation.pages.EcommercePage;
import com.qa.automation.pages.MakeMyTripPage;
import com.qa.automation.util.Drivers;
import com.qa.automation.util.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MakeMyTripTest {
    WebDriver driver;
    MakeMyTripPage makeMyTripPage;
    Drivers drivers;
    String browserName;
    @BeforeClass
    @Parameters("browser")
    public void setup(String browserVal) throws Exception {
         this.drivers = new Drivers();
         this.browserName=browserVal;
        driver = drivers.launchDriver(browserName);
        makeMyTripPage = new MakeMyTripPage(driver);
    }
//
    @Test(priority = 0)
    public void getCheapestIndigoNonStopFlightsOnNexWeek() throws IOException {
        this.driver.navigate().to("https://www.makemytrip.com/");
        System.out.println("MakeMytrip site launched on "+this.browserName);
        Assert.assertTrue(makeMyTripPage.selectFromCity("Bengaluru"),"Unable to select from city");
        System.out.println("Selected Bengaluru as From City");
        Assert.assertTrue(makeMyTripPage.selectToCity("hyderabad"),"Unable to select To city");
        System.out.println("Selected hyderabad as To City");
        Assert.assertTrue(makeMyTripPage.selectLowestFareDateInNextWeek(),"Unable to select lowest fare date");
        System.out.println("Selected lowest fare date");
        makeMyTripPage.clickOnSearchFlights();
        Assert.assertTrue(makeMyTripPage.selectIndigoNonStopFilter(),"Unable to select Indigo NonStop filter");
        System.out.println("Selected Indigo NonStop filter");
        int noOfFlights=makeMyTripPage.getListOfFlightResults().size();
        Assert.assertTrue(noOfFlights>0,"No Flights available for the selected filter");
        System.out.println("Number of Cheapest Indigo NonStop Flights "+noOfFlights);
    }

    @Test(priority = 1)
    public void getFlightsFromDealsPage() throws IOException {
        this.driver.navigate().to("https://www.makemytrip.com/");
        System.out.println("MakeMytrip site launched on "+this.browserName);
        Assert.assertTrue(makeMyTripPage.selectSubMenuUnderMore("Deals"),"Unable to select deals submenu option from more");
        System.out.println("Selected deals submenu option from more menu");
        Assert.assertTrue(makeMyTripPage.selectFromCityOnDeals("Bengaluru"),"Unable to select from city on deals page");
        System.out.println("Selected Bengaluru as From City");
        Assert.assertTrue(makeMyTripPage.selectToCityOnDeals("Hyderabad"),"Unable to select To city");
        System.out.println("Selected hyderabad as To City");
        Assert.assertTrue(makeMyTripPage.selectAnyDepDateInDeals(),"Unable to select date");
        System.out.println("Selected date");
        makeMyTripPage.clickOnSearchDeals();
        int noOfFlights=makeMyTripPage.getListOfFlightResults().size();
        Assert.assertTrue(noOfFlights>0,"No Flights available for the selected filter");
        System.out.println("Number of Cheapest Indigo NonStop Flights "+noOfFlights);
    }
    @AfterClass
    public void tearDown(){
        drivers.quitDriver();
    }
}
