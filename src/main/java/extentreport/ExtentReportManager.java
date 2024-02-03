package extentreport;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager extends ThreadLocal{
    private static ThreadLocal<ExtentTest> report = new ThreadLocal<>();

    protected  static ExtentTest getReport() {
        return report.get();
    }

    protected static void setTest(ExtentTest test){
        report.set(test);
    }

    public static void removeTest(){
        report.remove();
    }
}
