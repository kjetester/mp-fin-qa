package com.moex.mpfin.qa.pages.registration;

import com.moex.mpfin.qa.businessobjects.User;
import com.moex.mpfin.qa.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class QuestionnairePage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Учётная запись Госуслуги успешно привязана";
	private static final String QUESTIONS_BLOCK_SELECTOR = "//form/div[5]";


	@FindBy(xpath = "//form/div[1]//input[@name='fio']")
	private WebElement fioField;

	@FindBy(xpath = "//input[@name='sex']")
	private WebElement sexField;

	@FindBy(xpath = "//input[@name='dayOfBirthday']")
	private WebElement birthDayField;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@name='phone']")
	private WebElement phoneField;

	@FindBy(xpath = "//input[@name='documentType']")
	private WebElement documentTypeField;

	@FindBy(xpath = "//input[@name='documentSerial']")
	private WebElement documentSerialField;

	@FindBy(xpath = "//input[@name='documentDate']")
	private WebElement documentDateField;

	@FindBy(xpath = "//input[@name='citizenship']")
	private WebElement citizenshipField;

	@FindBy(xpath = "//input[@name='issuedBy']")
	private WebElement docIssuerField;

	@FindBy(xpath = "//input[@name='departmentCode']")
	private WebElement docIssuerDepartmentCodeField;

	@FindBy(xpath = "//input[@name='placeOfBirth']")
	private WebElement placeOfBirthField;

	@FindBy(xpath = "//input[@name='snils']")
	private WebElement snilsField;

	@FindBy(xpath = "//input[@name='inn']")
	private WebElement innField;

	@FindBy(xpath = "//*[@name='secretWord']/div[1]")
	private WebElement secretWordField;

	@FindBy(xpath = "//input[@name='Адрес регистрации']")
	private WebElement registrationAddressField;

	@FindBy(xpath = "//input[@name='Адрес проживания']")
	private WebElement residentialAddressField;

	@FindBy(xpath = "//*[@name='matchWithRegistration']/..")
	private WebElement matchWithRegistrationCheckbox;

	@FindBy(xpath = "//input[@name='dateOfRegistration']")
	private WebElement registrationDateField;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR)
	private WebElement questionsBlock;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR + "/div[2]//span/div/div[1]/label/span")
	private WebElement question1Yes;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR + "/div[3]//span/div/div[2]/label/span")
	private WebElement question2No;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR + "/div[4]//span/div/div[2]/label/span")
	private WebElement question3No;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR + "/div[5]//span/div/div[1]/label/span")
	private WebElement question4Yes;

	@FindBy(xpath = QUESTIONS_BLOCK_SELECTOR + "/div[6]//span/div/div[1]/label/span")
	private WebElement question5Yes;

	@FindBy(xpath = "//button")
	private WebElement submitButton;

	@Override
	public QuestionnairePage checkIfPageOpens() {
		waitForElementIsVisible(submitButton);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public QuestionnairePage verifyStaticFields() {
		Assertions.assertThat(fioField.getAttribute("value"))
				.as("Check FIO field")
				.isEqualTo(User.getLastName() + " " + User.getFirstName() + " " + User.getPatronymicName());
		Assertions.assertThat(sexField.getAttribute("value"))
				.as("Check SEX field").isEqualTo(User.getSexRu());
		Assertions.assertThat(birthDayField.getAttribute("value"))
				.as("Check BIRTH DAY field")
				.isEqualTo(User.getBirthDay() + "." + User.getBirthMonth() + "." + User.getBirthYear());
		Assertions.assertThat(emailField.getAttribute("value"))
				.as("Check EMAIL field").isEqualTo(User.getEmailAddress());
		Assertions.assertThat(phoneField.getAttribute("value"))
				.as("Check PHONE NUMBER field").isEqualTo("+" + User.getPhoneNumber());
		Assertions.assertThat(documentTypeField.getAttribute("value"))
				.as("Check DOC TYPE field").isEqualTo(User.getDocType());
		Assertions.assertThat(documentSerialField.getAttribute("value"))
				.as("Check DOC SERIAL field").isEqualTo(User.getDocSerial());
		Assertions.assertThat(documentDateField.getAttribute("value"))
				.as("Check DOC DATE field").isEqualTo(User.getDocIssueDate());
		Assertions.assertThat(citizenshipField.getAttribute("value"))
				.as("Check CITIZENSHIP field").isEqualTo(User.getSitezenshipCountry());
		Assertions.assertThat(docIssuerField.getAttribute("value"))
				.as("Check DOC ISSUER field").isEqualTo(User.getDocIssuer());
		Assertions.assertThat(docIssuerDepartmentCodeField.getAttribute("value"))
				.as("Check DOC ISSUER DEPARTMENT CODE field").isEqualTo(User.getDocIssuerDepartmentCode());
		Assertions.assertThat(placeOfBirthField.getAttribute("value"))
				.as("Check BIRTH PLACE field").isEqualTo(User.getPlaceOfBirth());
		Assertions.assertThat(snilsField.getAttribute("value"))
				.as("Check SNILS field").isEqualTo(User.getSnilsNumber());
		Assertions.assertThat(innField.getAttribute("value"))
				.as("Check INN field").isEqualTo(User.getInnNumber());
//		Assertions.assertThat(registrationAddressField.getAttribute("value"))
//				.as("Check Registration Address field").isEqualTo(User.getRegistrationAddress());
//		Assertions.assertThat(residentialAddressField.getAttribute("value"))
//				.as("Check Residential Address field").isEqualTo(User.getResidentialAddress());
		return this;
	}

	public void fillAndSubmitForm() {
		actions.moveToElement(scrollTo(secretWordField)).click().sendKeys(User.getSecretWord()).build().perform();
		clickViaJavaScriptExecutor(registrationDateField).sendKeys(User.getAddressRegistrationDate());
		matchWithRegistrationCheckbox.click();
		scrollTo(questionsBlock);
		question1Yes.click();
		question2No.click();
		question3No.click();
		question4Yes.click();
		question5Yes.click();
		waitForElementToBeClickable(scrollTo(submitButton)).click();
	}
}
