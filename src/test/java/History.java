import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class History {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Explicit wait
    }

    @Test
    public void testHistoryPage() {
        try {
            // Step 1: Log in
            System.out.println("Attempting to log in...");
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".primary-button")));
            loginBtn.click(); // Wait for login page to load
            wait.until(ExpectedConditions.urlContains("login"));
            System.out.println("On login page.");

            // Enter credentials
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            emailField.sendKeys("mkarunarathna872@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys("malsha123");

            // Submit login
            WebElement loginSubmitBtn = driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']"));
            loginSubmitBtn.click();
            wait.until(ExpectedConditions.urlContains("/")); // Navigate to profile page
            System.out.println("Login successful. Current URL: " + driver.getCurrentUrl());

            // Re-locate and click on the profile button after the page reloads
            WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Profile']")));
            profileBtn.click();

            // Wait for the logout button to be available and re-locate it
            WebElement lgoutbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='flex flex-col items-start justify-center flex-1 w-full gap-0 overflow-hidden text-sm font-medium truncate']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lgoutbtn);
            lgoutbtn.click();

            System.out.println("Logout successful.");
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
