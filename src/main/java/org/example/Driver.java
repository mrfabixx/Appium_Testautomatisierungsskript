//package org.example;
//
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.URL;
//
//public class Driver {
//
//        AndroidDriver androidDriver;
//
//        public AndroidDriver buildDriver(){
//            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability("deviceName", "Medium  Phone API 33 2");
//            desiredCapabilities.setCapability("udid", "emulator-5554");
//            desiredCapabilities.setCapability("platformName", "Android");
//            desiredCapabilities.setCapability("platformVersion", "13.0");
//            desiredCapabilities.setCapability("app", "C:\\Users\\fhoti\\Downloads\\task.apk");
//            desiredCapabilities.setCapability("automationName", "UIAutomator2");
//
//            try {
//                URL appiumServer = new URL("http://localhost:4723/wd/hub");
//                androidDriver = new AndroidDriver(appiumServer, desiredCapabilities);
//                androidDriver.manage().timeouts();
//                System.out.println("Application started...");
//            } catch (Exception e) {
//                System.out.println("Server could not Connect");
//            }
//
//            return androidDriver;
//        }
//
//
//        //Set up the Appium server URL to connect to
//
//
//}
