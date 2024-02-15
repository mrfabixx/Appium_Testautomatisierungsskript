package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LaunchApp {

    static AndroidDriver androidDriver;

    public static void main(String[] args) {

        try {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("deviceName","Medium  Phone API 33 2");
            desiredCapabilities.setCapability("udid","emulator-5554");
            desiredCapabilities.setCapability("platformName","Android");
            desiredCapabilities.setCapability("platformVersion","13.0");
            desiredCapabilities.setCapability("app","C:\\Users\\fhoti\\Downloads\\task.apk");
            desiredCapabilities.setCapability("automationName","UIAutomator2");

            //Setup the Appium server URL to connect to

            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            androidDriver = new AndroidDriver(appiumServer, desiredCapabilities);
            System.out.println("Application started...");

        }catch (Exception e){
            System.out.println("Server could not Connect");
        }



    }



    @Test
    public void configureAppium() throws MalformedURLException {

        // create Capabilities
    }


}
