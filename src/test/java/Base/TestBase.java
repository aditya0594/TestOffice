package Base;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.AppConfigTags;
import utils.CommonUtils;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;




public class TestBase {

    public static AppiumDriver driver;
    public static WebDriver webdriver;

    //public LandingPageAndroid LandingPage;
//appium -a 127.0.0.1 -p 4723 --base-path /wd/hub –-allow-cors
   // appium -a 127.0.0.1 -p 4723 --base-path /wd/hub --allow-cors --use-plugins=gestures
    String drivertype ="appium";
    /*@BeforeSuite
    public void beforeSuitSetup() throws IOException, InterruptedException {
        CommonUtils.startServer();
        Thread.sleep(15000);
    }*/
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException, URISyntaxException {
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

	}
	@AfterSuite
	public void tearDown() {

        }
    @AfterTest
    public void closeApp(){

        }
 /*   @AfterClass
    public void appiumEnd() throws IOException, InterruptedException {
        if(drivertype == "appium")
        {
            CommonUtils.killServer();
        }
    }*/
    public static void waitForElement(By element) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }


    public static void swipe(int startX, int startY,int endX,int endY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down"); // Optionally you can still provide a direction
        scrollObject.put("startX", startX);
        scrollObject.put("startY", startY);
        scrollObject.put("endX", endX);
        scrollObject.put("endY", endY);
        js.executeScript("mobile: dragGesture", scrollObject);
        // Slide_touch_mobile(531, 51, 463, 1094);

    }
    public static void swipeUp(int startX, int startY,int endX,int endY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up"); // Optionally you can still provide a direction
        scrollObject.put("startX", startX);
        scrollObject.put("startY", startY);
        scrollObject.put("endX", endX);
        scrollObject.put("endY", endY);
        js.executeScript("mobile: dragGesture", scrollObject);
        // Slide_touch_mobile(531, 51, 463, 1094);

    }
    public static void pressAndRelease(int startX, int startY, int endX,int endY) {
        // Create a pointer input (finger simulation)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    // Press (at startX, startY)
        Sequence pressSequence = new Sequence(finger, 0);
        pressSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        pressSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    // Perform the press sequence
        driver.perform(Arrays.asList(pressSequence));
    // Move (or swipe) to (endX, endY) after pressing down
        Sequence moveSequence = new Sequence(finger, 0);
        moveSequence.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.pointer(), endX, endY));
    // Release
        Sequence releaseSequence = new Sequence(finger, 1);
        releaseSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
// Perform the move and release sequences
        driver.perform(Arrays.asList(moveSequence, releaseSequence));

    }
    public static void moveTo(int startX, int startY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> swipeObject = new HashMap<>();
        swipeObject.put("action", "moveTo");
        swipeObject.put("x", startX); // ending x-coordinate
        swipeObject.put("y", startY); // ending y-coordinate
        js.executeScript("mobile: touchAction", swipeObject);
    }
    public static void release(int startX, int startY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> releaseObject = new HashMap<>();
        releaseObject.put("action", "release");
        js.executeScript("mobile: touchAction", releaseObject);
    }



     public static void click_Point(By Locator) {
        WebElement Button = driver.findElement(Locator);
        Point source = Button.getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
// Move the finger to the desired location
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), source.x, source.y));
// Press down to start the touch
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
// Wait for a brief moment to simulate the duration of a tap
        sequence.addAction(new Pause(finger, Duration.ofMillis(100)));
// Release to end the touch
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
// Perform the entire sequence
        driver.perform(Arrays.asList(sequence));
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
     /*   TouchAction action = new  TouchAction(driver);
        action.tap(PointOption.point(startx, starty))
                .release()
                .perform();*/
    }
    public static void Slide_touch (int startx, int starty, int endX, int endY ) throws InterruptedException {
        // int startx = 568;
        // int starty = 2140 ;
/*
        TouchAction action = new  TouchAction(driver);
        action.press(PointOption.point(startx, starty))
                .waitAction()
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();*/
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
