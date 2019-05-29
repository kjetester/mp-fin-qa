package com.moex.mpfin.qa.pages.registration;

import com.moex.mpfin.qa.businessobjects.User;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends AbstractPage {

  private static final String PAGE_UNIQUE_TEXT = "Добро пожаловать на Маркетплейс Московской Биржи!";

  @FindBy(xpath = "//input[@name = 'fio']")
  private WebElement fullNameInput;

  @FindBy(xpath = "//input[@name = 'электронная почта']")
  private WebElement emailInput;

  @FindBy(xpath = "//input[@name = 'phone']")
  private WebElement phoneInput;

  @FindBy(xpath = "//form/div[3]//button")
  private WebElement sendOtpCodeButton;

  @FindBy(xpath = "//input[@name = 'codeInput']")
  private WebElement otpCodeInput;

  @FindBy(xpath = "//*[@width = '17']")
  private WebElement otpCodePassedSign;

  @FindBy(xpath = "//*[@id='registration']//*[@name='isUserAgreementAccepted']/..")
  private WebElement agreementCheckbox;

  @FindBy(xpath = "//form/div[5]//button")
  private WebElement submitButton;

  @Override
  public RegistrationPage checkIfPageOpens() {
    waitForElementIsVisible(submitButton);
    super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
    return this;
  }

  public void fillAndSubmitForm() {
    clickViaJavaScriptExecutor(fullNameInput).sendKeys(User.getLastName() + " "
        + User.getFirstName() + " "
        + User.getPatronymicName());
    clickViaJavaScriptExecutor(emailInput).sendKeys(User.getEmailAddress());
    clickViaJavaScriptExecutor(phoneInput).sendKeys(User.getPhoneNumber());
    waitForElementToBeClickable(sendOtpCodeButton);
    scrollTo(sendOtpCodeButton).click();
    waitForElementToBeClickable(otpCodeInput);
    clickViaJavaScriptExecutor(otpCodeInput).sendKeys("0000");
    waitForElementIsVisible(otpCodePassedSign);
    scrollTo(agreementCheckbox).click();
    waitForElementToBeClickable(submitButton);
    scrollTo(submitButton).click();
  }
}
