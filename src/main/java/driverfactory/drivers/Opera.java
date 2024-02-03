package driverfactory.drivers;

import frameworkconstants.Constants;
import frameworkproperties.ReadPropertyFiles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Opera {
    public static WebDriver setGetDriver(String runMode) throws MalformedURLException {
        if (runMode.equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(Browser.OPERA.browserName());
            String hubHost = ReadPropertyFiles.getPropertyValue(Constants.GRID_HUB_HOST);
            String urlFormat = ReadPropertyFiles.getPropertyValue(Constants.GRID_URL_FORMAT);
            String  url = String.format(urlFormat, hubHost);
            return new RemoteWebDriver(new URL(url), cap);
        } else {
            WebDriverManager.operadriver().setup();
            return new OperaDriverManager().getWebDriver();
        }
    }
}
