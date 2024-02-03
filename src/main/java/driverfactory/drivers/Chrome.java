package driverfactory.drivers;

import frameworkconstants.Constants;
import frameworkproperties.ReadPropertyFiles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

//import static extentreport.ExtentReport.test;

public class Chrome {
    public static WebDriver setGetDriver(String runMode) throws MalformedURLException {
        if (runMode.equalsIgnoreCase("remote")){
            Capabilities cap = new ChromeOptions();
            String hubHost = ReadPropertyFiles.getPropertyValue(Constants.GRID_HUB_HOST);
            String urlFormat = ReadPropertyFiles.getPropertyValue(Constants.GRID_URL_FORMAT);
            String  url = String.format(urlFormat, hubHost);
            System.out.println("Remote URL execution value" + url);

            return new RemoteWebDriver(new URL(url),cap);
        }else{
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-notifications");
//            options.addArguments("--start-maximized");
//            options.addArguments("--disable-features=EnableEphemeralFlashPermission");
//            options.addArguments("--disable-infobars");
            WebDriverManager.chromedriver().setup();


            return new ChromeDriver(options);
        }




    }
}
