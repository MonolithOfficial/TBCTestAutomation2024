package ge.tbc.testautomation.navigation;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationTests {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("detach=true");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/slider/");
    }

    @Test
    public void test01() throws InterruptedException {
//        WebElement draggableLink = driver.findElement(By.xpath("//a[@href='https://jqueryui.com/draggable/']"));
//        draggableLink.click();
//        String pageTitle = driver.getTitle();
//        String pageSource = driver.getPageSource();
//        System.out.println(pageTitle);
//        System.out.println(pageSource);
//        driver.navigate().to("/resizable");
        WebElement sliderFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(sliderFrame);
        WebElement slider = driver.findElement(By.className("ui-slider-handle"));
        Actions actions = new Actions(driver);
        int x = 0;
        while (x < 10){
            actions.
                    clickAndHold(slider)
                    .moveByOffset(10, 0)
                    .release()
                    .perform();
            x++;
        }

        driver.switchTo().defaultContent();

        WebElement mobileLink = driver.findElement(By.xpath("//li[@class='project jquery-mobile']"));
        mobileLink.click();
        System.out.println(sliderFrame.getText());



    }

    @AfterClass
    public void tearDown(){
//        driver.quit();
    }
}
