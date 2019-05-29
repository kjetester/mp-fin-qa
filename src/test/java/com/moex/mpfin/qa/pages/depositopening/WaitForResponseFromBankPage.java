package com.moex.mpfin.qa.pages.depositopening;

import com.moex.mpfin.qa.pages.AbstractPage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WaitForResponseFromBankPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Ваша заявка обрабатывается банком.";

	@FindBy(css = "button:last-child")
	private WebElement goToDashboardButton;

	@Override
	public WaitForResponseFromBankPage checkIfPageOpens() {
		waitForElementIsVisible(goToDashboardButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void goToDashboard() {
		scrollTo(goToDashboardButton).click();
	}
}
