package module;

import com.moex.mpfin.businessobjects.Product;
import com.moex.mpfin.businessobjects.user.BasicUser;
import com.moex.mpfin.utils.WebDriverSingleton;
import com.moex.mpfin.utils.EnvironmentProperties;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

import java.util.concurrent.TimeUnit;

public class BaseModuleTest {

  protected BasicUser user = new BasicUser();

  @BeforeGroups(groups = {"module"})
  public void beforeGroups(final ITestContext testContext) {
    WebDriverSingleton.getDriver().manage().window().setSize(new Dimension(1200, 800));
    WebDriverSingleton.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    WebDriverSingleton.getDriver().get(EnvironmentProperties.getEnvProps().getProperty("BASE_URL") + "/add-product/"
        + "?productId=" + Product.getProductId()
        + "&optionId=" + Product.getOptionId()
        + "&durationType=" + Product.getDurationType()
        + "&durationValue=" + Product.getDurationValue()
        + "&amount=" + Product.getAmountValue());
  }

  @AfterGroups(groups = {"module"})
  public void afterGroups() {
    WebDriverSingleton.removeDriver();
  }
}
