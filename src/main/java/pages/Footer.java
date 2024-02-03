package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer extends BasePage{

    public Footer(WebDriver driver) {
        super(driver);
    }
    WebElement footerHead = driver.findElement(By.className("footer"));
    WebElement socialFb = driver.findElement(By.className("social_facebook"));
    WebElement socialTwit = driver.findElement(By.className("social_twitter"));
    WebElement socialLinkedin = driver.findElement(By.className("social_linkedin"));
    WebElement footerDisclaimer = driver.findElement(By.className("footer_copy"));

    public boolean CheckVisibilityOfTheFooterElements(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",footerDisclaimer);
        boolean fb = isElementVisible(socialFb);
        boolean st = isElementVisible(socialTwit);
        boolean linkedin = isElementVisible(socialLinkedin);
        boolean disclaimer = isElementVisible(footerDisclaimer);
        if (fb && st && linkedin && disclaimer){
            return true;
        }
        return false;
    }

}
