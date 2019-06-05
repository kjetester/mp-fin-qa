package com.moex.mpfin.qa.tests;

import com.moex.mpfin.qa.businessobjects.Product;
import com.moex.mpfin.qa.businessobjects.User;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;
import static com.moex.mpfin.qa.utils.DriverFactory.removeDriver;
import static com.moex.mpfin.qa.utils.EnvironmentProperties.getEnvProps;

public class BaseTest {

	@BeforeGroups(groups = { "e2e" })
	public void beforeGroups(final ITestContext testContext) {
		RestAssured.get("https://sso.beta.moex.com/auth/realms/marketplace/userService/users/"
				+ User.getEmailAddress() + "/delete");
		getDriver().manage().window().setSize(new Dimension(1200,800));
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		getDriver().get(getEnvProps().getProperty("BASE_URL") + "/add-product/"
				+ "?productId=" + Product.getProductId()
				+ "&optionId=" + Product.getOptionId()
				+ "&durationType=" + Product.getDurationType()
				+ "&durationValue=" + Product.getDurationValue()
				+ "&amount=" + Product.getAmountValue());
	}

	@AfterGroups(groups = { "e2e" })
	public void afterGroups() {
		removeDriver();
	}
}
