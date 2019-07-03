package com.moex.mpfin.pages.profile;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import com.moex.mpfin.pages.AbstractPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbstractPage {

	private static final String PAGE_UNIQUE_TEXT = "Профиль";
	private static final String CONTACTS_BLOCK = "//div[@id = 'app']/div[1]/div/div[3]";

	@FindBy(css = "a[href*='#personData']")
	private WebElement personalDataTab;

	@FindBy(xpath = "//div[@id = 'app']/div[1]/div/div[2]")
	private WebElement fioField;

	@FindBy(xpath = CONTACTS_BLOCK + "/div[1]/div/div[2]/div")
	private WebElement phoneNumberField;

	@FindBy(xpath = CONTACTS_BLOCK + "/div[2]/div/div[2]/div")
	private WebElement emailAddressField;

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

	@FindBy(xpath = "//input[@name='Адрес регистрации']")
	private WebElement registrationAddressField;

	@FindBy(xpath = "//input[@name='dateOfRegistration']")
	private WebElement registrationDateField;

	@FindBy(xpath = "//input[@name='Адрес проживания']")
	private WebElement residentialAddressField;

	@Override
	public ProfilePage checkIfPageOpens() {
		waitForElementIsVisible(personalDataTab);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void verifyStaticFields(FlexibleUser user) {
		Assertions.assertThat(scrollTo(fioField).getText()).as("Checking FIO.")
				.isEqualTo(user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymicName());
		Assertions.assertThat(scrollTo(phoneNumberField).getText().replaceAll("[^0-9]", ""))
				.as("Checking Phone Number.").isEqualTo(user.getPhoneNumber());
		Assertions.assertThat(scrollTo(emailAddressField).getText())
				.as("Checking Email.").isEqualTo(user.getEmailAddress());
		Assertions.assertThat(scrollTo(documentTypeField).getAttribute("value"))
				.as("Check DOC TYPE field").isEqualTo(user.getDocType());
		Assertions.assertThat(scrollTo(documentSerialField).getAttribute("value"))
				.as("Check DOC SERIAL field").isEqualTo(user.getDocSerial());
		Assertions.assertThat(scrollTo(documentDateField).getAttribute("value"))
				.as("Check DOC DATE field").isEqualTo(user.getDocIssueDate());
		Assertions.assertThat(scrollTo(citizenshipField).getAttribute("value"))
				.as("Check CITIZENSHIP field").isEqualTo(user.getCitizenshipCountry());
		Assertions.assertThat(scrollTo(docIssuerField).getAttribute("value"))
				.as("Check DOC ISSUER field").isEqualTo(user.getDocIssuer());
		Assertions.assertThat(scrollTo(docIssuerDepartmentCodeField).getAttribute("value"))
				.as("Check DOC ISSUER DEPARTMENT CODE field").isEqualTo(user.getDocIssuerDepartmentCode());
		Assertions.assertThat(scrollTo(placeOfBirthField).getAttribute("value"))
				.as("Check BIRTH PLACE field").isEqualTo(user.getPlaceOfBirth());
		Assertions.assertThat(scrollTo(snilsField).getAttribute("value"))
				.as("Check SNILS field").isEqualTo(user.getSnilsNumber());
		Assertions.assertThat(scrollTo(innField).getAttribute("value"))
				.as("Check INN field").isEqualTo(user.getInnNumber());
//		Assertions.assertThat(scrollTo(registrationAddressField).getAttribute("value"))
//				.as("Check INN field").isEqualTo(user.getRegistrationAddress());
		Assertions.assertThat(scrollTo(registrationDateField).getAttribute("value").replaceAll("[^0-9]", ""))
				.as("Check Registration Date Field").isEqualTo(user.getAddressRegistrationDate());
//		Assertions.assertThat(scrollTo(residentialAddressField).getAttribute("value"))
//				.as("Check INN field").isEqualTo(user.getResidentialAddress());
	}
}
