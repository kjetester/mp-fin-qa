package com.moex.mpfin.qa.utils;

import com.moex.mpfin.qa.pages.AbstractPage;
import org.openqa.selenium.support.PageFactory;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

/**
 * Generate a new Object Page.
 */
public class PageGenerator {

	/**
	 * Generate a new Page object.
	 * @param pageClass page class
	 * @param <PageClass> generic page
	 * @return page pbject instance
	 */
	public <PageClass extends AbstractPage> PageClass getInstance(Class<PageClass> pageClass) {
		return PageFactory.initElements(getDriver(), pageClass);
	}

}
