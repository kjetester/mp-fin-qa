package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends AbstractPage {

  private static final String PAGE_UNIQUE_TEXT = "Добро пожаловать на Маркетплейс Московской Биржи!";

  private Logger logger = LogManager.getLogger(this);

  @FindBy(xpath = "//input[@name = 'fio']")
  private WebElement fullNameInput;

  @FindBy(xpath = "//div/ul/li/span")
  private List<WebElement> suggestedNames;

  @FindBy(xpath = "//input[@name = 'hasPatronymic']/..")
  private WebElement hasPatronymicCheckbox;

  @FindBy(xpath = "//input[@name = 'Электронная почта']")
  private WebElement emailInput;

  @FindBy(xpath = "//input[@name = 'phone']")
  private WebElement phoneInput;

  @FindBy(xpath = "//form/div[3]//button")
  private WebElement sendOtpCodeButton;

  @FindBy(xpath = "//input[@name = 'codeInput']")
  private WebElement otpCodeInput;

  @FindBy(xpath = "//*[@width = '17']")
  private WebElement otpCodePassedSign;

  @FindBy(xpath = "//*[@name='isUserAgreementAccepted']/..")
  private WebElement agreementCheckbox;

  @FindBy(xpath = "//form/div[5]//button")
  private WebElement submitButton;

//  <a aria-controls="#login" href="#login" role="tab" class="tabs-component-tab-a">Вход</a>
  @FindBy(xpath = "//a[@href='#login']")
  private WebElement loginTab;

  @FindBy(xpath = "//section/div/div/p/button")
  private WebElement submitButtonOnLoginTab;

  @Override
  public RegistrationPage checkIfPageOpens() {
    logger.log(Level.INFO, "Checking if page opens.");
    waitForElementIsVisible(submitButton);
    super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
    return this;
  }

  /**
   * Steps to fill up and submit the form.
   * @param user user business object
   */
  public void fillAndSubmitForm(FlexibleUser user) {
    fillFullNameField(user);
    if (user.isNoPatronymic()) {
      chooseSuggestedName(user);
      hasPatronymicCheckbox.click();
    }
    fillEmailField(user);
    fillPhoneNumberField(user);
    clickSendCodeButton();
    fillOtpCodeInput();
    setAgreementCheckBox();
    clickSubmitButton();
  }

  /**
   * Filling The Full Name input.
   * @param user user business object
   */
  private void fillFullNameField(FlexibleUser user) {
    String fullName = user.isNoPatronymic()
        ? user.getLastName() + " "+ user.getFirstName()
        : user.getLastName() + " "+ user.getFirstName() + " " + user.getPatronymicName();
    logger.log(Level.INFO, String.format("Filling The Full Name input with '%s'", fullName));
    clickViaJavaScriptExecutor(fullNameInput).sendKeys(fullName);
  }

  /**
   * Searching among suggested names correct one and setting it.
   * @param user user business object
   */
  private void chooseSuggestedName(FlexibleUser user) {
    waitForElementToBeClickable(suggestedNames.get(0));
    logger.log(Level.INFO, String.format("Searching among suggested names correct one and setting it."));
    suggestedNames.stream().filter(w ->
      w.getText().equals(
          user.isNoPatronymic()
              ? user.getLastName() + " " + user.getFirstName()
              : user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymicName()
      )).findFirst().get().click();
  }

  /**
   * Filling The E-mail input.
   * @param user user business object
   */
  private void fillEmailField(FlexibleUser user) {
    logger.log(Level.INFO, String.format("Filling The E-mail input with '%s'", user.getEmailAddress()));
    clickViaJavaScriptExecutor(emailInput).sendKeys(user.getEmailAddress());
  }

  /**
   * Filling The Phone Number input.
   * @param user user business object
   */
  private void fillPhoneNumberField(FlexibleUser user) {
    logger.log(Level.INFO, String.format("Filling The Phone Number input with '%s'", user.getPhoneNumber()));
    clickViaJavaScriptExecutor(phoneInput).sendKeys(user.getPhoneNumber());
  }

  /**
   * Clicking The Send OTP Code button.
   */
  private void clickSendCodeButton() {
    logger.log(Level.INFO, "Clicking The Send OTP Code button.");
    waitForElementToBeClickable(sendOtpCodeButton);
    scrollTo(sendOtpCodeButton).click();
  }

  /**
   * Filling The OTP Code input.
   */
  private void fillOtpCodeInput() {
    logger.log(Level.INFO, "Filling The OTP Code input with '0000'.");
    waitForElementToBeClickable(otpCodeInput);
    clickViaJavaScriptExecutor(otpCodeInput).sendKeys("0000");
    waitForElementIsVisible(otpCodePassedSign);
  }

  /**
   * Setting The Agreement checkbox into true.
   */
  private void setAgreementCheckBox() {
    logger.log(Level.INFO, "Setting The Agreement checkbox into true.");
    scrollTo(agreementCheckbox).click();
  }

  /**
   * Clicking The Submit button.
   */
  private void clickSubmitButton() {
    logger.log(Level.INFO, "Clicking The Submit button.");
    waitForElementToBeClickable(submitButton);
    scrollTo(submitButton).click();
  }

  /**
   * Navigating to The Login Page.
   */
  public void goToLoginPage() {
    waitForElementToBeClickable(loginTab);
    loginTab.click();
  }
}
