package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class YourInformationPageTest extends BaseTest{
    @Test
    public void theUserAddsOnlyHisFirstName(){
        String errorMessage = lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage()
                .clickOnCheckoutBtn().theUserEntersUserFirstNameOnly().getTheErrorMessageMissingInformationError();
        assertThat(errorMessage).isEqualTo("Error: Last Name is required");
    }
    @Test
    public void theUserEntersLastNameOnly(){
        String errorMessage =  lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage()
                .clickOnCheckoutBtn().theUserEntersUserLastNameOnly().getTheErrorMessageMissingInformationError();
        assertThat(errorMessage).isEqualTo("Error: First Name is required");
    }
    @Test
    public void theUserEntersFirstNameAndLastNameNotTheZipCode(){
        String errorMessage =  lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage()
                .clickOnCheckoutBtn().theUserEntersFirstNameAndLastNameNoZipCode().getTheErrorMessageMissingInformationError();
        assertThat(errorMessage).isEqualTo("Error: Postal Code is required");
    }
    @Test
    public void enterDataAndClickOnCancelTheItemsOnCartAppear(){
        boolean itemsAreVisibleOnCartPage =  lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage()
                .clickOnCheckoutBtn().theUserEntersZipCodeOnly().clickOnTheCancelBtnAndReturnToCartPage().theItemsAreVisibleOnTheCart();

        assertThat(itemsAreVisibleOnCartPage).isTrue();
    }
}
