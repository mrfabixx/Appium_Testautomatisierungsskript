package UI;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Driver {

       static AndroidDriver androidDriver;

        public static AndroidDriver buildDriver(){

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("deviceName", "Phone API 33");
            desiredCapabilities.setCapability("udid", "emulator-5554");
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("platformVersion", "13.0");
            desiredCapabilities.setCapability("app", "C:\\Users\\fhoti\\Downloads\\org.secuso.privacyfriendlynotes_18.apk");
            desiredCapabilities.setCapability("automationName", "UIAutomator2");
            desiredCapabilities.setCapability("appPackage","org.secuso.privacyfriendlynotes");
            desiredCapabilities.setCapability("appActivity","org.secuso.privacyfriendlynotes.ui.SplashActivity");


            try {
                URL appiumServer = new URL("http://localhost:4723/wd/hub");
                androidDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                androidDriver.manage().timeouts();
                System.out.println("Application started...");
            } catch (Exception e) {
                System.out.println("Server could not Connect");
            }

            return androidDriver;
        }
}
