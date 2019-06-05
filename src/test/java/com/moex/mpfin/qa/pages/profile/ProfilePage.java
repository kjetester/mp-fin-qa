package com.moex.mpfin.qa.pages.profile;

import com.moex.mpfin.qa.businessobjects.User;
import com.moex.mpfin.qa.pages.AbstractPage;
import com.moex.mpfin.qa.pages.registration.EsiaAccessGrantingPage;
import com.moex.mpfin.qa.pages.registration.RegistrationPage;
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

	@FindBy(name = "documentType")
	private WebElement documentTypeField;

	@FindBy(name = "documentSerial")
	private WebElement documentSerialField;

	@FindBy(name = "documentDate")
	private WebElement documentDateField;

	@FindBy(name = "citizenship")
	private WebElement citizenshipField;

	@FindBy(name = "issuedBy")
	private WebElement docIssuerField;

	@FindBy(name = "departmentCode")
	private WebElement docIssuerDepartmentCodeField;

	@FindBy(name = "placeOfBirth")
	private WebElement placeOfBirthField;

	@FindBy(name = "snils")
	private WebElement snilsField;

	@FindBy(name = "inn")
	private WebElement innField;

	@FindBy(xpath = "//form/div[2]/div[2]//*[@name='fio']")
	private WebElement registrationAddressField;

	@FindBy(name = "dateOfRegistration")
	private WebElement registrationDateField;

	@FindBy(xpath = "//form/div[2]/div[4]//*[@name='fio']")
	private WebElement residentialAddressField;

	@Override
	public ProfilePage checkIfPageOpens() {
		waitForElementIsVisible(personalDataTab);
		super.checkIfPageOpens(PAGE_UNIQUE_TEXT);
		return this;
	}

	public void verifyStaticFields() {
		Assertions.assertThat(scrollTo(fioField).getText()).as("Checking FIO.")
				.isEqualTo(User.getLastName() + " " + User.getFirstName() + " " + User.getPatronymicName());
		Assertions.assertThat(scrollTo(phoneNumberField).getText().replaceAll("[^0-9]", "").replaceAll("^7", ""))
				.as("Checking Phone Number.").isEqualTo(User.getPhoneNumber());
		//TODO: http://jira.moex.com/browse/MPFIN-1835
//		Assertions.assertThat(scrollTo(emailAddressField).getAttribute("value"))
//				.as("Checking Email.").isEqualTo(User.getEmailAddress());
		Assertions.assertThat(scrollTo(documentTypeField).getAttribute("value"))
				.as("Check DOC TYPE field").isEqualTo(User.getDocType());
		Assertions.assertThat(scrollTo(documentSerialField).getAttribute("value"))
				.as("Check DOC SERIAL field").isEqualTo(User.getDocSerial());
		Assertions.assertThat(scrollTo(documentDateField).getAttribute("value"))
				.as("Check DOC DATE field").isEqualTo(User.getDocIssueDate());
		Assertions.assertThat(scrollTo(citizenshipField).getAttribute("value"))
				.as("Check CITIZENSHIP field").isEqualTo(User.getSitezenshipCountry());
		Assertions.assertThat(scrollTo(docIssuerField).getAttribute("value"))
				.as("Check DOC ISSUER field").isEqualTo(User.getDocIssuer());
		Assertions.assertThat(scrollTo(docIssuerDepartmentCodeField).getAttribute("value"))
				.as("Check DOC ISSUER DEPARTMENT CODE field").isEqualTo(User.getDocIssuerDepartmentCode());
		Assertions.assertThat(scrollTo(placeOfBirthField).getAttribute("value"))
				.as("Check BIRTH PLACE field").isEqualTo(User.getPlaceOfBirth());
		Assertions.assertThat(scrollTo(snilsField).getAttribute("value"))
				.as("Check SNILS field").isEqualTo(User.getSnilsNumber());
		Assertions.assertThat(scrollTo(innField).getAttribute("value"))
				.as("Check INN field").isEqualTo(User.getInnNumber());
//		Assertions.assertThat(scrollTo(registrationAddressField).getAttribute("value"))
//				.as("Check INN field").isEqualTo(User.getRegistrationAddress());
		Assertions.assertThat(scrollTo(registrationDateField).getAttribute("value").replaceAll("[^0-9]", ""))
				.as("Check Registration Date Field").isEqualTo(User.getAddressRegistrationDate());
//		Assertions.assertThat(scrollTo(residentialAddressField).getAttribute("value"))
//				.as("Check INN field").isEqualTo(User.getResidentialAddress());
	}
}
