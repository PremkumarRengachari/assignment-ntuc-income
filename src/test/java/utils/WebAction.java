package utils;


import org.openqa.selenium.*;

import java.util.List;
import java.util.Optional;

public class WebAction extends CommonUtil{
    private WebDriver driver;

    public WebAction() {
        driver = BrowserManager.getWebDriver(browser);
    }

    public String getTitle() {
       return driver.getTitle();
    }

    public boolean exists(String locator) {
        WebElement element = getElement(locator);
        boolean present = Optional.of(element).
                filter(WebElement::isDisplayed).isPresent();
        if (!present) {
            throw new RuntimeException("Element not exists");
        }
        return present;
    }

    public void click(String locator) {
        WebElement element = getElement(locator);
        element.click();
    }

    public void javascriptClick(String locator) {
        WebElement element = getElement(locator);
        ((JavascriptExecutor)driver).executeScript("element[0].click();",element);
    }

    public void enter(String locator,String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }


    public void sendKey(String locator, Keys key) {
        WebElement element = getElement(locator);
        element.sendKeys(key);
    }

    public String getText(String locator) {
        String text = "";
        WebElement element = getElement(locator);
        text = element.getText();
        return text;
    }

    public String getTextByAttribute(String locator,String attr) {
        String text = "";
        WebElement element = getElement(locator);
        text = element.getAttribute(attr);
        return text;
    }

    public WebElement getElement(String locator) {
        By by = getLocator(locator);
        WebElement element = driver.findElement(by);
        return element;
    }

    public List<WebElement> getElements(String locator) {
        By by = getLocator(locator);
        return driver.findElements(by);
    }


    private By getLocator(String locatorName) {
        if(!locatorName.contains("==")) {
           throw new RuntimeException("Locator does not contains seperator = ");
        }
        String[] split = locatorName.split("==");
        String locatorType = split[0];
        String locator = split[1];
        By by;
        switch (locatorType.toLowerCase()) {
            case "xpath" : by = By.xpath(locator);
            return by;
            case "id" : by = By.id(locator);
            return by;
            case "name" : by = By.name(locator);
            return by;
            case "classname" : by = By.className(locator);
            return by;
            default:
                throw new RuntimeException("No locator type matched");
        }
    }
}
