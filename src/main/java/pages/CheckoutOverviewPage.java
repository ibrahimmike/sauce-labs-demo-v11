package pages;


import extentreport.ExtentLogger;
import frameworkproperties.ReadPropertyFiles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import products.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class CheckoutOverviewPage extends BasePage{
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    private List<WebElement> items = driver.findElements(By.className("cart_item"));
    private WebElement amountBeforeTax = driver.findElement(By.className("summary_subtotal_label"));
    private WebElement cancelBtn = driver.findElement(By.id("cancel"));
    private WebElement finishBtn = driver.findElement(By.id("finish"));
    private WebElement taxAmountField = driver.findElement(By.className("summary_tax_label"));

    private WebElement amountAfterTax = driver.findElement(By.cssSelector(".summary_info_label.summary_total_label"));


    public HomePage clickOnCancelBtn(){
        clickBtn(cancelBtn);
        return  new HomePage(driver);
    }
    public CheckoutComplete clickOnFinishBtn(){
        clickBtn(finishBtn);
        return new CheckoutComplete(driver);
    }
    public double getPriceBeforeTax(){
        String amountThree = getTextFromElement(amountBeforeTax).
                replace("Item total: ", " ").replace('$',' ').trim();
        return Double.parseDouble(amountThree);
    }
    public double getTaxPayable(){
        String taxAmount = getTextFromElement(taxAmountField).replace("Tax: $", " ").replace('$',' ').trim();
        return Double.parseDouble(taxAmount);
    }
    public double theSumOfTheProductsPrices(){
        List<Product> products = new ArrayList<>();
        for(WebElement item : items) {
            String name = getTextFromElement(item.findElement(By.className("inventory_item_name"))).trim();
            String description = getTextFromElement(item.findElement(By.className("inventory_item_desc")));
            double price = Double.parseDouble(getTextFromElement(item.findElement(By.className("inventory_item_price")))
                    .replace('$',' ').trim());
            Product product = new Product(name, description, price);
            products.add(product);
        }
        double sumOfTheAmount = products.stream().flatMapToDouble(product -> DoubleStream.of(product.getProductPrice())).sum();
       ExtentLogger.log("The amount of the sum of the products prices : " + sumOfTheAmount);

        return  sumOfTheAmount;
    }


    public boolean checkTheTotalAmountCalculation(){
        double sumOfTheProducts = theSumOfTheProductsPrices();
        double priceBeforeTax = getPriceBeforeTax();
        if(sumOfTheProducts==priceBeforeTax){
            return true;
        }else{
            return false;
        }

    }
    public double getTheTotalAmountAfterTaxAsShownOnThePage(){
        String importedAmount =  getTextFromElement(amountAfterTax).split(" ")[1].replace('$', ' ').trim();
        double amount = Double.parseDouble(importedAmount);
       // ExtentLogger.log("The total amount extracted from the page : " +importedAmount);
        return amount;
    }
    public boolean theTotalAmountAfterTaxEqualsTheCalculatedAmount(){
        DecimalFormat format =new DecimalFormat("#.##");
        double sumOfTheProducts = theSumOfTheProductsPrices();
        double taxAmountToBePaid = sumOfTheProducts * Double.parseDouble(ReadPropertyFiles.getPropertyValue("taxRate"));
        double amountToBePaid = sumOfTheProducts + taxAmountToBePaid;
        double amount = Double.parseDouble(format.format(amountToBePaid));
        if(amount==getTheTotalAmountAfterTaxAsShownOnThePage()){
          //  ExtentLogger.log("The calculated amount to be paid : " );
            return true;
        }else {

            return false;
        }
    }

    public HomePage clickOnCancel(){
        clickBtn(cancelBtn);
        return new HomePage(driver);
    }
}
