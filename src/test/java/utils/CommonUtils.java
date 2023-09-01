package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonUtils {


    DesiredCapabilities caps = new DesiredCapabilities();
    String path;
    public AppiumDriver<MobileElement> driver;

    public void setup(String platformName, String deviceName, String uri) throws MalformedURLException {
    	System.out.println("Session is creating");
		path = System.getProperty("user.dir");
    	caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Galaxy s22 FE");
		caps.setCapability("app", "C:\\Users\\Aditya Pawar\\eclipse-workspace\\TestDemoQA\\src\\test\\app\\HP600AndMaintenanceRealeaseBuildDate.20.03.2022v2.84.2.apk");
        //path+"//app//HP600AndMaintenanceRealeaseBuildDate.17.10.2022v2.82.7.apk"
		//caps.setCapability("autoGrantPermissions", "true");
        //caps.setCapability("fullReset", "true");
        caps.setCapability("udid","RZ8NA1P2S8D");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);

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


}