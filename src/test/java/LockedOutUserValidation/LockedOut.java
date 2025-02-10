package LockedOutUserValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LockedOut {
    WebDriver driver;
    String baseURL = "https://www.saucedemo.com/";

    public void setup() throws InterruptedException {
       
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
        driver.manage().window().maximize();
        System.out.println("Navigated to SauceDemo homepage");
        Thread.sleep(2000);
    }

    public void lockedOutUserValidation() throws InterruptedException {
        System.out.println("Attempting to log in with locked-out credentials...");
        login("locked_out_user", "secret_sauce");
        Thread.sleep(2000);

        System.out.println("Verifying error message...");
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
        if (errorMessage.getText().equals(expectedMessage)) {
            System.out.println("Test Passed: Locked-out user cannot log in.");
        } else {
            System.out.println("Test Failed: Error message incorrect or missing.");
        }
    }

    public void ClosingBrowser() {
        System.out.println("Closing the browser...");
        driver.quit();
    }

    public void login(String username, String password) throws InterruptedException {
        System.out.println("Entering username...");
        driver.findElement(By.id("user-name")).sendKeys(username);
        Thread.sleep(1000);
        System.out.println("Entering password...");
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000);
        System.out.println("Clicking login button...");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        LockedOut test = new LockedOut();
        test.setup();
        test.lockedOutUserValidation();
        test.ClosingBrowser();
    }
}
