package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EsiaAccessGrantingPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT
			= "Подтвердите на сайте Госуслуг своё согласие на передачу данных маркетплейсу Московской Биржи.";

	private Logger logger = LogManager.getLogger(EsiaAccessGrantingPage.class.getSimpleName());

	@FindBy(xpath = "//div[3]/button")
	private WebElement submitButton;

	@Override
	public EsiaAccessGrantingPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void submitForm() {
		submitButton.click();
	}
}
