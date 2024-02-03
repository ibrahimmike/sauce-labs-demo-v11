package listeners;

import extentreport.ExtentLogger;
import extentreport.ExtentReport;
import frameworkconstants.Constants;
import frameworkproperties.ReadPropertyFiles;
import org.testng.*;

public class ListenersClass implements ITestListener, ISuiteListener{


    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
        ExtentReport.extentInit();
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
        ExtentReport.closeTest();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        result.getTestClass().getName();
        ExtentReport.createTest(result.getTestClass().getName()+ " : " +result.getMethod().getMethodName() + " "
                + ReadPropertyFiles.getPropertyValue(Constants.BROWSER));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);

        // System.out.println(result.getMethod().getMethodName());
        ExtentLogger.pass(result.getMethod().getMethodName() + " has passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        ExtentLogger.fail(result.getMethod().getMethodName() + " test thrown error:  " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        ExtentLogger.skip(result.getName() + " test was skipped ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {

        ITestListener.super.onStart(context);

    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);

    }
}
