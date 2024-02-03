package pages;

//import extentreport.ExtentLogger;
import extentreport.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import products.Cart;
import products.Product;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{
    protected HomePage(WebDriver driver) {
        super(driver);
    }



    List<WebElement> items = getWebElementsWhenTheyAreVisible(driver.findElements(By.className("inventory_item")));

    public List<WebElement> getListOfItems(){
        return getWebElementsWhenTheyAreVisible(items);
    }


    public Header getHeader(){
        return new Header(driver);
    }
    public Footer getFooter(){
        return new Footer(driver);
    }

    public boolean areTheItemsVisible(){
        boolean visible = false;
        if(checkItemsVisibility(items)){
            visible = true;
        }
        return visible;
    }

    private Cart addItemsToCart(){
        Cart cart = new Cart();

        Product product;
        for (WebElement item : items.subList(0,4)){
            String productName = getTextFromElement(item.findElement(By.className("inventory_item_name"))).trim();
            String description = getTextFromElement(item.findElement(By.className("inventory_item_desc"))).trim();
            double price = Double.parseDouble(getTextFromElement(item.findElement(By.className("inventory_item_price"))).substring(1).trim());
            clickBtn(item.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")));
            product = new Product(productName, description, price);
            cart.addToCart(product);
        }
        if(cart.getCatSize() > 0){
          ExtentLogger.pass("Cart has been added");
        }else{
           ExtentLogger.fail("Cart has been not added");
        }
        return cart;

    }
    private Cart getTheItemsObjectOnTheHomePage(){
        List<WebElement> newItems = new HomePage(driver).getListOfItems();
        Cart cart = new Cart();
        for (WebElement item : newItems) {
            String productName = item.findElement(By.className("inventory_item_name")).getText()
                    .replaceAll("Sauce Labs ", " ").trim();
            String description = getTextFromElement(item.findElement(By.className("inventory_item_desc"))).trim();
            double price = Double.parseDouble(getTextFromElement(item.findElement(By.className("inventory_item_price"))).replace('$', ' ')
                    .trim());

            Product product = new Product(productName, description, price);
            cart.addToCart(product);
        }

        return cart;

    }


    public CartPage addItemsToCartAndGoToCartPage(){
        addItemsToCart();
        return getHeader().clickOnCartItem();
    }
    public boolean addingAndRemovingFromCartNumberChanges(){
        List<WebElement> addedItems = new ArrayList<>();
        boolean functional = false;
        for(int i = 0; i<3 ; i++){
            WebElement addBtn = items.get(i).findElement(By.xpath("//button[text()='Add to cart']"));
            clickBtn(addBtn);
            addedItems.add(addBtn);
        }
        clickBtn(driver.findElements(By.xpath("//button[text()='Remove']")).get(0));
        int amountOfItemsOnTheCart = getHeader().getTheAmountOfTheItemsOnTheCartLogo();
        if(amountOfItemsOnTheCart == 2){
            functional = true;
        } else if (addedItems.isEmpty()) {
            functional = false;
          //  ExtentLogger.fail("The user can't add Items to the cart");
        } else if (amountOfItemsOnTheCart > 2) {
            functional = false;
          //  ExtentLogger.fail("The user can't remove items from the Home page");
        }
        return functional;
    }





    public boolean headerIsVisible(){
        boolean headerIsVisible = getHeader().checkTheAvailabilityOfTheLogo();
        boolean secHeader = getHeader().secondaryHeaderIsVisible();

        if(headerIsVisible && secHeader){
            return true;
        }
        return false;
    }
    public boolean footerIsVisible(){
        return getFooter().CheckVisibilityOfTheFooterElements();
    }
    public boolean checkFilterOrderingZToA(){
        boolean orderingZtoA = checkOrdering("Name (Z to A)");
        return orderingZtoA;
    }
    public boolean checkFilterOrderingPriceLowToHigh(){
        boolean orderingPriceLowToHigh = checkOrdering("Price (low to high)");
        return orderingPriceLowToHigh;
    }
    public boolean checkFilterOrderingPriceHighToLow(){
        boolean orderingHighToLow = checkOrdering("Price (high to low)");
        return orderingHighToLow;
    }

    public boolean checkFilterOrderingAtoZ(){
        boolean checkOrderingAtoZ = checkOrdering("Name (A to Z)");
        return  checkOrderingAtoZ;
    }

    public List<Product> getTheItemsChosenByTheUserFromTheHomePage(){
        Cart chosenItems = addItemsToCart();
        return chosenItems.cartItems;
    }




    private boolean checkOrdering(String typeOfComparison){

        boolean check = true;
        WebElement filter =  getHeader().getFilter();
        clickBtn(filter);
        if(filter.isDisplayed()) {
            List<Product> listOfItems ;
            Select selectObj = new Select(filter);
            if(typeOfComparison.equalsIgnoreCase("Name (A to Z)" )){
                selectObj.selectByVisibleText(typeOfComparison);
                Cart cart = getTheItemsObjectOnTheHomePage();
                listOfItems = cart.getCart();
                String lastProduct = listOfItems.get(listOfItems.size()-1).getProductName();
                for (int i=1; i< listOfItems.size() -1; i++){
                    if(listOfItems.get(i).getProductName().compareToIgnoreCase(listOfItems.get(i+1).getProductName()) > 0){
                        check =  false;
                    //    ExtentLogger.fail("The filter doesn't sort the names in order from A to Z");
                        break;
                    }
                }
                cart.emptyCart();

            } else if (typeOfComparison.equalsIgnoreCase("Name (Z to A)")) {
                selectObj.selectByVisibleText(typeOfComparison);
                Cart cart = getTheItemsObjectOnTheHomePage();
                listOfItems = cart.getCart();
                for (int i=0; i< listOfItems.size() -1 ; i++){
                    if(listOfItems.get(i).getProductName().compareToIgnoreCase(listOfItems.get(i+1).getProductName()) < 0){
                        check = false;
                    //    ExtentLogger.fail("The filter doesn't sort the names in order from Z to A");
                        break;
                    }
                }
                cart.emptyCart();


            } else if (typeOfComparison.equalsIgnoreCase("Price (low to high)")) {
                selectObj.selectByVisibleText(typeOfComparison);
                Cart cart = getTheItemsObjectOnTheHomePage();
                listOfItems = cart.getCart();
                for (int i =0; i< listOfItems.size() -1 ; i++){
                    if(listOfItems.get(i).getProductPrice() > listOfItems.get(i+1).getProductPrice()){
                        check = false;
                      //  ExtentLogger.fail("The filter doesn't sort the prices from low to high");
                        break;
                    }
                }
                cart.emptyCart();

            } else if (typeOfComparison.equalsIgnoreCase("Price (high to low)")) {
                selectObj.selectByVisibleText(typeOfComparison);
                Cart cart = getTheItemsObjectOnTheHomePage();
                listOfItems = cart.getCart();
                for (int i = 0; i<listOfItems.size() -1; i++){
                    if(listOfItems.get(i).getProductPrice() < listOfItems.get(i+1).getProductPrice()){
                        System.out.println(listOfItems.get(i).getProductPrice());
                        check = false;
                      //  ExtentLogger.fail("The filter doesn't sort the prices from high to low " + listOfItems.get(i).getProductPrice() +
                        //        " < " + listOfItems.get(i+1).getProductPrice());
                        break;
                    }
                }
                cart.emptyCart();
            }

        }
        return check;
    }

    public SingleProductPage clickOnTheItemTitle(){
        WebElement firstItem = items.get(0);

        clickBtn(firstItem.findElement(By.className("inventory_item_name")));
        return new SingleProductPage(driver);
    }
    public Product getTheProductToBeClicked(){
        WebElement firstItem = items.get(0);
        String productName = getTextFromElement(firstItem.findElement(By.className("inventory_item_name"))).trim();
        String description = getTextFromElement(firstItem.findElement(By.className("inventory_item_desc"))).trim();
        double price = Double.parseDouble(getTextFromElement(firstItem.findElement(By.className("inventory_item_price"))).substring(1).trim());
        Product product = new Product(productName, description, price);
        return product;

    }
    public HomePage addToCartAndStayOnTheHomePage(){
        addItemsToCart();
        return this;
    }
    public boolean checkIfThereAreNoItemsChosenAndTheCartIsEmpty(){
        boolean empty = false;
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));
        int amountInCart = getHeader().getTheAmountOfTheItemsOnTheCartLogo();
        if((removeButtons.size()< 1) && (amountInCart == 0)){
            empty = true;
        }else{
           // ExtentLogger.log("The amount of buttons with the value remove is : " + removeButtons.size() +" the page did not refresh after reset");
        }
        return empty;
    }
    public boolean checkIfTheAmountChosenFromThePageAreTheSameOnTheCart(){
        List<Product> chosenItems = getTheItemsChosenByTheUserFromTheHomePage();
        List<Product> itemsOnCart = getHeader().clickOnCartItem().getProductsInCart();
        if(chosenItems.size() == itemsOnCart.size()){
            ArrayList<Integer> errors = new  ArrayList<>();
            for(Product p : chosenItems){
                if(p.compareTo(itemsOnCart.get(chosenItems.indexOf(p)))==1){
                    errors.add(0);
                }else{
                    errors.add(1);
                }
            }
            if (errors.contains(1)){
                return false;
            }
            else{
                return true;
            }
        }else {
           // ExtentLogger.fail("The amount of items chosen is not the same amount as the items on cart ");
            return false;
        }
    }
}
