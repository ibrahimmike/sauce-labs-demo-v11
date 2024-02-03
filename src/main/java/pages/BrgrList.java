package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrgrList extends BasePage{
    public BrgrList(WebDriver driver) {
        super(driver);
    }
    private WebElement allItemsElement = driver.findElement(By.xpath("//a[text()='All Items']"));
    private WebElement aboutAnchorTag = driver.findElement(By.xpath("//a[text()='About']"));
    private WebElement logoutAnchorTag = driver.findElement(By.xpath("//a[text()='Logout']"));
    private WebElement resetAppStateAnchorTag = driver.findElement(By.xpath("//a[text()='Reset App State']"));

    public LoginPage clickOnLogoutAnchorTag(){
        clickBtn(logoutAnchorTag);
        return new LoginPage(driver);
    }
    public HomePage clickOnAllItemsAnchorTag(){
        clickBtn(allItemsElement);
        return new HomePage(driver);
    }
    public void clickOnAboutAnchorTag(){
        clickBtn(aboutAnchorTag);
    }

    public HomePage clickOnResetAppStateAnchorTag(){
        clickBtn(resetAppStateAnchorTag);
        return new HomePage(driver);
    }
}
