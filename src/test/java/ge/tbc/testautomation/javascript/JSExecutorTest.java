package ge.tbc.testautomation.javascript;

import ge.tbc.testautomation.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class JSExecutorTest extends BaseTest {

    @Test
    public void jsExecutorTest() throws InterruptedException {
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = (String) js
                .executeScript("return document.querySelector('.entry-title').innerText;");
        System.out.println(result);

        String location = (String) js
                .executeScript("return document.location");
        System.out.println(location);

        WebElement checkButton = driver.findElement(By.xpath("//input[@value='option-1']"));
        js.executeScript("arguments[0].checked = true", checkButton);

        List<WebElement> divs = driver.findElements(By.xpath("//div[@class='thumbnail']"));
        WebElement firstDiv = divs.get(0);
        String html = (String) js.executeScript("return arguments[0].innerHTML", firstDiv);
        System.out.println(html);

        js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

        WebElement table = driver.findElement(By.className("tsc_table_s13"));
        WebElement popTutorials = driver.findElement(By.xpath("//span[text()='Popular Tutorials']"));
        String value =
                (String) js.executeScript("arguments[1].scrollIntoView(); return arguments[0].innerText;", popTutorials, table);
        System.out.println(value);

    }
}
