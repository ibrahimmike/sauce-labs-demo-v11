package driverfactory;

import org.openqa.selenium.WebDriver;

public class DriverManager extends ThreadLocal{
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal();

    public static void setDriver(WebDriver driver){drivers.set(driver);}
    public static WebDriver getDriver(){return drivers.get();}
    public static void removeDriver(){
        drivers.remove();
    }

}
