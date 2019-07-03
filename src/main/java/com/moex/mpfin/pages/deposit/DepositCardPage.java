package com.moex.mpfin.pages.deposit;

import com.moex.mpfin.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositCardPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Поздравляем, Вы открыли вклад!";

	@FindBy(xpath = "//*[@role = 'tablist']")
	private WebElement tabList;

	@Override
	public DepositCardPage checkIfPageOpens() {
		waitForElementIsVisible(tabList);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}
}
