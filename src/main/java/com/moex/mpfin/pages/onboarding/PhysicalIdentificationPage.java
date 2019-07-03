package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhysicalIdentificationPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Идентифицируйтесь через Единую Биометрическую Систему";

	@Override
	public PhysicalIdentificationPage checkIfPageOpens() {
		//TODO: change to explicit wait.
		actions.pause(4000).build().perform();
//		waitForElementIsVisible(skipButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	@FindBy(xpath = "//*[contains(text(), 'Пройти идентификацию')]/../../..")
	private WebElement submitButton;

}
