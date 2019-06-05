package com.moex.mpfin.qa.tests;

import com.moex.mpfin.qa.businessobjects.Contract;
import com.moex.mpfin.qa.pages.cart.CartPage;
import com.moex.mpfin.qa.pages.common.HeaderPage;
import com.moex.mpfin.qa.pages.dashboard.DashboardPage;
import com.moex.mpfin.qa.pages.deposit.*;
import com.moex.mpfin.qa.pages.profile.ProfilePage;
import com.moex.mpfin.qa.pages.registration.BanksSettingUpPage;
import com.moex.mpfin.qa.pages.registration.EsiaAccessGrantingPage;
import com.moex.mpfin.qa.pages.registration.EsiaLogInPage;
import com.moex.mpfin.qa.pages.registration.IdentificationPage;
import com.moex.mpfin.qa.pages.registration.QuestionnairePage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import com.moex.mpfin.qa.utils.CamundaWorker;
import com.moex.mpfin.qa.utils.PageGenerator;
import com.moex.mpfin.qa.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

@Listeners({TestListener.class})
public class SmokeTest extends BaseTest {

  private PageGenerator pageGen = new PageGenerator();
  private HeaderPage header = pageGen.getInstance(HeaderPage.class);
  private CartPage cart = pageGen.getInstance(CartPage.class);
  private DepositContractPage contract = pageGen.getInstance(DepositContractPage.class);
  private DepositInitialReplenishmentPage initialReplenishment = pageGen.getInstance(DepositInitialReplenishmentPage.class);
  private DepositCardPage depositCard = pageGen.getInstance(DepositCardPage.class);
  private DashboardPage dashboard = pageGen.getInstance(DashboardPage.class);
  private WaitForResponseFromBankPage waitForResponseFromBank = pageGen.getInstance(WaitForResponseFromBankPage.class);
  private static CamundaWorker camunda = new CamundaWorker();

  @Test(testName = "Registration Test", groups = { "e2e" })
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
        .checkIfPageOpens();
    camunda.setBusinessKey().skipEbsProcessAndSetUserId();
    pageGen.getInstance(DepositConditionsPage.class)
        .checkIfPageOpens();
    header
        .goToProfile();
    pageGen.getInstance(ProfilePage.class)
        .checkIfPageOpens()
        .verifyStaticFields();
  }

  @Test(testName = "Deposit Opening Test", groups = { "e2e" }, dependsOnMethods = "registrationTest")
  public void depositOpeningTest() {
    header
        .goToCart();
    cart
        .checkIfPageOpens()
        .verifyAmountOfProducesIsEqualTo(1)
        .verifyContractStatusIsEqualTo(Contract.Status.NEW)
        .startActionForDeposit();
    pageGen.getInstance(DepositConditionsPage.class)
        .checkPreconditions()
//TODO: MPFIN-1593        .setAmount(9999.99).setDuration(365)
        .grabPercentage()
        .sendContractToBank();
    waitForResponseFromBank
        .checkIfPageOpens();
    camunda.setBusinessKey();
    header
        .goToCart();
    cart
        .checkIfPageOpens()
        .verifyContractStatusIsEqualTo(Contract.Status.WAITING_FOR_BANK_APPROVAL);
    camunda.skipAccountOpeningAndSetContractId();
    getDriver().navigate().refresh();
    cart
        .checkIfPageOpens()
        .verifyContractStatusIsEqualTo(Contract.Status.WAITING_FOR_FUNDS);
  }

  @Test(testName = "Contract Signing Test", groups = { "e2e" },
      dependsOnMethods = {"registrationTest", "depositOpeningTest"})
  public void contractSigningTest() {
    cart
        .startActionForDeposit();
    contract
        .checkIfPageOpens()
        .signContract();
    initialReplenishment
        .checkIfPageOpens();
  }

  @Test(testName = "Deposit Initial Replenishment Test", groups = { "e2e" },
      dependsOnMethods = {"registrationTest", "contractSigningTest", "depositOpeningTest"} )
  public void depositInitialReplenishmentTest() {
    initialReplenishment
        .replenishDeposit();
    camunda.
        skipAccountDeposit();
    depositCard
        .checkIfPageOpens();
    header
        .goToDashboard();
    dashboard
        .checkIfPageOpens()
        .verifyActiveDeposit();
  }
}
