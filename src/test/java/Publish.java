import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Publish {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void publishRecipe() {
        try {
            // Step 1: Login
            System.out.println("Attempting to log in...");
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#root > div > div.home-container > nav > div.navbar-links-container > a:nth-child(6) > button")));
            loginBtn.click();

            wait.until(ExpectedConditions.urlContains("login"));
            System.out.println("On login page.");

            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            emailField.sendKeys("mkarunarathna872@gmail.com");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys("malsha123");

            WebElement loginSubmitBtn = driver.findElement(By.cssSelector("button.w-full.px-6.py-3.text-sm.font-medium.tracking-wide.text-white.capitalize.transition-colors.duration-300.transform.bg-gray-800.rounded-lg.hover\\:bg-gray-700.focus\\:outline-none.focus\\:ring.focus\\:ring-gray-300.focus\\:ring-opacity-50"));
            loginSubmitBtn.click();

            // Wait for successful login
            wait.until(ExpectedConditions.urlContains("/"));
            System.out.println("Login successful. Current URL: " + driver.getCurrentUrl());

            // Step 2: Navigate to the publish page
            System.out.println("Navigating to the Publish page...");
            WebElement publishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/publish']"))); // Update the selector as needed
            publishBtn.click();

            wait.until(ExpectedConditions.urlContains("/publish"));
            System.out.println("Publish page loaded.");

            // Step 3: Fill out the publish form
            WebElement recipeNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recipeName")));
            recipeNameField.sendKeys("Delicious Cake");

            WebElement recipeDescriptionField = driver.findElement(By.id("description"));
            recipeDescriptionField.sendKeys("This is a test description for a delicious cake.");

            WebElement recipeImageUpload = driver.findElement(By.id("image"));
            recipeImageUpload.sendKeys("C:\\Users\\User\\Downloads\\cake.webp"); // Update with a valid path

            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
            submitButton.click();

            // Wait for confirmation
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
            System.out.println("Recipe published successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser

        }
    }
}
