import com.moex.mpfin.businessobjects.user.BasicUser;
import com.moex.mpfin.businessobjects.user.NoPatronymicUser;
import com.moex.mpfin.pages.common.HeaderPage;
import com.moex.mpfin.pages.profile.ProfilePage;
import com.moex.mpfin.pages.onboarding.EsiaAccessGrantingPage;
import com.moex.mpfin.pages.onboarding.EsiaLogInPage;
import com.moex.mpfin.pages.onboarding.QuestionnairePage;
import com.moex.mpfin.pages.onboarding.RegistrationPage;
import com.moex.mpfin.pages.signin.SignInPage;
import com.moex.mpfin.utils.EnvironmentProperties;
import com.moex.mpfin.utils.PageGenerator;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

public class RegistrationTest extends BaseTest {

	private PageGenerator generator = new PageGenerator();
	private NoPatronymicUser noPatronymicUser = new NoPatronymicUser(user);

	@BeforeClass(groups = { "registration" })
	public void beforeClass(final ITestContext testContext) {
		RestAssured.get("https://sso.beta.moex.com/auth/realms/marketplace/userService/users/"
				+ noPatronymicUser.getEmailAddress() + "/delete");
		getDriver().manage().window().setSize(new Dimension(1200,800));
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		getDriver().get(EnvironmentProperties.getEnvProps().getProperty("BASE_URL"));
	}

	@Test(testName = "User with no patronymic onboarding test", groups = {"registration"})
	public void noPatronymicRegistrationTest() {
		generator.getInstance(SignInPage.class).checkIfPageOpens().goToRegistrationPage();
		generator.getInstance(RegistrationPage.class).fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(EsiaAccessGrantingPage.class).checkIfPageOpens().submitForm();
		generator.getInstance(EsiaLogInPage.class).checkIfPageOpens().fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(QuestionnairePage.class).checkIfPageOpens()
				.verifyStaticFields(noPatronymicUser).fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(HeaderPage.class).goToProfile();
		generator.getInstance(ProfilePage.class).checkIfPageOpens().verifyStaticFields(noPatronymicUser);
	}
}
