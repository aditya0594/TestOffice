
package testcases;

import testcases.Base.TestBase;
import androidpageobjectss.LandingPageAndroid;
import org.testng.annotations.Test;


public class LandingPageTest extends TestBase {
	
	public LandingPageAndroid LandingPage = new LandingPageAndroid();

    @Test(priority = 1, enabled = true)
    public void Sign_up() throws InterruptedException {
      // LandingPage.verify_signup_disble_firstname();
       LandingPage.verify_signup_disble_email();
       LandingPage.verify_signup_disble_password();
       LandingPage.password_validation();
       LandingPage.password_eye_btn();
       LandingPage.Sign_up();
       LandingPage.get_started_screen_After_signup();
       LandingPage.hamburger_Account_Setting();
       LandingPage.verify_account_name();
    }


    @Test(priority = 2, enabled = true)
    public void Delete_user() throws InterruptedException {
        LandingPage.Normal_login();
        LandingPage.get_started_screen();
        Thread.sleep(2000);
        LandingPage.hamburger_Account_Setting();
        Thread.sleep(2000);
        LandingPage.delete_account();
        LandingPage.delete_account_ok_popup();
    }


}
