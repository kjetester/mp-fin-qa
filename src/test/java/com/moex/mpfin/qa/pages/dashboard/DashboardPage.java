package com.moex.mpfin.qa.pages.dashboard;

import com.moex.mpfin.qa.businessobjects.Contract;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

public class DashboardPage extends AbstractPage {

	private static final String DASHBOARD_URL = "/dashboard";

	@FindBy(css = "div[class*='card ']")
	private List<WebElement> cartProductBanners;

	@Override
	public DashboardPage checkIfPageOpens() {
		waitForAllElementsAreVisible(cartProductBanners);
		Assertions.assertThat(getDriver().getCurrentUrl()).as("Check current URL").contains(DASHBOARD_URL);
		super.checkIfPageOpens(DASHBOARD_URL);
		return this;
	}

	public void checkContractStatus(Contract.Status status) {
		WebElement contractStatusOnProductBanner
				= getDriver().findElement(By.xpath("//div[contains(text(), 'Статус заявки')]/../div[2]"));
		Assertions.assertThat(contractStatusOnProductBanner.getText())
				.as("Checking contract status.")
				.isEqualTo(status.toString());
	}
}
