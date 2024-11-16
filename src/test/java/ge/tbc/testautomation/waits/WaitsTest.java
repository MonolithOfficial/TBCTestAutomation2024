package ge.tbc.testautomation.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitsTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void implicitlyWaitTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement inputForm = driver.findElement(By.id("input-example"));
        WebElement enableButton = inputForm.findElement(By.tagName("button"));
        enableButton.click();
        WebElement enabledMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(enabledMessage.getText(), "It's enabled!");
    }

    @Test
    public void explicitWaitTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement inputForm = driver.findElement(By.id("input-example"));
        WebElement enableButton = inputForm.findElement(By.tagName("button"));
        enableButton.click();
        boolean isAppeared = wait.until(element -> {
            if (inputForm.isEnabled()){
                return true;
            }
            else {
                return false;
            }
        });
        Assert.assertTrue(inputForm.isEnabled());
//        WebElement enabledMessage = driver.findElement(By.id("message"));
//        Assert.assertEquals(enabledMessage.getText(), "It's enabled!");
    }
}
