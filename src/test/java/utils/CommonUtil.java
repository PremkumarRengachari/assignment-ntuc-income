package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public abstract class CommonUtil {
   //API properties
    public final String api_base_url = "https://api.github.com/users/";
    public final String user_name = "torvalds";
    public final String repo_sub_url = "/repos";
    public final String repo_name = "linux";

    //Web app properties
    public final String browser = "chrome";
    public final String web_url = "https://github.com/";
    public final long wait = 20;
    public final String propertyPath = "\\src\\test\\resources\\properties\\";
    public final String propExtension = ".properties";
    public final String screenshotsPath = "screenshots\\";
    public final String reportsPath = "reports\\";
    public final String imageType = ".png";
    ExtentHtmlReporter htmlReporter;
    ExtentReports  extentReports;
    public ExtentReports getReport (String reportname) {
        htmlReporter = new ExtentHtmlReporter(reportsPath+"/report_"+reportname+"__"+System.nanoTime()+".html");
        htmlReporter.config().setReportName(reportname);
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }

    public void endReport(ExtentReports extentReports) {
        extentReports.flush();
        extentReports = null;
        htmlReporter = null;
    }

    public String convertToUnits(String input) {
        if(StringUtils.isEmpty(input)) {
            return "0";
        }
        int number = Integer.parseInt(input);
        String numberString = "";
        if (Math.abs(number / 1000000) > 1) {
            numberString = (number / 1000000) + "m";
        } else if (Math.abs(number / 1000) > 1) {
            numberString = (number / 1000) + "k";
        } else {
            numberString = String.valueOf(number);
        }
        return numberString;
    }

    public String takeScreenshot() throws IOException {
        WebDriver driver = BrowserManager.driver;
        TakesScreenshot ts=(TakesScreenshot) driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        String path=System.getProperty("user.dir")+screenshotsPath+System.currentTimeMillis()+imageType;
        File destination=new File(path);
        try
        {
            FileUtils.copyFile(src, destination);
        } catch (IOException e)
        {
            System.out.println("Capture Failed "+e.getMessage());
        }
        return path;
    }


}
