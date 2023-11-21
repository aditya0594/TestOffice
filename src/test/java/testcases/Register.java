package testcases;

import Base.TestBase;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Register extends TestBase {
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
    static By TextMessage =By.id("android:id/message_text");
    By Content_delete_pop = By.id("com.hp.impulse.sprocket:id/tv_detail");
    By Account_deleted_ok_btn = By.id("com.hp.impulse.sprocket:id/bt_ok");
    By Account_deleted= By.id("com.hp.impulse.sprocket:id/tv_alert");

    public static void Slide_touch_mobile (int startx, int starty, int endX, int endY ) throws InterruptedException {
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
    public void Notificationbaropen() throws InterruptedException {
        swipe(531, 51, 730, 1781);
    }

    public void ReadOTP() throws InterruptedException {
        waitForElement(TextMessage);
        if(driver.findElement(TextMessage).isDisplayed()) {
            String messageText = driver.findElement(By.id("android:id/message_text")).getText();
            System.out.println("Size of the message : " + messageText);
            System.out.println("OTP Extracted : " + messageText.substring(0, 4));
            HashMap opt = new HashMap();
            opt.put(1, messageText.substring(0, 1));
            opt.put(2, messageText.substring(1, 2));
            opt.put(3, messageText.substring(2, 3));
            opt.put(4, messageText.substring(3, 4));
            String OTP1 = (String) opt.get(1);
            String OTP2 = (String) opt.get(2);
            String OTP3 = (String) opt.get(3);
            String OTP4 = (String) opt.get(4);
            swipe(257,2328, 448, 70);
            driver.findElement(onenumberOTP).sendKeys(OTP1);
            driver.findElement(twonumberOTP).sendKeys(OTP2);
            driver.findElement(threenumberOTP).sendKeys(OTP3);
            driver.findElement(fournumberOTP).sendKeys(OTP4);
            waitForElement(VerifyBtn);
            //Js_click(VerifyBtn, 530, 2123);
            WebElement element = driver.findElement(VerifyBtn);
            click_Point(VerifyBtn);


        }
        else{
            waitForElement(TextMessage);
            System.out.println("OTP is not recevied yet");
        }
    }
    public void NotificationClose() throws InterruptedException {
        swipe(257,2328, 448, 70);

    }
    public void register_yourself() throws InterruptedException {
        Thread.sleep(15000);

    }


    @Test(enabled = true)
    public void Register() throws InterruptedException, IOException {
        Select_lang();
        enterNumber();
        Notificationbaropen();
        ReadOTP();
        register_yourself();

    }




}
