import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Recipes {
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
    public void loginandRecipes() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loginbtnclick = driver.findElement(By.cssSelector("#root > div > div.home-container > nav > div.navbar-links-container > a:nth-child(6) > button"));
        loginbtnclick.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("http://localhost:3000/login");
try {
    driver.findElement(By.id("email")).sendKeys("mkarunarathna872@gmail.com"); // Replace with valid credentials
    WebElement passwordField = driver.findElement(By.id("password"));
    passwordField.clear();
    passwordField.sendKeys("malsha123");
    driver.findElement(By.cssSelector("button.w-full.px-6.py-3.text-sm.font-medium.tracking-wide.text-white.capitalize.transition-colors.duration-300.transform.bg-gray-800.rounded-lg.hover\\:bg-gray-700.focus\\:outline-none.focus\\:ring.focus\\:ring-gray-300.focus\\:ring-opacity-50")).click();
    wait.until(ExpectedConditions.urlContains("/"));
    driver.findElement(By.cssSelector("a:nth-child(2)"));
    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
    driver.navigate().to("http://localhost:3000/recipes");
    WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
    searchBar.sendKeys("a"); // Enter search query
    WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(15));
    // Step 3: Wait for search results to update
    List<WebElement> recipeCards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > section:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2)")));

    // Step 4: Verify that the search results match the query
    boolean isSearchSuccessful = true;
    for (WebElement recipe : recipeCards) {
        String recipeTitle = recipe.getText().toLowerCase();
        if (!recipeTitle.contains("a")) { // Match the query to the recipe title
            isSearchSuccessful = false;
            break;
        }
    }

    if (isSearchSuccessful) {
        System.out.println("Search successful! All displayed recipes match the query.");
    } else {
        System.out.println("Search failed! Some displayed recipes do not match the query.");
    }

    // Step 5: Clean up by closing the browser
    driver.quit();

} catch (Exception e) {
    System.err.println("Test failed: " + e.getMessage());
    driver.quit();
}
    }
}





