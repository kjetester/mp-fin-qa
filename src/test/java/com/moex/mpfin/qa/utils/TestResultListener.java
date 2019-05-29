package com.moex.mpfin.qa.utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.moex.mpfin.qa.reporting.ReportTools.takeScreenshot;

public class TestResultListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				takeScreenshot();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
