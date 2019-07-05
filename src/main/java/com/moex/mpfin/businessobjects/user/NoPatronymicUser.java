package com.moex.mpfin.businessobjects.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class NoPatronymicUser extends UserDecorator {

	public NoPatronymicUser(FlexibleUser user) {
		super(user);
		LogManager.getRootLogger().log(Level.INFO, "Creating an User without a patronymic.");
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
