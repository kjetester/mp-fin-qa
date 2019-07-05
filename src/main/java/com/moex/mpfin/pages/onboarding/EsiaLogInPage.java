package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EsiaLogInPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "идентификации и аутентификации";

	private Logger logger = LogManager.getLogger(EsiaLogInPage.class.getSimpleName());

	@FindBy(id = "mobileOrEmail")
	private WebElement loginInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "loginByPwdButton")
	private WebElement submitButton;

	@Override
	public EsiaLogInPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void fillAndSubmitForm(FlexibleUser user) {
		waitForElementToBeClickable(loginInput).sendKeys(user.getEsiaPhoneNumber());
		waitForElementToBeClickable(passwordInput).sendKeys(user.getEsiaPassword());
		waitForElementToBeClickable(submitButton).click();
	}
}
