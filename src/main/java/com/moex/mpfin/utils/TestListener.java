package com.moex.mpfin.utils;

import com.moex.mpfin.reporting.ReportTools;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				ReportTools.takeScreenshot();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
