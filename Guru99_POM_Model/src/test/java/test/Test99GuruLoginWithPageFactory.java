package test;

import java.util.concurrent.TimeUnit;

import PageFactory.pfGuru99HomePage;
import PageFactory.pfGuru99Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Test99GuruLoginWithPageFactory {

    String driverPath = "C:\\geckodriver.exe";
    WebDriver driver;
    pfGuru99Login objLogin;
    pfGuru99HomePage objHomePage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/V4/");

    }

    /**
     * This test go to http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     */

    @Test(priority=1)
    public void test_Home_Page_Appear_Correct(){
//Create Login Page object
        objLogin = new pfGuru99Login(driver);

//Verify login page title
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

//login to application
        objLogin.loginToGuru99("mgr123", "mgr!23");

// go the next page
        objHomePage = new pfGuru99HomePage(driver);

//Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

    }
}

