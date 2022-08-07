package com.spotify.outh2.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class TestNGListener implements ITestListener {

    public void onStart(ITestResult result)
    {
        System.out.println("Start Test Execution .......... "+result.getName());
    }

    public void onFinish(ITestResult result)
    {
        System.out.println("Finish Test Execution  .........."+result.getName());
    }

    public void onTestStart(ITestResult result)
    {
        System.out.println("Start Test by .......... "+result.getName());
    }

    public void onTestSkipped(ITestResult result)
    {
        System.out.println("Skipped Test .......... "+result.getName());
    }

    public void onTestFailure(ITestResult result)
    {
        System.out.println("Test case failed and details are ......... "+result.getName() );
    }

    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Pass Test case Success ............ " +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
      //  System.out.println("Start Test .......... "+result.getName());
    }




}
