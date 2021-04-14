package com.qa.automation.ecommerce;

import com.qa.automation.pages.EcommercePage;
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

public class EcommerceTest {
    WebDriver driver;
    EcommercePage ecommercePage;
    ExcelUtil excelUtil;
    Drivers drivers;
    String browserName;
    String userName="testuserqa0012021591421@gmail.com";
    String password="Mypwd@2021";

    @BeforeClass
    @Parameters("browser")
    public void setup(String browserVal) throws Exception {
         this.drivers = new Drivers();
         this.browserName=browserVal;
        driver = drivers.launchDriver(browserName);
        ecommercePage = new EcommercePage(driver);
        excelUtil = new ExcelUtil();
    }

    @Test(priority = 0,invocationCount = 5)
    public void signUp() throws IOException {
        Map<String,String> map=new HashMap<>();
        this.driver.navigate().to("http://automationpractice.com/index.php");
        System.out.println("Ecommerce site launched on "+this.browserName);
        map=excelUtil.getTestData(1);
        Assert.assertTrue(ecommercePage.signUpForNewUser(map.get("username"),map.get("password")),"Unable to sign up");
        System.out.println("Assigning un & pwd");
        this.userName=map.get("username");
        this.password=map.get("password");
        Assert.assertTrue(ecommercePage.logOut(),"Unable to sign out");
    }

    @Test(priority = 1)
    public void purchaseAnItem(){
        this.driver.navigate().to("http://automationpractice.com/index.php");
        System.out.println("Ecommerce site launched on "+this.browserName);
        Assert.assertTrue(ecommercePage.login(this.userName,this.password),"unable to login");
        System.out.println("Logged in successfully");
        Assert.assertTrue(ecommercePage.selectSubMenuUnderWomen(),"Unable to navigate to sub menu");
        System.out.println("Navigated to sub menu");
        Assert.assertTrue(ecommercePage.addItemToCart(),"Unable add Item to cart");
        System.out.println("Item added to cart");
        String chkOutTotalPrice=ecommercePage.checkOutTotalPrice;
        System.out.println("chkOutTotalPrice "+chkOutTotalPrice);
        String cartPrice=ecommercePage.getCartItemTotalPrice();
        System.out.println("cartPrice "+cartPrice);
        Assert.assertEquals(chkOutTotalPrice,cartPrice,"Cart product details not matching");
        Assert.assertTrue(ecommercePage.purchaseAnItem(),"unable to purchase an item");
    }

    @AfterClass
    public void tearDown(){
        drivers.quitDriver();
    }
}
