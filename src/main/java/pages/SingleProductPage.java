package pages;


import extentreport.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import products.Product;

import java.util.List;

public class SingleProductPage extends BasePage{

    public SingleProductPage(WebDriver driver) {
        super(driver);
    }

    WebElement backToProducts = driver.findElement(By.id("back-to-products"));
    WebElement productImage = driver.findElement(By.xpath("//img[@class='inventory_details_img']")); //inventory_details_img
    WebElement productName = driver.findElement(By.cssSelector(".inventory_details_name.large_size"));
    WebElement productDescription = driver.findElement(By.cssSelector(".inventory_details_desc.large_size"));
    WebElement productPrice = driver.findElement(By.className("inventory_details_price"));



    public Header getHeader(){
        return new Header(driver);
    }


    public HomePage clickOnBackToProducts(){
        clickBtn(backToProducts);
        return new HomePage(driver);
    }

    public String getSingleProductName(){
        return getTextFromElement(productName).trim();
    }
    public String getSingleProductDescription(){
        return getTextFromElement(productDescription);
    }
    public double getThePriceOfTheSingleProduct(){
        return  Double.parseDouble(getTextFromElement(productPrice).replace('$',' ').trim());
    }

    public Product getTheSinglePageProduct(){
        String removedProduct = getSingleProductName();
        String productDescription = getSingleProductDescription();
        double price = getThePriceOfTheSingleProduct();
        Product removedProductObj = new Product(removedProduct, productDescription, price);
        return removedProductObj;
    }
    public boolean clickOnRemoveBtn(){
        boolean removed = false;
        clickBtn(driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")));
        Product removedProductObj = getTheSinglePageProduct();
        CartPage cart = getHeader().clickOnCartItem();
        List<Product> productsInCart = cart.getProductsInCart();
        if (!productsInCart.contains(removedProductObj)){
          ExtentLogger.pass("The  Item was removed from the cart after the remove btn on the single item page was clicked");
            removed = true;
        }else{
            ExtentLogger.fail("The item was not removed from the cart after the remove btn on the single item page was clicked");
            removed = false;
        }
        return removed;
    }

    public boolean removingItemsFromCartOnSinglePage(){
        boolean added = false;
        Product removedProductObj = getTheSinglePageProduct();
        clickBtn(driver.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"))); //btn btn_secondary btn_small btn_inventory
        CartPage cart = getHeader().clickOnCartItem();
        List<Product> productsInCart = cart.getProductsInCart();
        if (productsInCart.contains(removedProductObj)){
         //   ExtentLogger.fail("The item was not added to the cart after the add btn on the single item page was clicked");
            added = false;
        }else{
       //     ExtentLogger.pass("The item was added to the cart after the add btn on the single item page was clicked");
            added = true;
        }
        return added;
    }
    public boolean addingItemsToCartFromSinglePage(){
        boolean added = false;
        clickBtn(driver.findElement(By.xpath("//button[text()='Add to cart']")));
        int itemsInCart = getHeader().getTheAmountOfTheItemsOnTheCartLogo();
        if(itemsInCart > 0){
            added = true;
        }
        return added;
    }


}
