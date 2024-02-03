package products;

import frameworkproperties.ReadPropertyFiles;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<Product> cartItems = new ArrayList<>();


    public Cart(){

    }

    public  void addToCart(Product product){
        cartItems.add(product);
    }

    public void removeFromCart(Product product){
        cartItems.remove(product);
    }

    public  double amountToPayBeforeTaxes(){
        double amount = 0.0;
        for(Product product : cartItems){
            amount += product.getProductPrice();
        }
        return amount;
    }

    public double taxOnTheItemsChosen(){
        double amountBeforeTax = amountToPayBeforeTaxes();
        double taxRate = Double.parseDouble(ReadPropertyFiles.getPropertyValue("taxRate").trim());
        System.out.println(taxRate);
        return Double.parseDouble(String.format("%.2d",(amountBeforeTax *taxRate)));
    }

    public double getTotalPricePaid(){
        return  Double.parseDouble(String.format("%.2d", amountToPayBeforeTaxes() + taxOnTheItemsChosen()));
    }
    public int getCatSize(){
        return cartItems.size();
    }

    public  List<Product> getCart(){
        return cartItems;
    }

    public void emptyCart(){
        cartItems.clear();
    }
}
