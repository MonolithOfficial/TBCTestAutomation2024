package ge.tbc.testautomation.cookies;

import ge.tbc.testautomation.BaseTest;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Set;

public class CookieTest extends BaseTest {
    @Test
    public void testName() {
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        Cookie customCookie = new Cookie("isAutomated", "true", "/");
        driver.manage().addCookie(customCookie);

        Set<Cookie> cookieSet = driver.manage().getCookies();
        for (Cookie singleCookie :
                cookieSet) {
            System.out.println(singleCookie.getName() + " " + singleCookie.getExpiry());
        }

        Cookie someKindOfCookie = driver.manage().getCookieNamed("isAutomated");
        Assert.assertNotNull(someKindOfCookie);

        driver.manage().deleteCookie(someKindOfCookie);
        Assert.assertNotNull(driver.manage().getCookieNamed("isAutomated"));
    }
}
