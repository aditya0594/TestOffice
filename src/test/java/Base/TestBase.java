package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.AppConfigTags;
import utils.CommonUtils;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestBase {

    public static AppiumDriver driver;
    public static WebDriver webdriver;

    //public LandingPageAndroid LandingPage;
//appium -a 127.0.0.1 -p 4723 --base-path /wd/hub –allow-cors
    String drivertype ="appium";
    /*@BeforeSuite
    public void beforeSuitSetup() throws IOException, InterruptedException {
        CommonUtils.startServer();
        Thread.sleep(15000);
    }*/
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        switch(drivertype){
            case("appium"):
                System.out.println("Setup TestCase");
                CommonUtils utils = new CommonUtils();
                utils.setup(AppConfigTags.ANDROID, AppConfigTags.MOTOROLA, Constants.ANDROID_URI);
                driver = utils.driver;
                break;
            case("chrome"):
                WebDriverManager.chromedriver().setup();
                webdriver = new ChromeDriver();
                break;
            case("firefox"):
                WebDriverManager.firefoxdriver().setup();
                webdriver = new FirefoxDriver();
                break;
            case("edge"):
                WebDriverManager.edgedriver().setup();
                webdriver = new EdgeDriver();
                break;
            case("opera"):
                WebDriverManager.operadriver().setup();
                webdriver = new OperaDriver();
                break;
        }
    	 //configuration items to change the look and feel
         //add content, manage tests etc
    }

	@AfterMethod
	public void Aftertest() throws InterruptedException {
            driver.closeApp();
	}
	@AfterSuite
	public void tearDown() {
            driver.closeApp();
            CommonUtils.killServer();
        }
    @AfterTest
    public void closeApp(){
            driver.closeApp();
        }
 /*   @AfterClass
    public void appiumEnd() throws IOException, InterruptedException {
        if(drivertype == "appium")
        {
            CommonUtils.killServer();
        }
    }*/
    public static void waitForElement(By element) {
        WebDriverWait w = new WebDriverWait(driver,3);
        w.until(ExpectedConditions.presenceOfElementLocated ((By) element));
    }

    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");
    }
    public void scrollDown(String pixel) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + pixel + ")", "");
    }
    public static void Tap_screen (int startx, int starty) throws InterruptedException {
       // int startx = 568;
       // int starty = 2140 ;
        TouchAction action = new  TouchAction(driver);
        action.tap(PointOption.point(startx, starty))
                .release()
                .perform();
    }
    public static void Slide_touch (int startx, int starty, int endX, int endY ) throws InterruptedException {
        // int startx = 568;
        // int starty = 2140 ;
        TouchAction action = new  TouchAction(driver);
        action.press(PointOption.point(startx, starty))
                .waitAction()
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    public static void captureScreenShots(String Feature_name)throws IOException {
        String folder_name = "screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format fot screenshot file name
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name= Feature_name + df.format(new Date())+".png";
        //coppy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }
}
