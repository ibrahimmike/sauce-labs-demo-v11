package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected List<WebElement> getWebElementsWhenTheyAreVisible(List<WebElement> items) {
        wait.until(ExpectedConditions.visibilityOfAllElements(items));
        return items;
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void enterDataToField(WebElement element, String userName) {
        waitForElementToBeVisible(element);
        element.sendKeys(userName);
    }


    protected void clickBtn(WebElement element) {

        waitForElementToBeVisible(element);
        waitForElementToBeClickable(element);
        element.click();
    }

    protected String getTextFromElement(WebElement element) {
        waitForElementToBeVisible(element);
        String textFromElement = element.getText();
        return textFromElement;
    }

    protected boolean checkItemsVisibility(List<WebElement> elements) {
        boolean check = false;
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        if (elements.size() > 3) {
            check = true;
        }
        return check;
    }

    protected boolean isElementVisible(WebElement element) {
        boolean visible = false;
        waitForElementToBeVisible(element);
        if (element.isDisplayed()) {
            visible = true;
        }
        return visible;

    }
}
