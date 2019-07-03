package com.moex.mpfin.businessobjects.user;

public interface FlexibleUser {

	void setUserId(String userId);
	String getUserId();
	String getLastName();
	String getFirstName();
	String getPatronymicName();
	boolean isNoPatronymic();
	String getSexRu();
	String getBirthDay();
	String getPlaceOfBirth();
	String getBirthMonth();
	String getBirthYear();
	String getPhoneNumber();
	String getEmailAddress();
	String getDocType();
	String getDocSerial();
	String getDocIssueDate();
	String getDocIssuer();
	String getDocIssuerDepartmentCode();
	String getCitizenshipCountry();
	String getSnilsNumber();
	String getInnNumber();
	String getEsiaPhoneNumber();
	String getEsiaPassword();
	String getSecretWord();
	String getAddressRegistrationDate();
	String getRegistrationAddress();
	String getResidentialAddress();
	String getRegistrationDate();
}
