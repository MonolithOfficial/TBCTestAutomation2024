package ge.tbc.testautomation.frames;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class FrameTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void frameTest() {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-middle");
        WebElement content = driver.findElement(By.id("content"));
        System.out.println(content.getText());
    }

    @Test
    public void alertTest() {
        driver.get("https://demoqa.com/alerts");
        WebElement timerAlert = driver.findElement(By.id("timerAlertButton"));
        timerAlert.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
    }

    @Test
    public void windowTest(){
        driver.get("https://demoqa.com/browser-windows");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");

        String currentWindow = driver.getWindowHandle();
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();
        Set<String> allTabs = driver.getWindowHandles();

        for(String id : allTabs){
            if (!currentWindow.equals(id)){
                driver.switchTo().window(id);
                WebElement heading = driver.findElement(By.id("sampleHeading"));
                System.out.println(heading.getText());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
