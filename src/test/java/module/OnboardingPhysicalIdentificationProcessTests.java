package module;

import com.moex.mpfin.pages.onboarding.EsiaLogInPage;
import com.moex.mpfin.pages.onboarding.RegistrationPage;
import com.moex.mpfin.utils.CamundaWorker;
import com.moex.mpfin.utils.PageGenerator;
import com.moex.mpfin.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class OnboardingPhysicalIdentificationProcessTests extends BaseModuleTest {
  private PageGenerator pageGen = new PageGenerator();
  private static CamundaWorker camunda = new CamundaWorker();

  @Test(testName = "Onboarding Physical Identification Tests", groups = {"module"})
  public void registrationTest() {
    pageGen.getInstance(RegistrationPage.class)
        .checkIfPageOpens()
        .switchingOnLoginFormAndSubmitForm();
    pageGen.getInstance(EsiaLogInPage.class)
        .fillAndSubmitForm(user);
    camunda.setBusinessKey().jumpToPhysicalIdentification();
  }
}
