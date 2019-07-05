package com.moex.mpfin.pages.signin;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Уже регистрировались у нас? Войдите с помощью";

	private Logger logger = LogManager.getLogger(SignInPage.class.getSimpleName());

	@FindBy(xpath = "//section[@id = 'login']//button")
	private WebElement submitButton;

	@FindBy(css = "a[href *= 'registration']")
	private WebElement registrationTab;

	@Override
	public SignInPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	/**
	 * Going to registration tab.
	 */
	public void goToRegistrationPage() {
		logger.log(Level.INFO, "Navigating to the Registration Page.");
		waitForElementToBeClickable(registrationTab).click();
	}
}
