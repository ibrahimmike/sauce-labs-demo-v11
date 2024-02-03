package driverfactory;

import frameworkconstants.Constants;
import frameworkproperties.ReadPropertyFiles;
import org.openqa.selenium.WebDriver;

public class Driver {
    //WebDriver driver;

    public static WebDriver initDriver() {




        try {
            //   DriverManager.setDriver(Chrome.setGetDriver("local"));
            DriverManager.setDriver(AssignWebDriver.getWebdriver(ReadPropertyFiles.getPropertyValue(Constants.BROWSER),
                    ReadPropertyFiles.getPropertyValue(Constants.RUNMODE)));
        } catch (Exception e) {
            e.getMessage();
        }
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ReadPropertyFiles.getPropertyValue(Constants.URL));



        return DriverManager.getDriver();
    }

    public static void driverQuit(){
        DriverManager.getDriver().quit();
        DriverManager.removeDriver();

    }
}
