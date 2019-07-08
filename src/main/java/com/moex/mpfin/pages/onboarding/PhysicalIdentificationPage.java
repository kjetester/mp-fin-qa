package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

public class PhysicalIdentificationPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Идентифицируйтесь через Единую Биометрическую Систему";

	private Logger logger = LogManager.getLogger(this);

	@Override
	public PhysicalIdentificationPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		//TODO: change to explicit wait.
		new Actions(getDriver()).pause(4000).build().perform();
//		waitForElementIsVisible(skipButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	@FindBy(xpath = "//*[contains(text(), 'Пройти идентификацию')]/../../..")
	private WebElement submitButton;
}
