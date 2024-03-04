package UI;

import io.appium.java_client.android.AndroidDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCases {

    static AndroidDriver androidDriver;
    private static final Logger logger = LogManager.getLogger(String.valueOf(TestCases.class));
    static Timer timer = new Timer();


    public static void main(String[] args) throws IOException {

        try {
            androidDriver = Driver.buildDriver();


        } catch (Exception e) {
            logger.info("-- Android driver couldn't connect");
            e.printStackTrace();
        }

        // Text inputs für Testfälle
        String noteTile = "";

        String firstCategorie = "";
        String secondCategorie = "";
        String thirdCategrie = "";

        String checklistTitle = "";
        String item1 = "";
        String item2 = "";
        String item3 = "";


        // Read Json File Data for Test input
        String data = new String(Files.readAllBytes(Paths.get("src/test/resources/testData.json")));
        JSONArray jsonArray = new JSONArray(data);
        JSONObject firstelement = jsonArray.getJSONObject(0);
        JSONArray categories = firstelement.getJSONArray("categories");

        for (int i = 0; i < categories.length(); i++) {
            firstCategorie = categories.getString(0);
            secondCategorie = categories.getString(1);
            thirdCategrie = categories.getString(2);
        }
        JSONObject secondelement = jsonArray.getJSONObject(1);
        noteTile = secondelement.getString("noteTitle");
        JSONObject thirdelement = jsonArray.getJSONObject(3);
        checklistTitle = thirdelement.getString("checklistTitle");
        JSONObject itemsobject = jsonArray.getJSONObject(3);
        JSONArray itemsarray = itemsobject.getJSONArray("checklistItems");

        for (int i = 0; i < itemsarray.length(); i++) {
            item1 = itemsarray.getString(0);
            item2 = itemsarray.getString(1);
            item3 = itemsarray.getString(2);
        }


        //Aufrufen der Testfälle
        WebElement skipbutton = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_skip"));
        skipbutton.click();

        addTask(noteTile);
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        manageCategories(firstCategorie, secondCategorie, thirdCategrie);
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        createChecklist(checklistTitle, item1, item2, item3);
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        deleteTasks(noteTile);
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        shareTask();

        // Beenden des Android Driver nachdem Testskripte durchgelaufen sind
        androidDriver.quit();
        System.out.println("-- Android driver was shut down after Testskript run");

    }

    //T1
    public static void addTask(String note) {
        POM element = new POM(androidDriver);
        timer.start();
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        element.click_Menubtn();
        element.click_addTextbtn();
        element.setTextInput(note);

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

        element.setSave_btn();

        WebElement recylerView = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recycler_view"));
        recylerView.isDisplayed();
        recylerView.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.secuso.privacyfriendlynotes:id/recycler_view\"]/android.widget.FrameLayout"));

        timer.stopAndPrintDuration();
    }

    public static void manageCategories(String firstCategorie, String secondCategorie, String thirdCategorie) {
        timer.start();
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
        enterTextName.sendKeys(firstCategorie);

        WebElement add_btn = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_add"));
        add_btn.click();

        //Erstellen der Kategories Checklisten
        enterTextName.click();
        enterTextName.sendKeys(secondCategorie);
        add_btn.click();

        //Erstellen der Kategorie Skizzen
        enterTextName.click();
        enterTextName.sendKeys(thirdCategorie);
        add_btn.click();

        WebElement backarrow = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
        backarrow.click();

        // Check if Kategorien erstellt worden sind im RecylerView //TODO: Recylerview schwer testbar in appium
//        WebElement recylerView = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recyclerview_category"));
//        List<WebElement> elements;

        timer.stopAndPrintDuration();

    }

    public static void createChecklist(String checklistTile, String item1, String item2, String item3) {
        timer.start();
        WebElement fab_menu_btn = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_expand_menu_button"));
        fab_menu_btn.click();
        WebElement createChecklist = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/fab_checklist"));
        createChecklist.click();
        WebElement enterChecklistName = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etName"));
        enterChecklistName.click();
        enterChecklistName.clear();
        enterChecklistName.sendKeys(checklistTile);

        WebElement newItem = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/etNewItem"));
        newItem.click();
        newItem.sendKeys(item1);
        WebElement btn_add = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/btn_add"));
        btn_add.click();

        newItem.click();
        newItem.sendKeys(item2);
        btn_add.click();

        newItem.click();
        newItem.sendKeys(item3);
        btn_add.click();

        WebElement backarrow = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
        backarrow.click();
        timer.stopAndPrintDuration();



    }

    public static void deleteTasks(String note) {

        timer.start();
        addTask(note);
        WebElement reyclerview = androidDriver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.secuso.privacyfriendlynotes:id/recycler_view\"]/android.widget.FrameLayout"));
        reyclerview.click();
        WebElement options = androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"));
        options.click();
        WebElement deleteOption = androidDriver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id=\"org.secuso.privacyfriendlynotes:id/content\"])[2]"));
        deleteOption.click();
        androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        WebElement widgetbtn = androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
        widgetbtn.click();
        WebElement recylerView = androidDriver.findElement(By.id("org.secuso.privacyfriendlynotes:id/recycler_view"));
        if (!recylerView.isDisplayed()) {
            System.out.println("Recylerview doesnt show");
        }
        timer.stopAndPrintDuration();


    }

    public static void shareTask() {
        timer.start();
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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

        timer.stopAndPrintDuration();
    }

}
