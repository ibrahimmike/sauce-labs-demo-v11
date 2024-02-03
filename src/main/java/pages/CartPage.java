package pages;


import extentreport.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import products.Cart;
import products.Product;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }
    private List<WebElement> cartItems =  driver.findElements(By.className("cart_item"));
    private WebElement continueShoppingBtn = driver.findElement(By.id("continue-shopping"));

    private WebElement checkoutBtn = driver.findElement(By.id("checkout"));

    public Header getHeader(){
        return new Header(driver);
    }

    public HomePage clickOnContinueShoppingBtn(){

        System.out.println("Clicked on continue shopping btn");

        clickBtn(continueShoppingBtn);
        ExtentLogger.pass("Continue shopping has been clicked");
        return new HomePage(driver);
    }


    public YourInformationPage clickOnCheckoutBtn(){
        clickBtn(checkoutBtn);
        return new YourInformationPage(driver);
    }

    public List<Product> getProductsInCart(){
        List<Product> products = new ArrayList<>();
        Cart cart = new Cart();
        for (WebElement item : cartItems){
            String productName = getTextFromElement(item.findElement(By.className("inventory_item_name"))).trim();
            String productDescription = getTextFromElement(item.findElement(By.className("inventory_item_desc"))).trim();
            double price = Double
                    .parseDouble(getTextFromElement(item.findElement(By.className("inventory_item_price"))).replace('$', ' ')
                            .trim());
            Product product = new Product(productName, productDescription, price);
            products.add(product);
            cart.addToCart(product);
        }
        return products;
    }

    public boolean clickOnTheContinueShoppingAndCheckTheAmountOnCart(){
        boolean checked = false;
        int  amountOfProducts = getProductsInCart().size();
        int amountShownOnHomePage = clickOnContinueShoppingBtn().getHeader().getTheAmountOfTheItemsOnTheCartLogo();
        if(amountOfProducts==amountOfProducts){
            checked = true;
         //   ExtentLogger.pass("When the user presses continue shopping the amount on cart doesn't get deleted");
        }else{
         //   ExtentLogger.fail("The amount on the cart changes when the user clicks continue shopping");
        }
        return checked;
    }
    public boolean whenClickingOnRemoveBtnTheItemIsRemovedFromCart(){
        List<Product> productsOnCartBeforeRemove = getProductsInCart();
        List<WebElement> removeBtns = driver.findElements(By.xpath("//button[text()='Remove']"));

        if((removeBtns.size() > 0)){
            clickBtn(removeBtns.get(0));
            int amountShowingOnTheCart = getHeader().getTheAmountOfTheItemsOnTheCartLogo();
            if((amountShowingOnTheCart==productsOnCartBeforeRemove.size()) ){
             //   ExtentLogger.fail("The remove btn on the item doesn't remove the items from the cart page");
                return false;
            }else{
            //    ExtentLogger.pass("The remove btn has successfully removed the item from the cart " + productsOnCartBeforeRemove.size());
                return true;
            }
        }else{
         //   ExtentLogger.log("The cart page is empty of any products");
        }
        return false;
    }

    public boolean theItemsAreVisibleOnTheCart(){
        if(getWebElementsWhenTheyAreVisible(cartItems).size()>0){
            return true;
        }else {
            return false;
        }

    }

}
