package utils;

import org.openqa.selenium.WebDriver;

public class BrowserManager extends CommonUtil{
    public static WebDriver driver;


    public static WebDriver getWebDriver(String browser) {
        if(null == driver) {
            driver = new DriverUtil().getDriver(browser);
        }
        return driver;
    }

    public static void quitDriver() {
        new DriverUtil().quitDriver(driver);
        driver=null;
    }
}
