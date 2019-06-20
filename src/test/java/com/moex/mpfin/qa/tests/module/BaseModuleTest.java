package com.moex.mpfin.qa.tests.module;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;
import static com.moex.mpfin.qa.utils.DriverFactory.removeDriver;
import static com.moex.mpfin.qa.utils.EnvironmentProperties.getEnvProps;

import com.moex.mpfin.qa.businessobjects.Product;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

public class BaseModuleTest {
  @BeforeGroups(groups = {"module"})
  public void beforeGroups(final ITestContext testContext) {
    getDriver().manage().window().setSize(new Dimension(1200, 800));
    getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    getDriver().get(getEnvProps().getProperty("BASE_URL") + "/add-product/"
        + "?productId=" + Product.getProductId()
        + "&optionId=" + Product.getOptionId()
        + "&durationType=" + Product.getDurationType()
        + "&durationValue=" + Product.getDurationValue()
        + "&amount=" + Product.getAmountValue());
  }

  @AfterGroups(groups = {"module"})
  public void afterGroups() {
    removeDriver();
  }
}
