package extentreport;

import com.aventstack.extentreports.MediaEntityBuilder;
import driverfactory.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentLogger {
    private static void extentReportPass() {
    }

    public static void pass(String message){

        ExtentReportManager.getReport().pass(message);
    }

    public static void fail(String message){
        ExtentReportManager.getReport().fail(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(makePicture()).build());
    }
    public static void skip(String message){
        ExtentReportManager.getReport().skip(message);
    }
    public static void log(String message){
        ExtentReportManager.getReport().info(message);

    }



    private static String makePicture(){
        return  ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
