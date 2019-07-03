package com.moex.mpfin.businessobjects.user;

public class NoPatronymicUser extends UserDecorator {

	public NoPatronymicUser(FlexibleUser user) {
		super(user);
	}

	@Override
	public String getPatronymicName() {
		return "";
	}

	@Override
	public boolean isNoPatronymic() {
		return true;
	}
}
