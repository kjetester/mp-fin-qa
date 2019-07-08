package com.moex.mpfin.businessobjects.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoPatronymicUser extends UserDecorator {

	public NoPatronymicUser(FlexibleUser user) {
		super(user);
		Logger logger = LogManager.getLogger(this);
		logger.log(Level.INFO, "Creating an User without a patronymic.");
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
