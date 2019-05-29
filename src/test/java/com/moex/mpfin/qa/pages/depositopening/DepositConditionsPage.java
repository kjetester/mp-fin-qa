package com.moex.mpfin.qa.pages.depositopening;

import com.moex.mpfin.qa.businessobjects.Product;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.moex.mpfin.qa.utils.CamundaApi.grabAndSetContractId;

public class DepositConditionsPage extends AbstractPage {

	//TODO: Define unique text
	private static final String PAGE_UNIQUE_TEXT = "";

	@FindBy(name = "amount")
	private WebElement amountInput;

	@FindBy(name = "duration")
	private WebElement durationInput;

	@FindBy(xpath = "//*[@name='consentContract']/..")
	private WebElement agreementCheckbox;

	@FindBy(css = "section > div:last-child > button")
	private WebElement submitButton;

	@Override
	public DepositConditionsPage checkIfPageOpens() {
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		grabAndSetContractId();
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
