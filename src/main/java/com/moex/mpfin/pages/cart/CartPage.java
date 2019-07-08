package com.moex.mpfin.pages.cart;

import com.moex.mpfin.businessobjects.Contract;
import com.moex.mpfin.pages.AbstractPage;
import com.moex.mpfin.utils.WebDriverSingleton;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

	private static final String BUTTON_ON_PRODUCT_BANNER_SELECTOR = " * button";
	private static final String BUTTON_ON_PRODUCT_BANNER_NAME_SELECTOR = "//button//span";

	private Logger logger = LogManager.getLogger(this);

	@FindBy(xpath = "//h1[contains(., 'Корзина')]")
	private WebElement title;

	@FindBy(css = "div[class*='card ']")
	private List<WebElement> productBanners;

	@Override
	public CartPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(title);
		Assertions.assertThat(WebDriverSingleton.getDriver().getCurrentUrl()).as("Checking current URL.")
				.contains("/cart");
		return this;
	}

	public CartPage verifyAmountOfProducesIsEqualTo(int i) {
		Assertions.assertThat(productBanners.size()).as("Checking amount of products in the Cart.")
				.isEqualByComparingTo(i);
		return this;
	}

	public void startActionForDeposit() {
		scrollTo(productBanners.get(0).findElement(By.cssSelector(BUTTON_ON_PRODUCT_BANNER_SELECTOR))).click();
	}

	public CartPage verifyContractStatusIsEqualTo(Contract.Status status) {
		switch (status) {
			case NEW:
				Assertions.assertThat(
						productBanners.get(0).findElement(By.xpath(BUTTON_ON_PRODUCT_BANNER_NAME_SELECTOR)).getText())
						.as("Checking the button name")
						.isEqualTo(Contract.Action.MAKE_APPLICATION.toString());
				break;
			case WAITING_FOR_BANK_APPROVAL:
				Assertions.assertThat(productBanners.get(0).findElement(By.xpath("//div[4]//div[2]"))
						.getText().replaceAll("\"", ""))
						.as("Checking the status name")
						.isEqualTo(status.toString());
				break;
			case WAITING_FOR_FUNDS:
				Assertions.assertThat(
						productBanners.get(0).findElement(By.xpath(BUTTON_ON_PRODUCT_BANNER_NAME_SELECTOR)).getText())
						.as("Checking the button name")
						.isEqualTo(Contract.Action.START_INITIAL_REPLENISHMENT.toString());
				break;
		}
		return this;
	}
}
