package LogOutFunctionality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LogOut {
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

    public void logoutFunctionalityTest() throws InterruptedException {
        System.out.println("Logging in with valid credentials...");
        login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        System.out.println("Clicking the hamburger menu...");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);
        
        System.out.println("Clicking Logout button...");
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(2000);

        System.out.println("Verifying user is redirected to login page...");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        if (loginButton.isDisplayed()) {
            System.out.println("Logout successful! User is back on the login page.");
        } else {
            System.out.println("Logout failed!");
        }
    }

    public void BrowserClose() {
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
        LogOut test = new LogOut();
        test.setup();
        test.logoutFunctionalityTest();
        test.BrowserClose();
    }
}
