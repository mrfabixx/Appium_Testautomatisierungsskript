package UI;

import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCases {

    static AndroidDriver androidDriver;
    private static final Logger logger = LogManager.getLogger(String.valueOf(TestCases.class));


    public static void main(String[] args) {

        try {
            androidDriver = Driver.buildDriver();

        } catch (Exception e) {
           logger.info("-- Android driver couldn't connect");
            e.printStackTrace();
        }

        //Aufrufen der Testfälle
//        addTask();
//        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        manageCategories();
//        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        createChecklist();

//        deleteTasks();

        shareTask();

        // Beenden des Android Driver nachdem Testskripte durchgelaufen sind
        androidDriver.quit();
        logger.info("-- Android driver was shut down after Testskript run");




    }
    //T1
    public static void addTask(){
        WebElement skipbutton = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_skip"));
        skipbutton.click();
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement addNotizButton = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_expand_menu_button"));
        addNotizButton.click();
        WebElement add_text = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_text"));
        add_text.click();
        WebElement etname = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etName"));
        etname.clear();
        etname.sendKeys("Notiz 1");
        WebElement category = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/text_input_end_icon"));
        category.isDisplayed();
        WebElement fab_menu = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_menu"));
        fab_menu.isDisplayed();
        fab_menu.click();
        WebElement btn_underline = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_underline"));
        btn_underline.isDisplayed();
        WebElement btn_italics = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_italics"));
        btn_italics.isDisplayed();
        WebElement btn_bold = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_bold"));
        btn_bold.isDisplayed();
        WebElement save_btn = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/action_save"));
        save_btn.click();
        WebElement recylerView = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recycler_view"));
        recylerView.isDisplayed();
        recylerView.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.secuso.privacyfriendlynotes:id/recycler_view\"]/android.widget.FrameLayout"));

    }

    public static void manageCategories(){
//        skipbutton.click();
        WebElement navigationDrawer = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]"));
        navigationDrawer.click();
        WebElement manacategories = androidDriver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id=\"org.secuso.privacyfriendlynotes:id/design_menu_item_text\" and @text=\"Manage categories\"]"));
        manacategories.isDisplayed();
        manacategories.click();

        //Erstellen der Kategorie Notizen
        WebElement enterTextName = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etName"));
        enterTextName.click();
        enterTextName.clear();
        enterTextName.sendKeys("Notizen ");

        WebElement add_btn = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_add"));
        add_btn.click();

        //Erstellen der Kategories Checklisten
        enterTextName.click();
        enterTextName.sendKeys("Checklisten");
        add_btn.click();

        //Erstellen der Kategorie Skizzen
        enterTextName.click();
        enterTextName.sendKeys("Skizzen");
        add_btn.click();

        WebElement backarrow = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
        backarrow.click();

        // Check if Kategorien erstellt worden sind im RecylerView //TODO: Recylerview schwer testbar in appium
        WebElement recylerView =  androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recyclerview_category"));
        List<WebElement> elements;

    }

    public static void createChecklist(){
       WebElement fab_menu_btn = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_expand_menu_button"));
       fab_menu_btn.click();
       WebElement createChecklist = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_checklist"));
       createChecklist.click();
       WebElement enterChecklistName = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etName"));
       enterChecklistName.click();
       enterChecklistName.clear();
       enterChecklistName.sendKeys("Checklist 1 ");

       WebElement newItem = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etNewItem"));
       newItem.click();
       newItem.sendKeys("Item 1 ");
       WebElement btn_add = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_add"));
       btn_add.click();

      newItem.click();
      newItem.sendKeys("item 2");
      btn_add.click();

      newItem.click();
      newItem.sendKeys("item 3");
      btn_add.click();

      WebElement backarrow = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
      backarrow.click();

      //TODO check ob Toast message erschienen ist

      //TODO: Check liste ob elemente richtig angezeigt werden

    }

    public static void deleteTasks(){
        addTask();
        WebElement reyclerview = androidDriver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.secuso.privacyfriendlynotes:id/recycler_view\"]/android.widget.FrameLayout"));
        reyclerview.click();
        WebElement options = androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"));
        options.click();
        WebElement deleteOption = androidDriver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id=\"org.secuso.privacyfriendlynotes:id/content\"])[2]"));
        deleteOption.click();
        androidDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        WebElement widgetbtn= androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
        widgetbtn.click();
        WebElement recylerView = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recycler_view"));
        if(!recylerView.isDisplayed()){
            System.out.println("Recylerview doesnt show");
        }

        //TODO check if item was deleted


    }

    public static void shareTask(){
        WebElement skipbutton = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_skip"));
        skipbutton.click();
        androidDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        WebElement menu = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_expand_menu_button"));
        menu.click();
        WebElement createtask = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_text"));
        createtask.click();
        WebElement enterName = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etName"));
        enterName.click();
        enterName.sendKeys("Notiz teilen auf Google Drive ");
        WebElement options = androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"));
        options.click();
        WebElement share_btn = androidDriver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id=\"org.secuso.privacyfriendlynotes:id/content\"])[3]"));
        share_btn.click();

        WebElement googleDriveIcon = androidDriver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"android:id/icon\"])[2]"));
        googleDriveIcon.click();

        WebElement googledriveSaveBtn = androidDriver.findElement(By.id("com.google.android.apps.docs:id/save_button"));
        googledriveSaveBtn.click();

    }

}
