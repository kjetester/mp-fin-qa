package registration;

import com.moex.mpfin.businessobjects.user.BasicUser;
import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.utils.CamundaWorker;
import com.moex.mpfin.utils.EnvironmentProperties;
import com.moex.mpfin.utils.IdpWorker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static com.moex.mpfin.utils.WebDriverSingleton.removeDriver;

public class BaseRegistrationTest {

	private Logger logger = LogManager.getLogger(this);
	protected CamundaWorker camunda = new CamundaWorker();
	protected IdpWorker idp = new IdpWorker();
	protected FlexibleUser user = new BasicUser();

	@BeforeClass(groups = {"registration"})
	@Parameters({"window-width","window-height"})
	public void beforeMethod(int width, int height) {
		idp.deleteUser(user);
		logger.log(Level.DEBUG, String.format("Setting window's width to %s and height to %s", width, height));
		getDriver().manage().window().setSize(new Dimension(width, height));
		String targetUrl = EnvironmentProperties.getEnvProps().getProperty("BASE_URL");
		logger.log(Level.INFO, String.format("Navigating to ' %s '.", targetUrl));
		getDriver().get(targetUrl);
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		getDriver().close();
		removeDriver();
	}
}
