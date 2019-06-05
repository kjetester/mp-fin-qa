package com.moex.mpfin.qa.pages.registration;

import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IdentificationPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Идентифицируйтесь через Единую Биометрическую Систему";

	@Override
	public IdentificationPage checkIfPageOpens() {
		//TODO: change to explicit wait.
		actions.pause(4000).build().perform();
//		waitForElementIsVisible(skipButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	@FindBy(xpath = "//*[contains(text(), 'Пройти идентификацию')]/../../..")
	private WebElement submitButton;

}
