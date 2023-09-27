package testcases;

import Base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class Edit_about_me_section extends TestBase {

    Register register = new Register();
    static By profileBtn = By.xpath("//android.widget.FrameLayout[@content-desc=\"Profile\"]/android.view.ViewGroup/android.widget.TextView");
    static By sideAddBtn =  By.id("com.wavar:id/add_joddhanda_lyt");
        public void editAbout(){
            waitForElement(profileBtn);
            driver.findElement(profileBtn).click();
            swipe(858,2057,735,968);
        }
        public void sideBussiness_section(){


        }



    @Test()
        public void Edit_about_me_Section() throws IOException, InterruptedException {
        register.Select_lang();
        register.enterNumber();
        register.Notificationbaropen();
        register.ReadOTP();
        register.register_yourself();
    }

}
