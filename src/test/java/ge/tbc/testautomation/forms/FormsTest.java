package ge.tbc.testautomation.forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class FormsTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        driver.get("https://techcanvass.com/examples/register.html");
    }

    @Test
    public void textFieldTest() {
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@value='First Name']"));
        firstNameInput.sendKeys("Manuchari");
    }

    @Test
    public void radioButtonTest() {
        WebElement femaleRadioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='female']"));
        if (!femaleRadioButton.isSelected()){
            femaleRadioButton.click();
        }
    }

    @Test
    public void checkBoxTest() {
        WebElement deliveryCheckbox = driver.findElement(By.xpath("//input[@type='checkbox' and @name='DeliveryLoc']"));
        if (!deliveryCheckbox.isSelected()){
            deliveryCheckbox.click();
        }
    }

    @Test
    public void nativeSelecttest() {
        Select modelSelect = new Select(driver.findElement(By.name("model")));
        modelSelect.selectByValue("Ser32");
//        modelSelect.
//        modelSelect.selectByVisibleText("Mega 123 Large screen");
//        modelSelect.selectByIndex(2);
//        if (!deliveryCheckbox.isSelected()){
//            deliveryCheckbox.click();
//        }
    }

    @Test
    public void customDropDowntest() {
        driver.get("https://ng-bootstrap.github.io/#/components/dropdown/examples");
        WebElement dropDownButton = driver.findElement(By.id("dropdownBasic1"));
        dropDownButton.click();
        List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class,'dropdown-menu') and @aria-labelledby='dropdownBasic1']/button"));

        for (WebElement el :
                options) {
            System.out.println(el.getText());
        }
        WebElement somethingElseOpt = options.stream()
                .filter(webElement -> webElement.getText().equalsIgnoreCase("Something else is here"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Such option could not be detected"));
        somethingElseOpt.click();
    }

//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }
}
