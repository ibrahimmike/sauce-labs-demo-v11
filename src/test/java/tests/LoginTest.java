package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest(){
        lg.enterUserNameAndPassword("standard_user","secret_sauce");
    }
    @Test
    public void lockedOutUserLogin(){
        String lockedOutMessage = lg.getErrorLogin("locked_out_user", "secret_sauce");
        assertThat(lockedOutMessage).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void loginLogout(){
        LoginPage loginPage = lg.enterUserNameAndPassword("standard_user","secret_sauce").
                getHeader().clickOnBrgrList().clickOnLogoutAnchorTag();
        assertThat(loginPage.checkIfLoginBtnIsVisible()).isTrue();
    }
}
