import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Signup {
    WebDriverWait wait;
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    @Test
    public void signup() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loginbtnclick=driver.findElement(By.cssSelector("#root > div > div.home-container > nav > div.navbar-links-container > a:nth-child(6) > button"));
        loginbtnclick.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("http://localhost:3000/login"); // Replace with your signup page URL
try{
        WebElement signuplink=driver.findElement(By.cssSelector("#root > div > div.flex.items-center.justify-center.min-h-screen.bg-gray-100.dark\\:bg-gray-900 > div.flex.w-full.max-w-sm.mx-auto.overflow-hidden.bg-white.rounded-lg.shadow-lg.dark\\:bg-gray-800.lg\\:max-w-4xl > div.w-full.px-6.py-8.md\\:px-8.lg\\:w-1\\/2 > div:nth-child(4) > a"));
        signuplink.click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("http://localhost:3000/signup");
        Thread.sleep(5000);

        //enter
        driver.findElement(By.id("fname")).sendKeys("Test User");
        driver.findElement(By.id("phone")).sendKeys("0123456789");
        driver.findElement(By.id("email")).sendKeys("mkarunarathna872@gmail.com");
        driver.findElement(By.id("password")).sendKeys("password123");

        WebElement submitBtn=driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']"));
        submitBtn.click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String messageText = errorMessage.getText();
        if (messageText.contains("duplicate key")) {
            driver.navigate().to("http://localhost:3000/login");
        } else {
            System.out.println("Signup successful!");
        }
    } catch (Exception e) {
        System.err.println("Test failed: " + e.getMessage());
    }

    }
    @Test
    public void signupNew() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loginbtnclick=driver.findElement(By.cssSelector("#root > div > div.home-container > nav > div.navbar-links-container > a:nth-child(6) > button"));
        loginbtnclick.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("http://localhost:3000/login"); // Replace with your signup page URL
        try{
            WebElement signuplink=driver.findElement(By.cssSelector("#root > div > div.flex.items-center.justify-center.min-h-screen.bg-gray-100.dark\\:bg-gray-900 > div.flex.w-full.max-w-sm.mx-auto.overflow-hidden.bg-white.rounded-lg.shadow-lg.dark\\:bg-gray-800.lg\\:max-w-4xl > div.w-full.px-6.py-8.md\\:px-8.lg\\:w-1\\/2 > div:nth-child(4) > a"));
            signuplink.click();
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.navigate().to("http://localhost:3000/signup");
            Thread.sleep(5000);

            //enter
            driver.findElement(By.id("fname")).sendKeys("Test User");
            driver.findElement(By.id("phone")).sendKeys("0123456789");
            driver.findElement(By.id("email")).sendKeys("newuser@gmail.com");
            driver.findElement(By.id("password")).sendKeys("password123");

            WebElement submitBtn=driver.findElement(By.cssSelector("button[class='w-full px-6 py-3 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50']"));
            submitBtn.click();
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
            String messageText = errorMessage.getText();
            if (messageText.contains("Email already in use")) {

                driver.navigate().to("http://localhost:3000/login");
                System.out.println("Error: " + messageText);
            } else {
                System.out.println("Signup successful!");
                driver.navigate().to("http://localhost:3000/login");
            }
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
        }

    }
}
