package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.checkerframework.checker.units.qual.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchApp {

    static AndroidDriver androidDriver;

    public static void main(String[] args) {

//
//        Driver driver = new Driver();
//        driver.buildDriver();


        try {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("deviceName", "Medium  Phone API 33 2");
            desiredCapabilities.setCapability("udid", "emulator-5554");
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("platformVersion", "13.0");
            desiredCapabilities.setCapability("app", "C:\\Users\\fhoti\\Downloads\\task.apk");
            desiredCapabilities.setCapability("automationName", "UIAutomator2");

            //Set up the Appium server URL to connect to
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            androidDriver = new AndroidDriver(appiumServer, desiredCapabilities);
            androidDriver.manage().timeouts();
            System.out.println("Application started...");
        } catch (Exception e) {
            System.out.println("Server could not Connect");
        }

//
        WebElement menulayout = androidDriver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"org.tasks:id/bottomAppBar\"]/android.widget.ImageButton"));
        menulayout.click();
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement settings = androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"org.tasks:id/text\" and @text=\"Settings\"]"));
        settings.click();
        WebElement notifications = androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Notifications\"]"));
        notifications.click();
        WebElement toolgeSwitch = androidDriver.findElement(By.xpath("(//android.widget.Switch[@resource-id=\"org.tasks:id/switchWidget\"])[1]"));
        if (!toolgeSwitch.isSelected()) {
            toolgeSwitch.click();
        }
        WebElement backarrow = androidDriver.findElement(By.className("android.widget.ImageButton"));
        backarrow.click();
        backarrow.click();


//            T1 Testcase Add a To do Task wich propertys and save the task
//            The task button to enter a task
        WebElement taskbutton = androidDriver.findElement(By.id("org.tasks:id/bottomAppBar"));
        taskbutton.click();
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //Enter your Task name
        String taskinput = "Aufgabe 1";
        WebElement taskName = androidDriver.findElement(By.id("org.tasks:id/title"));
        taskName.clear();
        taskName.sendKeys(taskinput);
        //Enter the Date is due

        //Selct a Date
        WebElement startDate = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"No due date\"]"));
        startDate.click();
//        selectDate("15 February 2024");

        WebElement okButton = androidDriver.findElement(By.id("org.tasks:id/ok_button"));
        okButton.click();
        // Prioroty Switches
        priorotyButton(1);
        priorotyButton(2);
        priorotyButton(3);
        priorotyButton(4);

        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        // Add a subtask
        WebElement element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Add subtask\"]"));
        element.click();
        element.clear();
//        element.sendKeys("Subtask 1 ");

        //Save the Task
        WebElement saveButton = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Save\"]"));
        saveButton.click();

        // Check the RecycleView List
        WebElement recycleView = androidDriver.findElement(By.id("org.tasks:id/recycler_view"));
        boolean isvisible = recycleView.isDisplayed();
        if (isvisible){
            System.out.println("Recycleview is visible..");
        }

        // Check if Task is displayed in the RecyclerView
        String xpathTemplate = "//android.widget.TextView[@resource-id=\"org.tasks:id/title\" and @text=\"%s\"]";
        String xpath = String.format(xpathTemplate,taskinput);
        WebElement taskinView = androidDriver.findElement(By.xpath(xpath));
        boolean isvisilbe = taskinView.isDisplayed();
        if (isvisilbe){
            System.out.println("Task is visible..");
        }

    }

    public static void selectDate(String date) {

        String xpath = String.format("\"//android.view.View[@content-desc=\\\"%s\\\"]\"", date);
        WebElement datumElement = androidDriver.findElement(By.xpath(xpath));
        datumElement.click();
    }

    public static void priorotyButton(int zahl) {
        String xpathtemplate = "//androidx.compose.ui.platform.ComposeView[@resource-id=\"org.tasks:id/compose_view\"]/android.view.View/android.view.View[2]/android.widget.RadioButton[%d]";
        String xpath = String.format(xpathtemplate, zahl);
        WebElement element = androidDriver.findElement(By.xpath(xpath));
        element.click();


    }


}
