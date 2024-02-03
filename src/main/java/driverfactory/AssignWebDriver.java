package driverfactory;

import driverfactory.drivers.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class AssignWebDriver {
    protected static WebDriver getWebdriver (String browser, String remote) throws MalformedURLException {

        if(browser.equalsIgnoreCase("chrome")){

            return Chrome.setGetDriver(remote);

        } else if (browser.equalsIgnoreCase("edge")) {
            return Edge.setGetDriver(remote);
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("Firefox is called");
            return Firefox.setGetDriver(remote);

        } else if (browser.equalsIgnoreCase("opera")) {
            return Opera.setGetDriver(remote);
        } else if (browser.equalsIgnoreCase("safari")) {
            return Safari.setGetDriverString(remote);
        }else{
            return Chrome.setGetDriver(remote);
        }
    }
}
