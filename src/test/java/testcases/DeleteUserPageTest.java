package testcases;

import Base.TestBase;
import androidpageobjectss.DeletePageAndroid;
import androidpageobjectss.LandingPageAndroid;
import org.testng.annotations.Test;

public class DeleteUserPageTest extends TestBase {

    LandingPageAndroid LandingPage= new  LandingPageAndroid();
    DeletePageAndroid DeletePage = new DeletePageAndroid();

    @Test(priority = 1, enabled = true)
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
