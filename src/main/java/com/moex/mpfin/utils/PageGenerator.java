package com.moex.mpfin.utils;

import com.moex.mpfin.pages.AbstractPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

/**
 * Generate a new Object Page.
 */
public class PageGenerator {

	private static Logger logger = LogManager.getLogger(PageGenerator.class.getSimpleName());

	/**
	 * Generate a new Page object.
	 * @param pageClass page class
	 * @param <PageClass> generic page
	 * @return page pbject instance
	 */
	public <PageClass extends AbstractPage> PageClass getInstance(Class<PageClass> pageClass) {
		logger.log(Level.DEBUG, String.format("Instantiating '%s'", pageClass.getSimpleName()));
		return PageFactory.initElements(getDriver(), pageClass);
	}
}
