package com.moex.mpfin.pages.deposit;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositContractPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Ваш договор с банком подписан!";

	private Logger logger = LogManager.getLogger(DepositContractPage.class.getSimpleName());

	@FindBy(xpath = "//pre")
	private WebElement contractTextBlock;

	@FindBy(xpath = "//*[contains(text(), 'Внести средства')]/../../..")
	private WebElement replenishButton;

	@Override
	public DepositContractPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(contractTextBlock);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void signContract() {
		scrollTo(replenishButton).click();
	}
}
