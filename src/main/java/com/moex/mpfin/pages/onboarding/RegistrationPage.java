package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends AbstractPage {

  private static final String PAGE_UNIQUE_TEXT = "Добро пожаловать на Маркетплейс Московской Биржи!";

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
    waitForElementIsVisible(submitButton);
    super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
    return this;
  }

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

  private void chooseSuggestedName(FlexibleUser user) {
    waitForElementToBeClickable(suggestedNames.get(0));
    suggestedNames.stream().filter(w ->
      w.getText().equals(
          user.isNoPatronymic() ? user.getLastName() + " " + user.getFirstName()
          : (user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymicName())
      )).findFirst().get().click();
  }

  private void clickSubmitButton() {
    waitForElementToBeClickable(submitButton);
    scrollTo(submitButton).click();
  }

  private void setAgreementCheckBox() {
    scrollTo(agreementCheckbox).click();
  }

  private void fillOtpCodeInput() {
    waitForElementToBeClickable(otpCodeInput);
    clickViaJavaScriptExecutor(otpCodeInput).sendKeys("0000");
    waitForElementIsVisible(otpCodePassedSign);
  }

  private void clickSendCodeButton() {
    waitForElementToBeClickable(sendOtpCodeButton);
    scrollTo(sendOtpCodeButton).click();
  }

  private void fillPhoneNumberField(FlexibleUser user) {
    clickViaJavaScriptExecutor(phoneInput).sendKeys(user.getPhoneNumber());
  }

  private void fillEmailField(FlexibleUser user) {
    fillEmailField(emailInput, user.getEmailAddress());
  }

  private void fillEmailField(WebElement emailInput, String emailAddress) {
    clickViaJavaScriptExecutor(emailInput).sendKeys(emailAddress);
  }

  private void fillFullNameField(FlexibleUser user) {
    clickViaJavaScriptExecutor(fullNameInput).sendKeys(
        user.isNoPatronymic()
            ? user.getLastName() + " "+ user.getFirstName() :
            user.getLastName() + " "+ user.getFirstName() + " " + user.getPatronymicName());
  }

  public void switchingOnLoginFormAndSubmitForm() {
    waitForElementToBeClickable(loginTab);
    loginTab.click();
    waitForElementToBeClickable(submitButtonOnLoginTab);
    submitButtonOnLoginTab.click();
  }
}
