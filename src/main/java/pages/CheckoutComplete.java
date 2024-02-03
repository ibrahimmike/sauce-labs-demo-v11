package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutComplete extends BasePage{
    /**
     * The thankYouMessage.
     */
    WebElement thankYouMessage = driver.findElement(By.className("complete-header"));
    /**
     * The Order confirmation message.
     */
    WebElement orderConfirmationMessage = driver.findElement(By.className("complete-text"));
    /**
     * The Back home btn.
     */
    WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));


    /**
     * Instantiates a new Checkout complete.
     *
     * @param driver the driver
     */
    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    /**
     * For ease of navigation to the header elements a get header element was created to get the header of the current
     *  page instance.
     * @return Header
     */
    private Header getHeader(){
        return new Header(driver);
    }

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle(){
        return getHeader().getSecondaryHeaderTitle();
    }

    /**
     * Get thank you message string.
     *
     * @return the string
     */
    public String getThankYouMessage(){
        return getTextFromElement(thankYouMessage).trim();
    }

    /**
     * Get order confirmation message string.
     *
     * @return the string
     */
    public String getOrderConfirmationMessage(){
        return getTextFromElement(orderConfirmationMessage).trim();
    }

    /**
     * Click back home home page.
     *
     * @return the home page
     */
    public HomePage clickBackHome(){
        clickBtn(backHomeBtn);
        return new HomePage(driver);
    }

}
