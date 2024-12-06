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

public class Home {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/"); // Starting URL
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void contact() throws InterruptedException {
        // Wait and click on the login button
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".primary-button")));
        loginBtn.click();

        // Wait for the login form and enter email and password
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailField.sendKeys("mkarunarathna872@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("malsha123");

        // Wait for and click the login submit button
        WebElement loginSubmitBtn = driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']"));
        loginSubmitBtn.click();

        // Wait until the URL changes to the home page after login
        wait.until(ExpectedConditions.urlContains("/"));
        System.out.println("Login successful. Current URL: " + driver.getCurrentUrl());

        // Wait and click the secondary button (e.g., Submit)
        WebElement sbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".secondary-button")));
        sbtn.click();

        // Wait for the next page to load and then navigate back
        Thread.sleep(2000); // Add a brief wait before navigating back
        driver.navigate().back(); // This will navigate back to the previous page (home)


        WebElement shareBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='home-container'] span:nth-child(2)")));

        // Scroll to the share button using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", shareBtn);
        shareBtn.click();
        Thread.sleep(2000);

        WebElement review=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Reviews']")));
        review.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement name=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='name']")));
        name.sendKeys("Test User Name");
        WebElement reviewadd=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@id='review']")));
        reviewadd.sendKeys("Test User Review");
        WebElement rating=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='grid grid-cols-1 gap-6']//div//div[@class='flex items-center mt-2']//*[name()='svg'][3]/*[name()='path'][1]")));
        rating.click();
        WebElement btn=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
        btn.click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement lgout=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='primary-button']")));
        lgout.click();
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
