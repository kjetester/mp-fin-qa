package com.moex.mpfin.qa.pages.registration;

import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EsiaAccessGrantingPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT
			= "Подтвердите на сайте Госуслуг своё согласие на передачу данных маркетплейсу Московской Биржи.";

	@FindBy(xpath = "//div[3]/button")
	private WebElement submitButton;

	@Override
	public EsiaAccessGrantingPage checkIfPageOpens() {
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void submitForm() {
		submitButton.click();
	}
}
