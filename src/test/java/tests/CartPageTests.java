package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartPageTests extends BaseTest{
    @Test
    public void clickOnContinueShopping(){
        boolean changeOnTheCartAmountWhenContinueShopping =  lg.enterUserNameAndPassword("standard_user","secret_sauce")
                .addItemsToCartAndGoToCartPage().clickOnTheContinueShoppingAndCheckTheAmountOnCart();
        assertThat(changeOnTheCartAmountWhenContinueShopping).isTrue();
    }
    @Test
    public void removingItemsFromCart(){
        boolean removedItems = lg.enterUserNameAndPassword("standard_user","secret_sauce")
                .addItemsToCartAndGoToCartPage().whenClickingOnRemoveBtnTheItemIsRemovedFromCart();
        assertThat(removedItems).isTrue();
    }
}
