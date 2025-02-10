package CheckOutProcess;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Checkout {
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
    
    

    public void checkoutProcess() throws InterruptedException {
        System.out.println("Logging in with valid credentials...");
        login("standard_user", "secret_sauce");
        Thread.sleep(2000);
        
        System.out.println("Adding a product to the cart...");
        driver.findElement(By.cssSelector(".btn_inventory")).click();
        Thread.sleep(1000);
        
        System.out.println("Navigating to cart page...");
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        
        System.out.println("Proceeding to checkout...");
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
        
        System.out.println("Entering checkout information...");
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        
        System.out.println("Verifying item in summary...");
        WebElement summaryItem = driver.findElement(By.className("cart_item"));
        
        
        if (summaryItem.isDisplayed()) {
            System.out.println("Item appears correctly in summary");
        } else {
            System.out.println("Item not found in summary");
        }
        Thread.sleep(2000);
        
        System.out.println("Completing the order...");
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
        
        System.out.println("Verifying success message...");
        WebElement successMessage = driver.findElement(By.className("complete-header"));
        
        if (successMessage.getText().equals("Thank you for your order!")) {
            System.out.println("Order completed successfully!");
        } else {
            System.out.println("Order completion failed!");
        }
        Thread.sleep(2000);
    }

    public void CloseBrowser() {
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
        Checkout test = new Checkout();
        test.setup();
        test.checkoutProcess();
        test.CloseBrowser();
    }
}
