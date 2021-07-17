package utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverUtil {

    WebDriver driver;
    public  void quitDriver(WebDriver driver) {
        if(driver!=null) {
            driver.quit();
        }
    }

    public  WebDriver getDriver (String browserName) {
        Preconditions.checkArgument(!browserName.isEmpty(),
                "Browser name should not be empty or null");
        switch (browserName.toLowerCase()) {
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
                chromeOptions.addArguments("--disable-infobars");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
                firefoxOptions.addArguments("--disable-infobars");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default: throw new RuntimeException("No browser name mentioned");
        }
        return driver;
    }



}
