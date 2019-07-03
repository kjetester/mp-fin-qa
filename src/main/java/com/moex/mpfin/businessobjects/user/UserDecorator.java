package com.moex.mpfin.businessobjects.user;

public class UserDecorator implements FlexibleUser {

	private FlexibleUser user;

	UserDecorator(FlexibleUser user) {
		this.user = user;
	}

	@Override
	public String getLastName() {
		return user.getLastName();
	}

	@Override
	public String getFirstName() {
		return user.getFirstName();
	}

	@Override
	public String getPatronymicName() {
		return user.getPatronymicName();
	}

	@Override
	public boolean isNoPatronymic() {
		return user.isNoPatronymic();
	}

	@Override
	public String getSexRu() {
		return user.getSexRu();
	}

	@Override
	public String getPlaceOfBirth() {
		return user.getPlaceOfBirth();
	}

	@Override
	public String getBirthDay() {
		return user.getBirthDay();
	}

	@Override
	public String getBirthMonth() {
		return user.getBirthMonth();
	}

	@Override
	public String getBirthYear() {
		return user.getBirthYear();
	}

	@Override
	public String getCitizenshipCountry() {
		return user.getCitizenshipCountry();
	}

	@Override
	public String getDocType() {
		return user.getDocType();
	}

	@Override
	public String getDocSerial() {
		return user.getDocSerial();
	}

	@Override
	public String getDocIssueDate() {
		return user.getDocIssueDate();
	}

	@Override
	public String getDocIssuer() {
		return user.getDocIssuer();
	}

	@Override
	public String getDocIssuerDepartmentCode() {
		return user.getDocIssuerDepartmentCode();
	}

	@Override
	public String getPhoneNumber() {
		return user.getPhoneNumber();
	}

	@Override
	public String getEmailAddress() {
		return user.getEmailAddress();
	}

	@Override
	public String getSnilsNumber() {
		return user.getSnilsNumber();
	}

	@Override
	public String getInnNumber() {
		return user.getInnNumber();
	}

	@Override
	public String getEsiaPhoneNumber() {
		return user.getEsiaPhoneNumber();
	}

	@Override
	public String getEsiaPassword() {
		return user.getEsiaPassword();
	}

	@Override
	public String getSecretWord() {
		return user.getSecretWord();
	}

	@Override
	public String getAddressRegistrationDate() {
		return user.getAddressRegistrationDate();
	}

	@Override
	public String getRegistrationAddress() {
		return user.getRegistrationAddress();
	}

	@Override
	public String getResidentialAddress() {
		return user.getResidentialAddress();
	}

	@Override
	public String getRegistrationDate() {
		return user.getRegistrationDate();
	}

	@Override
	public void setUserId(String userId) {
		user.setUserId(userId);
	}

	@Override
	public String getUserId() {
		return user.getUserId();
	}
}
