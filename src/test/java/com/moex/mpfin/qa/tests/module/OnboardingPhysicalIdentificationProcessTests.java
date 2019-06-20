package com.moex.mpfin.qa.tests.module;

import com.moex.mpfin.qa.pages.registration.EsiaLogInPage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import com.moex.mpfin.qa.utils.CamundaWorker;
import com.moex.mpfin.qa.utils.PageGenerator;
import com.moex.mpfin.qa.utils.TestListener;
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
        .fillAndSubmitForm();
    camunda.setBusinessKey().jumpToPhysicalIdentification();
  }
}
