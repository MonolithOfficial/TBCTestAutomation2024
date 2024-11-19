package ge.tbc.testautomation.tables;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TableTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
    }

    // DEMONSTRATION OF HTML TABLE DYNAMIC HANDLING
    @Test
    public void dynamicTableTest(){
        WebElement table = driver.findElement(By.tagName("table"));

        String desiredColumnText = "Lorem";
        String searchByColumnText = "Diceret";
        String searchBYValueText = "Phaedrum3";

        // finding index of the second column
        List<WebElement> tableHeaders = table.findElements(By.xpath("//th"));
        WebElement desiredColumElement = tableHeaders.stream().filter(tableHeader -> tableHeader.getText().equals(desiredColumnText))
                .findFirst().orElse(null);
        Integer desiredColumnIndex = tableHeaders.indexOf(desiredColumElement);
        System.out.println(desiredColumnIndex);

        // finding index of the second column
        WebElement findByColumElement = tableHeaders.stream().filter(tableHeader -> tableHeader.getText().equals(searchByColumnText))
                .findFirst().orElse(null);
        Integer findByColumnIndex = tableHeaders.indexOf(findByColumElement);
        System.out.println(findByColumnIndex);

        // finding index of the row with specified value on second column
        List<WebElement> cellsOfSearchByColumn = table.findElements(By.xpath(String.format("//tr//td[%d]", findByColumnIndex + 1)));
        cellsOfSearchByColumn.forEach(element -> System.out.println(element.getText()));
        WebElement searchByValueElement = cellsOfSearchByColumn.stream().filter(cell -> cell.getText().equals(searchBYValueText))
                .findFirst().orElse(null);

        Integer searchByValueIndex = cellsOfSearchByColumn.indexOf(searchByValueElement);
        System.out.println(searchByValueIndex);

        WebElement desiredValueElement = table.findElement(By.xpath(String.format("//tr[%d]//td[%d]", searchByValueIndex + 1, desiredColumnIndex + 1)));
        System.out.println(desiredValueElement.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
