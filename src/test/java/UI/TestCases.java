package UI;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestCases {

    static AndroidDriver androidDriver;

    public static void main(String[] args) {

        try {
            androidDriver = Driver.buildDriver();

        } catch (Exception e) {
            System.out.println("Server could not Connect.." + e.getMessage());
            e.printStackTrace();
        }

        //Check the Setting menu
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
//        selectDate("20 February 2024");

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
        if (isvisible) {
            System.out.println("Recycleview is visible..");
        }

        // Check if Task is displayed in the RecyclerView
        String xpathTemplate = "//android.widget.TextView[@resource-id=\"org.tasks:id/title\" and @text=\"%s\"]";
        String xpathTask = String.format(xpathTemplate, taskinput);
        WebElement taskinView = androidDriver.findElement(By.xpath(xpathTask));
        boolean isvisilbe = taskinView.isDisplayed();
        if (isvisilbe) {
            System.out.println("Task is visible..");
        }
        //T3 TestCase edit the Task
        WebElement taskedit = androidDriver.findElement(By.id("org.tasks:id/title"));
        taskedit.click();
        WebElement subtask = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Add subtask\"]"));
        subtask.click();
        androidDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        WebElement editSubTask = androidDriver.findElement(By.xpath("//android.widget.EditText/android.view.View"));
        editSubTask.clear();
        editSubTask.sendKeys("Dies Sind Subtask informationen");
        WebElement timeEdit = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Timer\"]"));
        timeEdit.click();
        WebElement estimatedDuration= androidDriver.findElement(By.id("org.tasks:id/estimatedDuration"));
        estimatedDuration.click();
        WebElement incrementTime = androidDriver.findElement(By.xpath("(//android.widget.ImageButton[@resource-id=\"org.tasks:id/increment\"])[1]"));
        incrementTime.click();
        WebElement confirmButton = androidDriver.findElement(By.id("android:id/button1"));
        confirmButton.click();
        confirmButton.click();
        saveButton.click();
//        WebElement checkBoxSubtask = androidDriver.findElement(By.xpath("(//androidx.compose.ui.platform.ComposeView[@resource-id=\"org.tasks:id/chip_group\"])[1]/android.view.View/android.view.View[1]/android.widget.CheckBox"));
//        checkBoxSubtask.click();
//        androidDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
//
//        try{
//            androidDriver.wait(ExpectedCondition
//        }










        // T8 TestCase delete Task und check if Task was deleted from Recyclerview
//        WebElement rowbodyLongpress = androidDriver.findElement(AppiumBy.id("org.tasks:id/rowBody"));
//        TouchAction touchAction = new TouchAction(androidDriver);
//        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(rowbodyLongpress)))
//                .perform();
//
//        WebElement threedotsmenu = androidDriver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"More options\"])[2]"));
//        threedotsmenu.click();
//        WebElement deleteButton = androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"org.tasks:id/title\" and @text=\"Delete\"]"));
//        deleteButton.click();
//        WebElement popupwindow = androidDriver.findElement(By.id("android:id/button1"));
//        popupwindow.click();
//
//        // Check if Task item was deleted succesfully
//        try {
//            WebElement scrollView = androidDriver.findElement(By.className("android.widget.ScrollView"));
//            androidDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
//            WebElement searchedItem = scrollView.findElement(By.xpath(xpathTask));
//            if (searchedItem.isDisplayed()){
//                System.out.println("Task still in the Scrollview");
//            }
//        }catch (NoSuchElementException e){
//            System.out.println("Task was deleted successfully");
//        }


        //T3 TestCase



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
