package com.moex.mpfin.pages.common;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FooterPage extends AbstractPage {

	private Logger logger = LogManager.getLogger(FooterPage.class.getSimpleName());

	@Override
	public FooterPage checkIfPageOpens() {
		logger.log(Level.INFO, "Checking if the Page opens.");
		return this;
	}


}
