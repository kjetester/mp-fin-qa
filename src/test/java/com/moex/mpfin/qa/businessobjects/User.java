package com.moex.mpfin.qa.businessobjects;

public class User {

	private static String userId = "";
	private static final String LAST_NAME = "Колодзей";
	private static final String FIRST_NAME = "Юлиан";
	private static final String PATRONYMIC_NAME = "Евгеньевич";
	private static final String SEX_RU = "Мужчина";
	private static final String BIRTH_DAY = "08";
	private static final String BIRTH_MONTH = "06";
	private static final String BIRTH_YEAR = "1983";
	private static final String EMAIL_ADDRESS = "yulian.kolodzey@moex.com";
	private static final String PHONE_NUMBER = "7771112233";
	private static final String ESIA_PHONE_NUMBER = "9998520302";
	private static final String ESIA_PASSWORD = ".L4/s8=p<sP";
	private static final String DOC_TYPE = "Паспорт гражданина Российской Федерации";
	private static final String DOC_SERIAL = "5705 654564";
	private static final String DOC_ISSUE_DATE = "09.03.2018";
	private static final String SITEZENSHIP_COUNTRY = "РФ";
	private static final String DOC_ISSUER = "Отделом Управления Федеральной Миграционной Службы России по " +
			"Пермскому краю в Свердловском районе города Перми";
	private static final String DOC_ISSUER_DEPARTMENT_CODE = "758889";
	private static final String PLACE_OF_BIRTH = "Пермь";
	private static final String SNILS_NUMBER = "936-858-695 91";
	private static final String INN_NUMBER = "291152804593";
	private static final String SECRET_WORD = "power123";
	private static final String REGISTRATION_ADDRESS = "Москва, г. Москва, Липецкая, д. 26, кв. 77";
	private static final String RESIDENTIAL_ADDRESS = "Московская, г. Люберцы, Дружбы, д. 456, кв. 312";

	private static final String REGISTRATION_DATE = "20022002";
	private static final boolean NO_PATRONYMIC = false;
	public static String getDocType() {
		return DOC_TYPE;
	}

	public static String getDocSerial() {
		return DOC_SERIAL;
	}

	public static String getDocIssueDate() {
		return DOC_ISSUE_DATE;
	}

	public static String getSitezenshipCountry() {
		return SITEZENSHIP_COUNTRY;
	}

	public static String getDocIssuer() {
		return DOC_ISSUER;
	}

	public static String getDocIssuerDepartmentCode() {
		return DOC_ISSUER_DEPARTMENT_CODE;
	}

	public static String getPlaceOfBirth() {
		return PLACE_OF_BIRTH;
	}

	public static String getSnilsNumber() {
		return SNILS_NUMBER;
	}

	public static String getInnNumber() {
		return INN_NUMBER;
	}

	public static String getLastName() {
		return LAST_NAME;
	}

	public static String getFirstName() {
		return FIRST_NAME;
	}

	public static String getPatronymicName() {
		return PATRONYMIC_NAME;
	}

	public static String getSexRu() {
		return SEX_RU;
	}

	public static String getBirthDay() {
		return BIRTH_DAY;
	}

	public static String getBirthMonth() {
		return BIRTH_MONTH;
	}

	public static String getBirthYear() {
		return BIRTH_YEAR;
	}

	public static String getPhoneNumber() {
		return PHONE_NUMBER;
	}

	public static String getEmailAddress() {
		return EMAIL_ADDRESS;
	}

	public static String getEsiaPhoneNumber() {
		return ESIA_PHONE_NUMBER;
	}

	public static String getEsiaPassword() {
		return ESIA_PASSWORD;
	}

	public static String getSecretWord() {
		return SECRET_WORD;
	}

	public static String getAddressRegistrationDate() {
		return REGISTRATION_DATE;
	}

	public static boolean isNoPatronymic() {
		return NO_PATRONYMIC;
	}

	public static String getRegistrationAddress() {
		return REGISTRATION_ADDRESS;
	}

	public static String getResidentialAddress() {
		return RESIDENTIAL_ADDRESS;
	}

	public static String getRegistrationDate() {
		return REGISTRATION_DATE;
	}

	public static void setUserId(String userId) {
		User.userId = userId;
	}

	public static String getUserId() {
		return userId;
	}
}
