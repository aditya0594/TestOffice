package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


public class CommonUtils {


    DesiredCapabilities caps = new DesiredCapabilities();
    String path;
    public static AppiumDriver driver;

    public void setup(String platformName, String deviceName, String uri) throws MalformedURLException, URISyntaxException {
    	System.out.println("Session is creating");
		path = System.getProperty("user.dir");
    	caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Galaxy s22 FE");
		//caps.setCapability("app", "C:\\Users\\Aditya Pawar\\eclipse-workspace\\TestDemoQA\\src\\test\\app\\WavarApp_V_1.107_QA.apk");
        //path+"//app//HP600AndMaintenanceRealeaseBuildDate.17.10.2022v2.82.7.apk"
		caps.setCapability("autoGrantPermissions", "true");
        //hide the keyboard
        //caps.setCapability("unicodeKeyboard", true);
      //  caps.setCapability("resetKeyboard", true);
        //caps.setCapability("fullReset", "true");
        //Specify appPackage and appActivity for the app you are automating
        //caps.setCapability("appPackage", "com.example.yourapp");  // Replace with your app's package name
        caps.setCapability("appActivity", "com.wavar.view.activity.PostCommentReplyActivity - Wavar");   // Replace with your main activity's name
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("udid", "RZ8NA1P2S8D");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
        //driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public static Properties read_properties() throws IOException {
        File file = new File("src/test/java/resources/config.properties");
        Properties prop = new Properties();
        InputStreamReader is = new InputStreamReader(new FileInputStream(file));
        prop.load(is);
        return prop;
    }
    public static void main(String... args) throws IOException {
        CommonUtils.read_properties();
    }

    static Process serverProcess ;
    public static void startServer() throws IOException {
        String[] command = {"cmd.exe", "/c", "start", "cmd.exe", "/k", "appium -a 127.0.0.1 -p 4723 --base-path /wd/hub --allow-cors"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        // Redirecting error and output streams
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        serverProcess = processBuilder.start();
    }
    private static boolean isServerRunning() {
        try {
            URL status = new URL("http://127.0.0.1:4723/wd/hub/status");
            HttpURLConnection connection = (HttpURLConnection) status.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }
    public static void killServer() {
        if (serverProcess != null) {
            serverProcess.destroy();
        }
    }


    private PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        public void tap(int x, int y) {
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(tap));
        }

    }