package tests;

import driverfactory.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;

public class BaseTest {
    WebDriver driver;
    LoginPage lg;

    @BeforeSuite
    public void setUp(){


    }


    @AfterSuite
    public void tearDownSuite(){

    }
    @BeforeMethod
    public void beforeMethod(){

        //  System.out.println("Operating system : " + System.getProperty("os.name"));
        driver =  Driver.initDriver();

        lg = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDownTest(){

        Driver.driverQuit();
    }
}
