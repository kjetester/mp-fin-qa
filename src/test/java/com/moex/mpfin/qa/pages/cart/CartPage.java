package com.moex.mpfin.qa.pages.cart;

import com.moex.mpfin.qa.pages.AbstractPage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

public class CartPage extends AbstractPage {

	@FindBy(xpath = "//h1[contains(., 'Корзина')]")
	private WebElement title;

	@FindBy(css = "div[class*='card ']")
	private List<WebElement> productBanners;

	@Override
	public CartPage checkIfPageOpens() {
		waitForElementIsVisible(title);
		Assertions.assertThat(getDriver().getCurrentUrl()).as("Checking current URL.")
				.contains("/cart");
		return this;
	}

	public CartPage verifyAmountOfProducesIsEqualTo(int i) {
		Assertions.assertThat(productBanners.size()).as("Checking amount of products in the Cart.")
				.isEqualByComparingTo(i);
		return this;
	}

	public void makeApplicationForDeposit() {
		WebElement buttonOnProductBanner = getDriver().findElement(By.cssSelector(" >* button"));
		Assertions.assertThat(buttonOnProductBanner.isDisplayed()).as("Checking existence of the button").isTrue();
		Assertions.assertThat(buttonOnProductBanner.findElement(By.xpath("//span")).getText())
				.as("Checking the button name").isEqualTo("ОФОРМИТЬ ЗАЯВКУ");
		scrollTo(buttonOnProductBanner).click();
	}
}
