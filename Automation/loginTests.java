package login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class loginTests  { // This class includes all the testcases and the DataProvider
    private WebDriver driver;
    @Test (dataProvider = "credentials")
    public void VerifyLoginCredentials(String scenarioStatus, String userName, String password){ // We pass the three parameters from Object[][] below
       HomePage homePage = new HomePage(driver); //Creating an object from Class HomePage to use it for the design pattern benefit
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/"); // Opens the targeted website
        driver.findElement(By.id("user-name")).sendKeys(userName);   // Assigns the sent userName to the empty field of the username
        driver.findElement(By.id("password")).sendKeys(password);    // Assigns the sent password to the empty password field
        driver.findElement(By.id("login-button")).click();           // Clicks on the button

      if(scenarioStatus.equals("bothcorrect2")){ // The locked out user  (Passed - "locked_out_user","secret_sauce" )
            String errorMessage = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Sorry, this user has been locked out.')]")).getText();
            Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
        }
      else if(scenarioStatus.equals("bothcorrect3")){ // (Both inputs are accepted - Passed - "problem_user","secret_sauce")
            WebElement products = driver.findElement(By.className("title"));
          Assert.assertTrue(products.isDisplayed(), "Login Unsuccessfully");
        }
      else if(scenarioStatus.equals("bothcorrect1")){   // ( Both inputs are accepted - Passed - "standard_user","secret_sauce")
            assertEquals(homePage.getloginStatus(), "Products" , "Login Unsuccessfully"); // Here we use the homePage object to include the design pattern and same goes for the rest
        }

      else if(scenarioStatus.equals("bothcorrect4")){ // (Both inputs are accepted - Passed - "performance_glitch_user" , "secret_sauce" )
            assertEquals(homePage.getloginStatus(), "Products" , "Login Unsuccessfully");
        }
      else if(scenarioStatus.equals("bothcorrect5")){ //  (Both inputs are accepted - Passed - "error_user" , "secret_sauce" )
            assertEquals(homePage.getloginStatus(), "Products" , "Login Unsuccessfully");
        }
      else if(scenarioStatus.equals("bothcorrect6")){  // (Both inputs are accepted - Passed - "visual_user" , "secret_sauce" )
            assertEquals(homePage.getloginStatus(), "Products" , "Login Unsuccessfully");
        }
      else if(scenarioStatus.equals("correctusername")){ // (Accepted username with wrong password - Passed - "standard_user","nonsecret_sauce" )
            String errorMessage = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")).getText();
            Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
        }
      else if(scenarioStatus.equals("correctpassword")){ // (Wrong username with accepted password - Passed - "nonstandard_user","secret_sauce" )
            String errorMessage = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")).getText();
            Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
        }
      else if(scenarioStatus.equals("bothwrong")){ // (Wrong username and wrong password - Passed - "nonstandard_user","nonsecret_sauce" )
            String errorMessage = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")).getText();
            Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
        }
      else if(scenarioStatus.equals("bothempty")){ // (Empty inputs - Passed - "" , "" )
            String errorMessage = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username is required')]")).getText();
            Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
        }
    }

    @DataProvider (name = "credentials")
    public Object[][] getData(){ // 2d array that holds all the testcases
        return new Object[][]{
                {"bothcorrect2","locked_out_user","secret_sauce"},
                {"bothcorrect3","problem_user","secret_sauce"},
                {"bothcorrect1","standard_user","secret_sauce"},
                {"bothcorrect4","performance_glitch_user","secret_sauce"},
                {"bothcorrect5","error_user","secret_sauce"},
                {"bothcorrect6","visual_user","secret_sauce"},
                {"correctusername","standard_user","nonsecret_sauce"},
                {"correctpassword","nonstandard_user","secret_sauce"},
                {"bothwrong","nonstandard_user","nonsecret_sauce"},
                {"both empty","",""}
        };
    }
}
