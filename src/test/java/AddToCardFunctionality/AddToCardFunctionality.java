package AddToCardFunctionality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class AddToCardFunctionality {
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

    public void addToCartFunctionality() throws InterruptedException {
        System.out.println("Logging in with valid credentials...");
        login("standard_user", "secret_sauce");
        Thread.sleep(2000);
        
        System.out.println("Adding first two products to the cart...");
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        addToCartButtons.get(0).click();
        Thread.sleep(1000);
        addToCartButtons.get(1).click();
        Thread.sleep(1000);
        
        System.out.println("Verifying cart badge count...");
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        if (!cartBadge.getText().equals("2")) {
            System.out.println("Cart count mismatch");
        } else {
            System.out.println("Cart count is correct");
        }
        Thread.sleep(2000);
        
        System.out.println("Navigating to cart page");
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        
        System.out.println("Verifying products in the cart...");
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        
        if (cartItems.size() != 2) {
            System.out.println("Incorrect number of items in cart!");
        } else {
            System.out.println("Both products are present in the cart!");
        }
        Thread.sleep(2000);
        
        
        System.out.println("Removing items from the cart...");
        List<WebElement> removeButtons = driver.findElements(By.className("cart_button"));
        removeButtons.get(0).click();
        Thread.sleep(1000);
        removeButtons.get(1).click();
        Thread.sleep(1000);
        
        if (!driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            System.out.println("Cart badge still present!");
        } else {
            System.out.println("Cart is now empty!");
        }
        Thread.sleep(2000);
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
        AddToCardFunctionality test = new AddToCardFunctionality();
        test.setup();
        test.addToCartFunctionality();
        test.ClosingBrowser();
    }
}