package CartBadgeCountValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class CartBadg {
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

    public void cartBadgeCountValidation() throws InterruptedException {
        System.out.println("Logging in with valid credentials...");
        login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        System.out.println("Adding multiple products to the cart...");
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        addToCartButtons.get(0).click();
        Thread.sleep(1000);
        addToCartButtons.get(1).click();
        Thread.sleep(1000);
        
        System.out.println("Verifying cart badge count updates correctly...");
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        if (cartBadge.getText().equals("2")) {
            System.out.println("Test Passed: Cart badge count is correct after adding items.");
        } else {
            System.out.println("Test Failed: Cart badge count is incorrect!");
        }
        Thread.sleep(2000);

        System.out.println("Removing an item and verifying badge count updates...");
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("cart_button")).click();
        Thread.sleep(2000);
        
        List<WebElement> updatedCartBadge = driver.findElements(By.className("shopping_cart_badge"));
        if (updatedCartBadge.isEmpty()) {
            System.out.println("Test Passed: Cart badge is updated correctly after item removal.");
        } else {
            System.out.println("Test Failed: Cart badge did not update correctly!");
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
        CartBadg test = new CartBadg();
        test.setup();
        test.cartBadgeCountValidation();
        test.ClosingBrowser();
    }
}
