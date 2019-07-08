package com.moex.mpfin.businessobjects.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicUser implements FlexibleUser {

	private Logger logger = LogManager.getLogger(this);

	public BasicUser() {
		logger.log(Level.DEBUG, "Creating a Basic User Business Object.");
	}

	private static String userId = "";
	private static final String LAST_NAME = "Колодзей";
	private static final String FIRST_NAME = "Юлиан";
	private static final String PATRONYMIC_NAME = "Евгеньевич";
	private static final String SEX_RU = "Мужчина";
	private static final String BIRTH_DAY = "08";
	private static final String BIRTH_MONTH = "06";
	private static final String BIRTH_YEAR = "1983";
	private static final String EMAIL_ADDRESS = "yulian.kolodzey@moex.com";
	private static final String PHONE_NUMBER = "77771112233";
	private static final String ESIA_PHONE_NUMBER = "9998520302";
	private static final String ESIA_PASSWORD = ".L4/s8=p<sP";
	private static final String DOC_TYPE = "Паспорт";
	private static final String DOC_SERIAL = "5705 654564";
	private static final String DOC_ISSUE_DATE = "09.03.2018";
	private static final String CITIZENSHIP_COUNTRY = "РФ";
	private static final String DOC_ISSUER = "Отделом Управления Федеральной Миграционной Службы России по " +
			"Пермскому краю в Свердловском районе города Перми";
	private static final String DOC_ISSUER_DEPARTMENT_CODE = "758889";
	private static final String PLACE_OF_BIRTH = "Пермь";
	private static final String SNILS_NUMBER = "936-858-695 91";
	private static final String INN_NUMBER = "291152804593";
	private static final String SECRET_WORD = "power123";
	private static final String REGISTRATION_ADDRESS = "Москва, Липецкая, д. 26, кв. 77";
	private static final String RESIDENTIAL_ADDRESS = "Московская, г. Люберцы, Дружбы, д. 456, кв. 312";
	private static final String REGISTRATION_DATE = "20022002";
	private static final boolean NO_PATRONYMIC = false;

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		logger.log(Level.DEBUG, String.format("Setting up the User ID as '%s'", userId));
		BasicUser.userId = userId;
	}

	@Override
	public String getLastName() {
		return LAST_NAME;
	}

	@Override
	public String getFirstName() {
		return FIRST_NAME;
	}

	@Override
	public String getPatronymicName() {
		return PATRONYMIC_NAME;
	}

	@Override
	public boolean isNoPatronymic() {
		return NO_PATRONYMIC;
	}

	@Override
	public String getDocType() {
		return DOC_TYPE;
	}

	@Override
	public String getDocSerial() {
		return DOC_SERIAL;
	}

	@Override
	public String getDocIssueDate() {
		return DOC_ISSUE_DATE;
	}

	@Override
	public String getCitizenshipCountry() {
		return CITIZENSHIP_COUNTRY;
	}

	@Override
	public String getDocIssuer() {
		return DOC_ISSUER;
	}

	@Override
	public String getDocIssuerDepartmentCode() {
		return DOC_ISSUER_DEPARTMENT_CODE;
	}

	@Override
	public String getPlaceOfBirth() {
		return PLACE_OF_BIRTH;
	}

	@Override
	public String getSnilsNumber() {
		return SNILS_NUMBER;
	}

	@Override
	public String getInnNumber() {
		return INN_NUMBER;
	}

	@Override
	public String getSexRu() {
		return SEX_RU;
	}

	@Override
	public String getBirthDay() {
		return BIRTH_DAY;
	}

	@Override
	public String getBirthMonth() {
		return BIRTH_MONTH;
	}

	@Override
	public String getBirthYear() {
		return BIRTH_YEAR;
	}

	@Override
	public String getPhoneNumber() {
		return PHONE_NUMBER;
	}

	@Override
	public String getEmailAddress() {
		return EMAIL_ADDRESS;
	}

	@Override
	public String getEsiaPhoneNumber() {
		return ESIA_PHONE_NUMBER;
	}

	@Override
	public String getEsiaPassword() {
		return ESIA_PASSWORD;
	}

	@Override
	public String getSecretWord() {
		return SECRET_WORD;
	}

	@Override
	public String getAddressRegistrationDate() {
		return REGISTRATION_DATE;
	}

	@Override
	public String getRegistrationAddress() {
		return REGISTRATION_ADDRESS;
	}

	@Override
	public String getResidentialAddress() {
		return RESIDENTIAL_ADDRESS;
	}

	@Override
	public String getRegistrationDate() {
		return REGISTRATION_DATE;
	}
}
