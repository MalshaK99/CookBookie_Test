import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Contact {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void contact() {
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

        // Re-locate the contact button after login
        WebElement contactbtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a:nth-child(5)")));
        contactbtn.click();

        // Wait for the 'contact' page to load
        wait.until(ExpectedConditions.urlContains("contact"));

        WebElement type=driver.findElement(By.xpath("//input[@placeholder='Enter your question']"));
        type.sendKeys("This is a test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='secondary-button']"));
    }
}
