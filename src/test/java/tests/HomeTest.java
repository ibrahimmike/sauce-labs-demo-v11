package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomeTest extends BaseTest{
    @Test
    public void headerIsVisible(){
        boolean visible =  lg.enterUserNameAndPassword("standard_user","secret_sauce").headerIsVisible();
        assertThat(visible).isEqualTo(true);
    }
    @Test
    public void footerIsVisible(){
        boolean visible =  lg.enterUserNameAndPassword("standard_user","secret_sauce").footerIsVisible();
        assertThat(visible).isEqualTo(true);

    }
    @Test
    public void itemsAreVisible(){
        boolean visible = lg.enterUserNameAndPassword("standard_user","secret_sauce").areTheItemsVisible();
        assertThat(visible).isTrue();
    }
    @Test
    public void addingAndRemovingItemsFromCartOnHomePage(){
        boolean functional =  lg.enterUserNameAndPassword("standard_user","secret_sauce").addingAndRemovingFromCartNumberChanges();
        assertThat(functional).isTrue();
    }

    @Test
    public void standardUserFlow(){
        String orderConfirmationMessage = lg.enterUserNameAndPassword("standard_user","secret_sauce").
                addItemsToCartAndGoToCartPage().clickOnCheckoutBtn().
                enterUserInfoAndContinue().clickOnFinishBtn().getOrderConfirmationMessage();
        assertThat(orderConfirmationMessage).
                isEqualTo("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
    @Test
    public void orderingTheItemsUsingFiltersByNamesDescending(){
        boolean ordering =  lg.enterUserNameAndPassword("standard_user","secret_sauce").checkFilterOrderingZToA();
        assertThat(ordering).isTrue();
    }
    @Test
    public void orderingTheItemsUsingFiltersByNamesAscending() {
        boolean ordering = lg.enterUserNameAndPassword("standard_user", "secret_sauce").checkFilterOrderingAtoZ();
        assertThat(ordering).isTrue();
    }
    @Test
    public void orderingTheItemsUsingFilterPriceHighToLow(){
        boolean ordering = lg.enterUserNameAndPassword("standard_user", "secret_sauce").checkFilterOrderingPriceHighToLow();
        assertThat(ordering).isTrue();
    }
    @Test
    public void orderingTheItemsUsingFilterPriceLowToHigh(){
        boolean ordering = lg.enterUserNameAndPassword("standard_user", "secret_sauce").checkFilterOrderingPriceLowToHigh();
        assertThat(ordering).isTrue();
    }
    @Test
    public void itemsAreCorrectlyAddedToTheCart(){

        boolean checkItems =  lg.enterUserNameAndPassword("standard_user","secret_sauce").
                checkIfTheAmountChosenFromThePageAreTheSameOnTheCart();
        assertThat(checkItems).isTrue();
    }

}
