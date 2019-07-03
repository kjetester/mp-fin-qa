import com.moex.mpfin.businessobjects.Contract;
import com.moex.mpfin.businessobjects.Product;
import com.moex.mpfin.pages.cart.CartPage;
import com.moex.mpfin.pages.common.HeaderPage;
import com.moex.mpfin.pages.dashboard.DashboardPage;
import com.moex.mpfin.pages.deposit.*;
import com.moex.mpfin.pages.profile.ProfilePage;
import com.moex.mpfin.pages.onboarding.*;
import com.moex.mpfin.utils.CamundaWorker;
import com.moex.mpfin.utils.PageGenerator;
import com.moex.mpfin.utils.TestListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.moex.mpfin.utils.EnvironmentProperties.getEnvProps;
import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

@Listeners({TestListener.class})
public class SmokeTest extends BaseTest {

  private Logger logger = LogManager.getLogger();
  private PageGenerator pageGen = new PageGenerator();
  private HeaderPage header = pageGen.getInstance(HeaderPage.class);
  private CartPage cart = pageGen.getInstance(CartPage.class);
  private DepositContractPage contract = pageGen.getInstance(DepositContractPage.class);
  private DepositInitialReplenishmentPage initialReplenishment = pageGen.getInstance(DepositInitialReplenishmentPage.class);
  private DepositCardPage depositCard = pageGen.getInstance(DepositCardPage.class);
  private DashboardPage dashboard = pageGen.getInstance(DashboardPage.class);
  private WaitForResponseFromBankPage waitForResponseFromBank = pageGen.getInstance(WaitForResponseFromBankPage.class);
  private static CamundaWorker camunda = new CamundaWorker();

  @BeforeClass(groups = {"e2e"})
  public void beforeClass(final ITestContext testContext) {
    String targetUrl = getEnvProps().getProperty("BASE_URL") + "/add-product/"
        + "?productId=" + Product.getProductId()
        + "&optionId=" + Product.getOptionId()
        + "&durationType=" + Product.getDurationType()
        + "&durationValue=" + Product.getDurationValue()
        + "&amount=" + Product.getAmountValue();
    logger.log(Level.INFO, String.format("Navigating to ' %s '.", targetUrl));
    getDriver().get(targetUrl);
  }

  @Test(testName = "Registration Test", groups = { "e2e" })
  public void registrationSmokeTest() {
    pageGen.getInstance(RegistrationPage.class).checkIfPageOpens().fillAndSubmitForm(user);
    pageGen.getInstance(EsiaAccessGrantingPage.class).checkIfPageOpens().submitForm();
    pageGen.getInstance(EsiaLogInPage.class).checkIfPageOpens().fillAndSubmitForm(user);
    pageGen.getInstance(QuestionnairePage.class).checkIfPageOpens().verifyStaticFields(user).fillAndSubmitForm(user);
    pageGen.getInstance(BanksAccessGrantingPage.class).checkIfPageOpens().checkIfAllBanksAreSelected().submitForm();
    pageGen.getInstance(PhysicalIdentificationPage.class).checkIfPageOpens();
    camunda.setBusinessKey().skipEbsProcessAndSetUserId(user);
    pageGen.getInstance(DepositConditionsPage.class).checkIfPageOpens();
    header.goToProfile();
    pageGen.getInstance(ProfilePage.class).checkIfPageOpens().verifyStaticFields(user);
  }

  @Test(testName = "Deposit Opening Test", groups = { "e2e" }, dependsOnMethods = "registrationSmokeTest")
  public void depositOpeningSmokeTest() {
    header.goToCart();
    cart.checkIfPageOpens().verifyAmountOfProducesIsEqualTo(1)
        .verifyContractStatusIsEqualTo(Contract.Status.NEW).startActionForDeposit();
    pageGen.getInstance(DepositConditionsPage.class).checkPreconditions()
//TODO: MPFIN-1593        .setAmount(9999.99).setDuration(365)
        .grabPercentage().sendContractToBank();
    waitForResponseFromBank.checkIfPageOpens();
    camunda.setBusinessKey();
    header.goToCart();
    cart.checkIfPageOpens().verifyContractStatusIsEqualTo(Contract.Status.WAITING_FOR_BANK_APPROVAL);
    camunda.skipAccountOpeningAndSetContractId(user);
    getDriver().navigate().refresh();
    cart.checkIfPageOpens().verifyContractStatusIsEqualTo(Contract.Status.WAITING_FOR_FUNDS);
  }

  @Test(testName = "Contract Signing Test", groups = { "e2e" },
      dependsOnMethods = {"registrationSmokeTest", "depositOpeningSmokeTest"})
  public void contractSigningSmokeTest() {
    cart.startActionForDeposit();
    contract.checkIfPageOpens().signContract();
    initialReplenishment.checkIfPageOpens();
  }

  @Test(testName = "Deposit Initial Replenishment Test", groups = { "e2e" },
      dependsOnMethods = {"registrationSmokeTest", "contractSigningSmokeTest", "depositOpeningSmokeTest"})
  public void initialReplenishmentSmokeTest() {
//    initialReplenishment.initialReplenishDeposit();
    camunda.skipAccountDeposit(user);
    depositCard.checkIfPageOpens();
    header.goToDashboard();
    dashboard.checkIfPageOpens().verifyActiveDeposit();
  }
}
