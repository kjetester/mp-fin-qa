package com.moex.mpfin.qa.tests;

import com.moex.mpfin.qa.businessobjects.Product;
import com.moex.mpfin.qa.businessobjects.User;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;

import java.util.concurrent.TimeUnit;

import static com.moex.mpfin.qa.reporting.ReportTools.takeScreenshot;
import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;
import static com.moex.mpfin.qa.utils.DriverFactory.removeDriver;

public class BaseTest {

	public static final String NEW_DEV_IP = "http://10.50.12.20";
	public static final String NEW_DEV_CAMUNDA = "31257";
	private static final String NEW_DEV_UI = "32421";

	@BeforeGroups(groups = { "e2e" })
	public void beforeGroups(final ITestContext testContext) {
		RestAssured.get("https://sso.beta.moex.com/auth/realms/marketplace/userService/users/"
				+ User.getEmailAddress() + "/delete");
		getDriver().manage().window().setSize(new Dimension(1200,800));
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//		getDriver().get(NEW_DEV_IP + ":" + NEW_DEV_UI_PORT + "/add-product/"
		getDriver().get(NEW_DEV_IP + ":" + NEW_DEV_UI + "/add-product/"
				+ "?productId=" + Product.getProductId()
				+ "&optionId=" + Product.getOptionId()
				+ "&durationType=" + Product.getDurationType()
				+ "&durationValue=" + Product.getDurationValue()
				+ "&amount=" + Product.getAmountValue());
	}

	@AfterGroups(groups = { "e2e" })
	public void afterGroups() {
		removeDriver();
		RestAssured.get("https://sso.beta.moex.com/auth/realms/marketplace/userService/users/"
				+ User.getEmailAddress() + "/delete");
	}
}
