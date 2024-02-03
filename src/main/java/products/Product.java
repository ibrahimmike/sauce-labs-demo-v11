package products;



public class Product implements Comparable<Product>  {
    private String productName = null;
    private String productDescription = null;

    private double productPrice = 0.0;

    public Product(String productName, String productDescription, double productPrice){
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }


    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public int compareTo(Product o) {
        if(this.getProductName().compareToIgnoreCase(o.getProductName())==0
                && this.productDescription.compareToIgnoreCase(o.productDescription)==0
                && this.getProductPrice() == o.getProductPrice()){
          //  ExtentLogger.log("The 2 products in comparison are identical");
            return 1;
        }else {
           // ExtentLogger.fail("They are not the same product");
            return 0;
        }
    }



}
