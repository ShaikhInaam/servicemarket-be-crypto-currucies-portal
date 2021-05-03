package com.crypto.portal.cryptoportal;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;

public class ExtentReport {

    public static void main(String[] args) {

        ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
//        ExtentXReporter extentx = new ExtentXReporter("localhost");

        ExtentReports extent = new ExtentReports();

        extent.attachReporter(html);

        ExtentTest test1 =    extent.createTest("TestName");
        ExtentTest test2 =    extent.createTest("Failed");

        test1.pass("Success");
        test1.log(Status.INFO,"Starting test 1");

        test2.fail("Failed");
        test2.log(Status.FAIL,"Failed");


        extent.flush();
    }
}
