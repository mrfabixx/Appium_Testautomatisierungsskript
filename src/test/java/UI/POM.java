package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POM {

    WebDriver webDriver= null;
    By enterName = By.id("org.secuso.privacyfriendlynotes:id/etName");
    By menubtn= By.id("org.secuso.privacyfriendlynotes:id/fab_expand_menu_button");
    By add_textbtn = By.id("org.secuso.privacyfriendlynotes:id/fab_text");

    By save_btn = By.id("org.secuso.privacyfriendlynotes:id/action_save");

    public POM(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void setTextInput(String note){
        webDriver.findElement(enterName).sendKeys(note);

    }
    public void click_Menubtn(){
        webDriver.findElement(menubtn).click();
    }
    public void click_addTextbtn(){
        webDriver.findElement(add_textbtn).click();

    }
    public void setSave_btn(){
        webDriver.findElement(save_btn).click();

    }





}
