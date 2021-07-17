package steps;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.BrowserManager;
import utils.CommonUtil;

import java.util.concurrent.TimeUnit;

public class Hooks extends CommonUtil {

    WebDriver driver;
    @BeforeTest
    public void setUpBrowser() {
        driver = BrowserManager.getWebDriver(browser);
        driver.get(web_url);
        driver.manage()
                .timeouts()
                .implicitlyWait(wait, TimeUnit.SECONDS);

    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

}
