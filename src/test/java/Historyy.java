import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Historyy {
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
    public void historyy_test() {
        try {
            // Step 1: Log in
            System.out.println("Attempting to log in...");
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".primary-button")));
            loginBtn.click();

            // Wait for login form to appear
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            emailField.sendKeys("mkarunarathna872@gmail.com");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys("malsha123");

            WebElement loginSubmitBtn = driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']"));
            loginSubmitBtn.click();

            // Wait for login to complete
            wait.until(ExpectedConditions.urlContains("/"));
            System.out.println("Login successful. Current URL: " + driver.getCurrentUrl());

            // Step 2: Navigate to Profile page (re-locate the profile button after login)
            System.out.println("Navigating to Profile page...");
            WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/profile']")));
            profileBtn.click();

            // Wait for Profile page to load
            wait.until(ExpectedConditions.urlContains("profile"));
            System.out.println("Profile page loaded successfully.");

            // Step 3: Navigate to History page (re-locate the history button after loading Profile page)
            System.out.println("Navigating to History page...");
            WebElement historyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='History']")));
            historyBtn.click();

            // Wait for History page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='bg-white rounded-lg p-5 shadow-md']")));
            System.out.println("History page loaded successfully. Current URL: " + driver.getCurrentUrl());

            // After the page loads, re-locate elements as the previous references might have gone stale
            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[4]//div[2]//button[1]")));
            updateBtn.click();

            // Re-locate the recipe name field after the page update
            WebElement recipeName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='recipe-name']")));
            recipeName.clear();
            recipeName.sendKeys("Birthday Cake");

            // Re-locate and click the save button after the page update
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            saveBtn.click();

            // Wait for the success message to appear and be visible
            WebElement delBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[4]//div[2]//button[2]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delBtn);
            wait.until(ExpectedConditions.elementToBeClickable(delBtn)).click();

            WebElement yesbtn=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@label='Yes']")));
            yesbtn.click();




        } catch (StaleElementReferenceException e) {
            System.err.println("Stale Element Reference Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
