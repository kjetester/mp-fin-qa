package com.moex.mpfin.pages.deposit;

import com.moex.mpfin.pages.AbstractPage;
import com.moex.mpfin.utils.WebDriverSingleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepositInitialReplenishmentPage extends AbstractPage {

	private Logger logger = LogManager.getLogger(this);

	@FindBy(xpath = "//*[contains(text(), 'Сумма вклада')]")
	private WebElement totalAmount;

	@FindBy(xpath = "//*[contains(text(), 'Поступило')]/../div[2]")
	private WebElement receivedAmount;

	@FindBy(xpath = "//div[@class = 'multiselect']")
	private WebElement sourceComboBox;

	@FindBy(xpath = "//ul[@class = 'multiselect__content']/li")
	private List<WebElement> banksComboBoxList;

	@FindBy(xpath = "//input[@name= 'amount']")
	private WebElement amountInput;

	@FindBy(xpath = "//*[contains(text(), 'Перевести')]/../../..")
	private WebElement payButton;

	@Override
	public DepositInitialReplenishmentPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if page opens.");
		waitForElementIsVisible(sourceComboBox);
		super.checkIfPageOpens("Выберите источник для пополнения вклада");
		return this;
	}

	public void initialReplenishDeposit() {

		//TODO: SUT realization will be replaced.
		Actions actions = new Actions(WebDriverSingleton.getDriver());
		actions.moveToElement(sourceComboBox).click().build().perform();
		"ПАО Сбербанк".chars().mapToObj(i -> (char)i).forEach(c
				-> actions.sendKeys(c.toString()).pause(300).build().perform());
		banksComboBoxList.get(3).click();
		waitForElementToBeClickable(amountInput).sendKeys(StringUtils.substringBetween(totalAmount.getText(), ":", ",").replaceAll(" ", ""));
		waitForElementToBeClickable(payButton).click();
		actions.pause(5000).build().perform();
		Assertions.assertThat(
				StringUtils.substringBefore(receivedAmount.getText(), ",").replaceAll(" ", ""))
				.as("")
				.isEqualTo(StringUtils.substringBefore(totalAmount.getText(), ",").replaceAll(" ", ""));
	}
}
