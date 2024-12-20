import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Login {
    WebDriverWait wait; // Declare WebDriverWait instance
    WebDriver driver;

    @BeforeMethod
    public void OpenLink() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Initialize WebDriverWait here
    }

    @Test
    public void loginTest() throws InterruptedException {
        // Test Case 2: Empty Field Validation
        driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']")).click();
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        System.out.println("Error Message: " + toastMessage.getText());

        // Test Case 3: Invalid Email Format
        driver.findElement(By.id("email")).sendKeys("invalid-email");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']")).click();
        toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        System.out.println("Error Message: " + toastMessage.getText());

        // Clear fields for next test
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        // Test Case 4: Successful Login
        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("mkarunarathna872@gmail.com"); // Replace with valid credentials
        WebElement passwordField=driver.findElement(By.id("password"));
        passwordField.clear();

        passwordField.sendKeys("malsha123");

// Click the login button
        driver.findElement(By.cssSelector("button.w-full.px-6.py-3.text-sm.font-medium.tracking-wide.text-white.capitalize.transition-colors.duration-300.transform.bg-gray-800.rounded-lg.hover\\:bg-gray-700.focus\\:outline-none.focus\\:ring.focus\\:ring-gray-300.focus\\:ring-opacity-50")).click();

// Wait for redirection to the homepage
        wait.until(ExpectedConditions.urlContains("/")); // Replace "/" with your home page path

// Verify if the URL matches the expected homepage URL
        if (driver.getCurrentUrl().equals("http://localhost:3000/")) { // Replace with your expected URL
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        // Close the browser
    }
}
