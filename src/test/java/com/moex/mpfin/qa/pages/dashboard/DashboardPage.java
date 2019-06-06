package com.moex.mpfin.qa.pages.dashboard;

import com.moex.mpfin.qa.businessobjects.Product;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends AbstractPage {

	private static final String DASHBOARD_URL = "/dashboard";

	@FindBy(xpath = "//div[contains(text(), 'Мои вклады')]/..")
	private WebElement myDepositsBlock;

	@FindBy(xpath = "//table//tbody//tr")
	private List<WebElement> activeDeposits;

	@FindBy(css = "div[class*='card ']")
	private List<WebElement> cartProductBanners;

	@Override
	public DashboardPage checkIfPageOpens() {
		waitForElementIsVisible(myDepositsBlock);
		super.checkIfPageOpens(DASHBOARD_URL);
		return this;
	}

	public void verifyActiveDeposit() {
		Assertions.assertThat(activeDeposits.size()).as("").isEqualTo(1);
		Assertions.assertThat(activeDeposits.get(0).findElement(By.xpath(".//a")).getText())
				.as("Checking product name").isEqualTo(Product.getProductName());
		Assertions.assertThat(activeDeposits.get(0).findElement(By.xpath(".//td[3]/div")).getText()
				.replaceAll("[^\\d.]", ""))
				.as("Checking duration").isEqualTo(Product.getDurationValue());
		//TODO: http://jira.moex.com/browse/MP-2480
		// Assertions.assertThat(activeDeposits.get(0).findElement(By.xpath("./td[5]/div")).getText().replaceAll(" %", ""))
//				.as("Checking percentage").isEqualTo(Product.getPercentage());
		Assertions.assertThat(activeDeposits.get(0).findElement(By.xpath(".//td[6]/div")).getText()
				.replaceAll("[^\\d.]", ""))
				.as("Checking amount").isEqualTo(Product.getAmountValue());
	}
}
