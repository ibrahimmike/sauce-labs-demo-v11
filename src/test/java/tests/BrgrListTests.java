package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BrgrListTests extends BaseTest {
    @Test
    public void clickOnAllItems(){
        HomePage hp = lg.enterUserNameAndPassword("standard_user","secret_sauce").addItemsToCartAndGoToCartPage().
                getHeader().clickOnBrgrList().clickOnAllItemsAnchorTag();
        assertThat(hp.areTheItemsVisible()).isTrue();
    }
    @Test
    public void clickOnResetApp(){
        HomePage hp = lg.enterUserNameAndPassword("standard_user","secret_sauce").addToCartAndStayOnTheHomePage()
                .getHeader().clickOnBrgrList().clickOnResetAppStateAnchorTag();
        Assert.assertTrue(hp.checkIfThereAreNoItemsChosenAndTheCartIsEmpty(),"The page did not refresh after clicking on the burger list " +
                " anchor tag Rest App State");
    }
}
