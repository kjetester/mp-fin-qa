package com.moex.mpfin.utils;

import com.moex.mpfin.pages.AbstractPage;
import org.openqa.selenium.support.PageFactory;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

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
