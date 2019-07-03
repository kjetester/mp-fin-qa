import com.moex.mpfin.businessobjects.user.BasicUser;
import io.restassured.RestAssured;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static com.moex.mpfin.utils.WebDriverSingleton.removeDriver;

public class BaseTest {

	private Logger logger = LogManager.getLogger();

	protected BasicUser user = new BasicUser();

	@BeforeSuite (alwaysRun = true)
	public void beforeSuite() {
		logger.log(Level.TRACE, "Configuration File Defined To Be :: " + System.getProperty("log4j2.properties"));
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({"window-width","window-height", "implicitly-wait"})
	public void beforeClass(int width, int height, int implicitlyWait) {
		logger.log(Level.INFO, String.format("Deleting user with E-mail: '%s'.", user.getEmailAddress()));
		RestAssured.get("https://sso.beta.moex.com/auth/realms/marketplace/userService/users/"
				+ user.getEmailAddress() + "/delete");
		logger.log(Level.DEBUG, String.format("Setting window's width to %s and height to %s", width, height));
		getDriver().manage().window().setSize(new Dimension(width, height));
		logger.log(Level.DEBUG, String.format("Setting implicitlyWait to %s %s", implicitlyWait, TimeUnit.SECONDS));
		getDriver().manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		removeDriver();
	}
}
