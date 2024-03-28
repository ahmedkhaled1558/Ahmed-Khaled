package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage{ // This class is used to apply the Page Object Model design pattern
                       // and its the homepage that customer is moved to after the accepted login
    private WebDriver driver;
    private By products = By.className("title"); // It fetches products that has the classname of "title"
                                                 // if Products's displayed on the screen means the user's logged in successfully
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getloginStatus(){
        return driver.findElement(products).getText();
    } // it's the string word "Products"
}
