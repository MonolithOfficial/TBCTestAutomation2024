package ge.tbc.testautomation.crossBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {
    WebDriver driver;

    @BeforeTest
    @Parameters({"browserType", "isSomething"})
    public void setUp(String browserType, String isSomething){
        System.out.println(isSomething);
        if (browserType.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

    @Test
    public void crossBrowserTest() {
        driver.get("http://google.com");
    }
}
