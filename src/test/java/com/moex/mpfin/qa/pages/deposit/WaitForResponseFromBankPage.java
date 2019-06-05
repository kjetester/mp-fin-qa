package com.moex.mpfin.qa.pages.deposit;

import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WaitForResponseFromBankPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Ваша заявка обрабатывается банком.";

	@FindBy(xpath = "//*[@stroke='currentColor']")
	private WebElement spinner;

	@FindBy(css = "button:last-child")
	private WebElement goToDashboardButton;

	@Override
	public WaitForResponseFromBankPage checkIfPageOpens() {
		waitForElementIsVisible(spinner);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void goToDashboard() {
		scrollTo(goToDashboardButton).click();
	}
}
