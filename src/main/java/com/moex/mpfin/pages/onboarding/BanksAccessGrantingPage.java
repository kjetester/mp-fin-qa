package com.moex.mpfin.pages.onboarding;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BanksAccessGrantingPage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Выберите банки для личного кабинета";

	private Logger logger = LogManager.getLogger(BanksAccessGrantingPage.class.getSimpleName());

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[3]/div/div[1]/div/div/label[1]")
	private WebElement aggregateCheckbox;

	@FindBy(css = "div[class $= 'ps ps--active-y'] label")
	WebElement label;

	@FindBy(xpath = "//button")
	private WebElement submitButton;

	@Override
	public BanksAccessGrantingPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementToBeClickable(label);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	/**
	 * Checking if all of the linked banks are selected.
	 * @return this
	 */
	public BanksAccessGrantingPage checkIfAllBanksAreSelected() {
		logger.info("Starting checking if all of the linked banks are selected.");
		String[] s = StringUtils.substringBetween(
				aggregateCheckbox.findElement(By.xpath("./../label[2]")).getText(), "(", ")").split("/");
		int selectedCount = Integer.valueOf(s[0]);
		int totalCount = Integer.valueOf(s[1]);
		if (totalCount == 0)
			logger.log(Level.ERROR, String.format("Total quantity of the linked banks is %s", totalCount));
		else
			logger.log(Level.ERROR, String.format("Total quantity of the linked banks is %s. %s are selected.", totalCount,  selectedCount));
		//TODO Assertions.assertThat(selectedCount).as("Comparing selected vs. total count.").isEqualTo(totalCount);
		if (selectedCount != totalCount) aggregateCheckbox.click();
		return this;
	}

	/**
	 * Submitting the form.
	 */
	public void submitForm() {
		scrollTo(submitButton).click();
	}
}
