package com.moex.mpfin.pages.deposit;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositCardPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Поздравляем, Вы открыли вклад!";

	private Logger logger = LogManager.getLogger(this);

	@FindBy(xpath = "//*[@role = 'tablist']")
	private WebElement tabList;

	@Override
	public DepositCardPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(tabList);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}
}
