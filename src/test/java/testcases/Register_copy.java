package testcases;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class Register_copy extends TestBase {
    String MobileNumber = "";
    static By pagetilte = By.id("com.wavar:id/lbl_language");
    static By EnglishBtn_verify = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[2]");
    static By HindiBtn_verify = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[1]");
    static By selectLang = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]");
    static By nxt_btn = By.id("com.wavar:id/btnNext");
    static By language_btn_numberScreen = By.id("com.wavar:id/lbl_language");
    static By Enter_Number = By.id("com.wavar:id/edtPhoneNumber");

    static By number = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText");
    static By checkBox = By.id("com.wavar:id/chk_terms_and_condition");
    static By sendOTP = By.id("com.wavar:id/btnOtp");
    static By onenumberOTP = By.id("com.wavar:id/otp_edit_box1");
    static By twonumberOTP = By.id("com.wavar:id/otp_edit_box2");
    static By threenumberOTP = By.id("com.wavar:id/otp_edit_box3");
    static By fournumberOTP = By.id("com.wavar:id/otp_edit_box4");

    static By VerifyBtn = By.id("com.wavar:id/btnVarify");
    static By ResName = By.id("com.wavar:id/editPersonName");
    //com.hp.impulse.sprocket:id/tv_detail
    By Content_delete_pop = By.id("com.hp.impulse.sprocket:id/tv_detail");
    By Account_deleted_ok_btn = By.id("com.hp.impulse.sprocket:id/bt_ok");
    By Account_deleted= By.id("com.hp.impulse.sprocket:id/tv_alert");

    public static void Slide_touch_mobile (int startx, int starty, int endX, int endY ) throws InterruptedException {


        // int startx = 568;
        // int starty = 2140 ;
       /* TouchAction action = new  TouchAction(driver);
        action.press(PointOption.point(startx, starty))
                .waitAction()
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();*/
    }
    public static void Select_lang() {
        String SelectYourLang = driver.findElement(pagetilte).getText();
        Assert.assertEquals("आपली भाषा निवडा",SelectYourLang);
        String BtnEngLang =driver.findElement(EnglishBtn_verify).getText();
        Assert.assertEquals("English",BtnEngLang);
        String BtnHindiLang =driver.findElement(HindiBtn_verify).getText();
        Assert.assertEquals("इंग्रजी",BtnHindiLang);
        waitForElement(HindiBtn_verify);
        driver.findElement(selectLang).click();
        waitForElement(nxt_btn);
        String NextButton =driver.findElement(nxt_btn).getText();
        Assert.assertEquals("Next",NextButton);
        driver.findElement(nxt_btn).click();
        waitForElement(language_btn_numberScreen);
        String numberscreen_language  =driver.findElement(language_btn_numberScreen).getText();
        Assert.assertEquals("English",numberscreen_language);

    }
    public void enterNumber() throws InterruptedException, IOException {
        driver.findElement(Enter_Number).click();
        Thread.sleep(3000);
        Properties properties = CommonUtils.read_properties();
        MobileNumber=properties.getProperty("Mobilenumber");

        new Actions(driver).sendKeys(MobileNumber).perform();
       // driver.findElement(Enter_Number).sendKeys("8668852556");
        driver.findElement(checkBox).click();
        String sendOTPVerifyText  =driver.findElement(sendOTP).getText();
        Assert.assertEquals("Send OTP",sendOTPVerifyText);
        driver.findElement(sendOTP).click();
        Thread.sleep(10000);

    }

/*    public void OpenNotification(int startX, int startY,int endX,int endY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down"); // Optionally you can still provide a direction
        scrollObject.put("startX", startX);
        scrollObject.put("startY", startY);
        scrollObject.put("endX", endX);
        scrollObject.put("endY", endY);
        js.executeScript("mobile: dragGesture", scrollObject);



        // Slide_touch_mobile(531, 51, 463, 1094);

    }*/

    public void OpenNotificationClose() throws InterruptedException {
        Slide_touch_mobile(257,2328, 448, 70);

    }
    public void register_yourself() throws InterruptedException {
        Thread.sleep(15000);

    }


    @Test(enabled = true)
    public void Register() {
        Select_lang();
       // enterNumber();
        //OpenNotification(531, 51, 730, 1781);
       // register_yourself();

    }


}
