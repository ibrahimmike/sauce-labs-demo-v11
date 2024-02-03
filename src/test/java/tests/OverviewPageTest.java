package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OverviewPageTest extends BaseTest {
    @Test
    public void theSumOfTheItemsPriceShouldEqualThePriceBeforeTax(){
        boolean equals = lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage().
                clickOnCheckoutBtn().enterUserInfoAndContinue().checkTheTotalAmountCalculation();
        assertThat(equals).isTrue();
    }
    @Test
    public void taxAmountAndPriceAfterTax(){
        boolean equals = lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage().
                clickOnCheckoutBtn().enterUserInfoAndContinue().theTotalAmountAfterTaxEqualsTheCalculatedAmount();
        assertThat(equals).isTrue();

    }
    @Test
    public void clickOnCancelButtonBackToHomePage(){
        boolean equals = lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage().
                clickOnCheckoutBtn().enterUserInfoAndContinue().clickOnCancelBtn().checkIfThereAreNoItemsChosenAndTheCartIsEmpty();
        assertThat(equals).isFalse();
    }

}
