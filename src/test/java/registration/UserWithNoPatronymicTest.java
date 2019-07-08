package registration;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.businessobjects.user.NoPatronymicUser;
import com.moex.mpfin.pages.common.HeaderPage;
import com.moex.mpfin.pages.onboarding.*;
import com.moex.mpfin.pages.profile.ProfilePage;
import com.moex.mpfin.pages.signin.SignInPage;
import com.moex.mpfin.utils.CamundaWorker;
import com.moex.mpfin.utils.IdpWorker;
import com.moex.mpfin.utils.PageGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class UserWithNoPatronymicTest extends BaseRegistrationTest {

	private Logger logger = LogManager.getLogger(this);
	private PageGenerator generator = new PageGenerator();
	private FlexibleUser noPatronymicUser = new NoPatronymicUser(user);


	@Test(testName = "User with no patronymic onboarding test", groups = {"registration"})
	public void noPatronymicRegistrationTest() {
		generator.getInstance(SignInPage.class).checkIfPageOpens().goToRegistrationPage();
		generator.getInstance(RegistrationPage.class).fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(EsiaAccessGrantingPage.class).checkIfPageOpens().submitForm();
		generator.getInstance(EsiaLogInPage.class).checkIfPageOpens().fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(QuestionnairePage.class).checkIfPageOpens().verifyStaticFields(user).fillAndSubmitForm(noPatronymicUser);
		generator.getInstance(BanksAccessGrantingPage.class).checkIfPageOpens().checkIfAllBanksAreSelected().submitForm();
		generator.getInstance(PhysicalIdentificationPage.class).checkIfPageOpens();
		camunda.setBusinessKey().skipEbsProcessAndSetUserId(noPatronymicUser);
		idp.checkUserFullName(noPatronymicUser);
		generator.getInstance(HeaderPage.class).goToProfile();
		generator.getInstance(ProfilePage.class).checkIfPageOpens().verifyStaticFields(noPatronymicUser);
	}
}
