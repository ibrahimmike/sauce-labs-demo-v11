package pages;

import frameworkconstants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class YourInformationPage extends BasePage{

    public YourInformationPage(WebDriver driver) {
        super(driver);
    }

    private Header getHeader(){
        return new Header(driver);
    }

    WebElement inputFirstName = driver.findElement(By.id("first-name"));
    WebElement inputSecondName = driver.findElement(By.id("last-name"));
    WebElement inputZipCode = driver.findElement(By.id("postal-code"));
    WebElement cancelBtn = driver.findElement(By.id("cancel"));
    WebElement continueBtn = driver.findElement(By.id("continue"));


    private Map<String, String> map = Constants.createUserData();


    public String getYourInformationSecondaryHeaderText(){
        String text = getHeader().getSecondaryHeaderTitle().trim();
        return text;
    }



    public CheckoutOverviewPage enterUserInfoAndContinue(){

        enterDataToField(inputFirstName, map.get("name"));
        enterDataToField(inputSecondName, map.get("lastName"));
        enterDataToField(inputZipCode, map.get("zipCode"));
        clickBtn(continueBtn);
        return new CheckoutOverviewPage(driver);
    }

    public YourInformationPage theUserEntersUserFirstNameOnly(){
        enterDataToField(inputFirstName, map.get("name"));
        String error = getTheErrorMessageMissingInformationError();
        return this;
    }
    public YourInformationPage theUserEntersUserLastNameOnly(){
        enterDataToField(inputSecondName, map.get("lastName"));
        String error = getTheErrorMessageMissingInformationError();
        return this;
    }
    public YourInformationPage theUserEntersZipCodeOnly(){
        enterDataToField(inputZipCode, map.get("name"));
        String error = getTheErrorMessageMissingInformationError();
        return this;
    }
    public YourInformationPage theUserEntersFirstNameAndLastNameNoZipCode(){
        enterDataToField(inputFirstName, map.get("name"));
        enterDataToField(inputSecondName, map.get("lastName"));
        return this;
    }
    public String getTheErrorMessageMissingInformationError(){
        clickBtn(continueBtn);
        String errorMessage = getTextFromElement(driver.findElement(By.xpath("//h3[@data-test='error']"))).trim();
        return errorMessage;
    }
    public CartPage clickOnTheCancelBtnAndReturnToCartPage(){
        clickBtn(cancelBtn);
        return new CartPage(driver);
    }


}
