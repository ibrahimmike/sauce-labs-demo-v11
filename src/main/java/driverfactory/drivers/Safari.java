package driverfactory.drivers;

import frameworkconstants.Constants;
import frameworkproperties.ReadPropertyFiles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Safari {
    public static WebDriver setGetDriverString(String runMode) throws MalformedURLException {
        if (runMode.equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(Browser.SAFARI.browserName());
            String hubHost = ReadPropertyFiles.getPropertyValue(Constants.GRID_HUB_HOST);
            String urlFormat = ReadPropertyFiles.getPropertyValue(Constants.GRID_URL_FORMAT);
            String  url = String.format(urlFormat, hubHost);
            return new RemoteWebDriver(new URL(url), cap);
        } else {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        }
    }
}
