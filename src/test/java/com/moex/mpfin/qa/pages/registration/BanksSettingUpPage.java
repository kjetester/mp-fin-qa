package com.moex.mpfin.qa.pages.registration;

import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BanksSettingUpPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Выберите банки для личного кабинета";

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[3]/div/div[1]/div/div/label[1]")
	private WebElement aggregateCheckbox;

	@FindBy(xpath = "//button")
	private WebElement submitButton;

	@Override
	public BanksSettingUpPage checkIfPageOpens() {
		waitForElementIsVisible(aggregateCheckbox);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void submitForm() {
		softly.assertThat(aggregateCheckbox.isSelected()).as("Checking all banks are selected...").isTrue();
		scrollTo(submitButton).click();
	}
}
