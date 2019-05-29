package com.moex.mpfin.qa.tests;

import com.moex.mpfin.qa.businessobjects.Contract;
import com.moex.mpfin.qa.pages.dashboard.DashboardPage;
import com.moex.mpfin.qa.pages.cart.CartPage;
import com.moex.mpfin.qa.pages.common.HeaderPage;
import com.moex.mpfin.qa.pages.depositopening.DepositConditionsPage;
import com.moex.mpfin.qa.pages.depositopening.WaitForResponseFromBankPage;
import com.moex.mpfin.qa.pages.profile.ProfilePage;
import com.moex.mpfin.qa.pages.registration.BanksSettingUpPage;
import com.moex.mpfin.qa.pages.registration.EsiaAccessGrantingPage;
import com.moex.mpfin.qa.pages.registration.EsiaLogInPage;
import com.moex.mpfin.qa.pages.registration.IdentificationPage;
import com.moex.mpfin.qa.pages.registration.QuestionnairePage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import com.moex.mpfin.qa.utils.PageGenerator;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.moex.mpfin.qa.utils.CamundaApi.grabAndSetUserId;

@Listeners(TestListenerAdapter.class)
public class SmokeTest extends BaseTest {

  private PageGenerator pageGen = new PageGenerator();
  private HeaderPage header = pageGen.getInstance(HeaderPage.class);

  @Test(testName = "Registration", groups = {"e2e"})
  public void registrationTest() {
    pageGen.getInstance(RegistrationPage.class)
        .checkIfPageOpens()
        .fillAndSubmitForm();
    pageGen.getInstance(EsiaAccessGrantingPage.class)
        .checkIfPageOpens()
        .submitForm();
    pageGen.getInstance(EsiaLogInPage.class)
        .checkIfPageOpens()
        .fillAndSubmitForm();
    pageGen.getInstance(QuestionnairePage.class)
        .checkIfPageOpens()
        .verifyStaticFields()
        .fillAndSubmitForm();
    pageGen.getInstance(BanksSettingUpPage.class)
        .checkIfPageOpens()
        .submitForm();
    pageGen.getInstance(IdentificationPage.class)
        .checkIfPageOpens().skipProcess();
    pageGen.getInstance(DepositConditionsPage.class)
        .checkIfPageOpens();
    header
        .goToProfile();
    pageGen.getInstance(ProfilePage.class)
        .checkIfPageOpens()
        .verifyStaticFields();
  }

  @Test(
      testName = "DepositOpening",
      groups = {"e2e"},
      dependsOnMethods = "registrationTest")
  public void depositOpeningTest() {
    header
        .goToCart();
    pageGen.getInstance(CartPage.class)
        .checkIfPageOpens()
        .verifyAmountOfProducesIsEqualTo(1)
        .makeApplicationForDeposit();
    pageGen.getInstance(DepositConditionsPage.class)
        .checkPreconditions()
        //TODO: MPFIN-1593
//        .setAmount(9999.99).setDuration(365)
        .sendContractToBank();
    pageGen.getInstance(WaitForResponseFromBankPage.class)
        .checkIfPageOpens()
        .goToDashboard();
    pageGen.getInstance(DashboardPage.class)
        .checkIfPageOpens().checkContractStatus(Contract.Status.WAITING_FOR_FUNDS);
  }
}
