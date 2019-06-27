package com.moex.mpfin.qa.pages.deposit;

import com.moex.mpfin.qa.businessobjects.Product;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositConditionsPage extends AbstractPage {

	//TODO: Define unique text
	private static final String PAGE_UNIQUE_TEXT
			= "Открытие вклада \"" + Product.getProductName() + "\" | Банк \"" + Product.getProviderName() + "\"";

	@FindBy(xpath = "//input[@name='amount']")
	private WebElement amountInput;

	@FindBy(xpath = "//input[@name='duration']")
	private WebElement durationInput;

	@FindBy(xpath = "//*[contains(text(), 'Ставка')]/../div[2]")
	private WebElement percentage;

	@FindBy(xpath = "//*[@name='consentContract']/..")
	private WebElement agreementCheckbox;

	@FindBy(css = "section > div:last-child > button")
	private WebElement submitButton;

	@Override
	public DepositConditionsPage checkIfPageOpens() {
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public DepositConditionsPage setAmount(double amount) {
		clickViaJavaScriptExecutor(amountInput).sendKeys(String.valueOf(amount));
		return this;
	}

	public DepositConditionsPage setDuration(int duration) {
		clickViaJavaScriptExecutor(durationInput).sendKeys(String.valueOf(duration));
		return this;
	}

	public DepositConditionsPage grabPercentage() {
		Product.setPercentage(percentage.getText().replaceAll("%", ""));
		return this;
	}

	public void sendContractToBank() {
		scrollTo(agreementCheckbox).click();
		waitForElementToBeClickable(submitButton);
		scrollTo(submitButton).click();
	}

	public DepositConditionsPage checkPreconditions() {
		Assertions.assertThat(scrollTo(amountInput).getAttribute("value").replace(" ", ""))
				.as("Checking preconditions at amount...").isEqualTo(Product.getAmountValue());
		Assertions.assertThat(scrollTo(durationInput).getAttribute("value").replaceAll("[^\\d.]", ""))
				.as("Checking preconditions at duration...").isEqualTo(Product.getDurationValue());
		return this;
	}
}
