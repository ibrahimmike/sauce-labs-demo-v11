package pages;

import extentreport.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header extends BasePage{
    public Header(WebDriver driver) {
        super(driver);
    }
    private WebElement cart = driver.findElement(By.className("shopping_cart_link"));
    private WebElement appLogo = driver.findElement(By.className("app_logo"));
    private WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));




    public CartPage clickOnCartItem() {
        clickBtn(cart);
        ExtentLogger.pass("clicked on the click on cart Item ");
        return new CartPage(driver);
    }

    public BrgrList clickOnBrgrList() {
        clickBtn(burgerMenu);
        return new BrgrList(driver);
    }

    public boolean checkTheAvailabilityOfTheLogo() {
        boolean visible = false;
        if (appLogo.isDisplayed()) {
            visible = true;
        } else {
            visible = false;
        }
        return visible;
    }

    public String getSecondaryHeaderTitle() {
        String secTit = getTextFromElement(driver.findElement(By.className("title")));
        return secTit;
    }

    public Integer getTheAmountOfTheItemsOnTheCartLogo() {
        int amountOfItems = 0;
        if(driver.findElements(By.className("shopping_cart_badge")).size()<1){
         //   ExtentLogger.log("Shopping cart size is 0");

            return amountOfItems;
        }else {
            WebElement amountInCart = driver.findElement(By.className("shopping_cart_badge"));
            amountOfItems = Integer.parseInt(getTextFromElement(amountInCart));
          //  ExtentLogger.log("The amount of items in the cart is : " + amountOfItems);
        }
        return amountOfItems;

    }
    public boolean secondaryHeaderIsVisible() {
        boolean brgMenuIsVisible = isElementVisible(burgerMenu);
        if (brgMenuIsVisible) {
            return true;
        }
        return false;
    }
    protected WebElement getFilter(){
        WebElement filter = driver.findElement(By.className("product_sort_container"));
        waitForElementToBeVisible(filter);
        return filter;
    }


}
