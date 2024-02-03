package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import products.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleProductPageTest extends BaseTest{
    @Test
    public void clickOnItemTitleReturnTheSinglePageOfTheSameProduct(){
        HomePage homePage = lg.enterUserNameAndPassword("standard_user","secret_sauce");
        Product firstProduct = homePage.getTheProductToBeClicked();
        Product spProduct = homePage.clickOnTheItemTitle().getTheSinglePageProduct();
        assertThat(firstProduct.compareTo(spProduct)==1).isTrue();
    }
    @Test
    public void addingAnRemovingItemsOnSinglePage(){
        boolean added = lg.enterUserNameAndPassword("standard_user","secret_sauce")
                .clickOnTheItemTitle().addingItemsToCartFromSinglePage();
        assertThat(added).isTrue();

    }
    @Test
    public void removingItemsFromTheCartOnTheSingleProductPage(){
        boolean added = lg.enterUserNameAndPassword("standard_user","secret_sauce").
                addToCartAndStayOnTheHomePage().clickOnTheItemTitle().removingItemsFromCartOnSinglePage();
        assertThat(added).isTrue();
    }
}
