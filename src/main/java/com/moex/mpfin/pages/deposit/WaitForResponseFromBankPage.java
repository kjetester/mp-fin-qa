package com.moex.mpfin.pages.deposit;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WaitForResponseFromBankPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Ваша заявка обрабатывается банком.";

	private Logger logger = LogManager.getLogger(this);

	@FindBy(xpath = "//*[@stroke='currentColor']")
	private WebElement spinner;

	@FindBy(css = "button:last-child")
	private WebElement goToDashboardButton;

	@Override
	public WaitForResponseFromBankPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(spinner);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void goToDashboard() {
		scrollTo(goToDashboardButton).click();
	}
}
