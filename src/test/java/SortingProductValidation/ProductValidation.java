package SortingProductValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

public class ProductValidation {
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
    
    

    public void sortingProductsValidation() throws InterruptedException {
        System.out.println("Logging in with valid credentials...");
        
        login("standard_user", "secret_sauce");
        Thread.sleep(2000);
        
        System.out.println("......Sorting products by Price (low to high)");
        sortProducts("lohi");
        Thread.sleep(2000);
        verifySorting("price", true);
        
        System.out.println("......Sorting products by Price (high to low)");
        sortProducts("hilo");
        Thread.sleep(2000);
        verifySorting("price", false);
        
        System.out.println("...Sorting products by Name (A to Z)");
        sortProducts("az");
        Thread.sleep(2000);
        verifySorting("name", true);
        
        System.out.println("Sorting products by Name (Z to A)...");
        sortProducts("za");
        Thread.sleep(2000);
        verifySorting("name", false);
    }
    

    public void sortProducts(String sortOption) {
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByValue(sortOption);
    }

    public void verifySorting(String type, boolean ascending) {
        List<WebElement> productElements;
        if (type.equals("price")) {
            productElements = driver.findElements(By.className("inventory_item_price"));
        } else {
            productElements = driver.findElements(By.className("inventory_item_name"));
        }
        
        boolean isSorted = checkSorting(productElements, ascending, type.equals("price"));
        if (isSorted) {
            System.out.println("Products sorted correctly.");
        } else {
            System.out.println("Sorting failed!");
        }
    }
    

    public boolean checkSorting(List<WebElement> elements, boolean ascending, boolean isPrice) {
        for (int i = 0; i < elements.size() - 1; i++) {
            String value1 = elements.get(i).getText().replace("$", "");
            String value2 = elements.get(i + 1).getText().replace("$", "");
            
            if (isPrice) {
                double num1 = Double.parseDouble(value1);
                double num2 = Double.parseDouble(value2);
                if (ascending && num1 > num2) return false;
                if (!ascending && num1 < num2) return false;
            } else {
                if (ascending && value1.compareTo(value2) > 0) return false;
                if (!ascending && value1.compareTo(value2) < 0) return false;
            }
        }
        return true;
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
        ProductValidation test = new ProductValidation();
        test.setup();
        test.sortingProductsValidation();
        test.CloseBrowser();
    }
}
