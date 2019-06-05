package com.moex.mpfin.qa.pages.common;

import com.moex.mpfin.qa.pages.AbstractPage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends AbstractPage {

	@Override
	public HeaderPage checkIfPageOpens() {
		return this;
	}

	@FindBy(css = "a[href*='dashboard']")
	private WebElement dashboardLink;

	@FindBy(css = "a[href*='cart']")
	private WebElement cartLink;

	@FindBy(css = "a[href*='profile']")
	private WebElement profileLink;

	public void goToProfile() {
		scrollTo(profileLink).click();
	}

	public void goToCart() {
		scrollTo(cartLink).click();
	}

	public void goToDashboard() {
		scrollTo(dashboardLink).click();
	}
}
