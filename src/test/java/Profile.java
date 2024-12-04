import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Profile {
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
    public void updateProfile() {
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

            // Step 2: Navigate to the profile page
            System.out.println("Navigating to profile page...");
            WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/profile']")));
            profileLink.click();

            wait.until(ExpectedConditions.urlContains("/profile"));
            System.out.println("Profile page loaded.");

            // Step 3: Upload profile picture
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            fileInput.sendKeys("C:\\Users\\User\\Downloads\\Mal.png");
            System.out.println("Profile picture uploaded.");

            // Step 4: Update user details
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fname")));
            nameField.clear();
            nameField.sendKeys("Malsha");

            WebElement emailUpdateField = driver.findElement(By.id("email"));
            emailUpdateField.clear();
            emailUpdateField.sendKeys("mkarunarathna@gmail.com");

            WebElement phoneField = driver.findElement(By.id("phone"));
            phoneField.clear();
            phoneField.sendKeys("+7(123)123");

            WebElement updateBtn = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(2) > div:nth-child(3) > button:nth-child(1)"));

            updateBtn.click();
            System.out.println("User details updated.");

            // Step 5: Update password
            WebElement currentPasswordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("currentPassword")));
            currentPasswordField.sendKeys("malsha123");

            WebElement newPasswordField = driver.findElement(By.id("newPassword"));
            newPasswordField.sendKeys("newPassword123");

            WebElement saveButton = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > button:nth-child(1)"));

            saveButton.click();
            System.out.println("Password updated.");

            // Final confirmation
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".success-message"), "Profile updated successfully"));
            System.out.println("Profile updated successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
