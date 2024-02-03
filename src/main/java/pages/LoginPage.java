package pages;


import extentreport.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private WebElement userNameElement = driver.findElement(By.xpath("//input[@id='user-name']"));
    private WebElement passElement = driver.findElement(By.xpath("//input[@id='password']"));
    private WebElement loginBtn = driver.findElement(By.xpath("//input[@id='login-button']"));

    public HomePage enterUserNameAndPassword(String name, String password){
        enterDataToField(userNameElement, name );
        enterDataToField(passElement, password);
        clickBtn(loginBtn);

        ExtentLogger.pass("login button was clicked");
        return  new HomePage(driver);
    }

    public  String getErrorLogin(String name, String password){
        enterDataToField(userNameElement, name );
        enterDataToField(passElement, password);
        clickBtn(loginBtn);
        String error = getTextFromElement(driver.findElement(By.xpath("//h3[@data-test='error']")));
        return error;
    }

    public boolean checkIfLoginBtnIsVisible(){
        waitForElementToBeVisible(loginBtn);
        return loginBtn.isDisplayed();
    }


}
