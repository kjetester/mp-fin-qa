package com.moex.mpfin.qa.businessobjects;

public class Product {

	private static final String PRODUCT_ID = "54";
	private static final String OPTION_ID = "6382";
	private static final String DURATION_TYPE = "1";
	private static final String DURATION_VALUE = "30";
	private static final String AMOUNT_VALUE = "1000";
	private static final String RATE_VALUE = "999";

	public static String getProductId() {
		return PRODUCT_ID;
	}

	public static String getOptionId() {
		return OPTION_ID;
	}

	public static String getDurationType() {
		return DURATION_TYPE;
	}

	public static String getDurationValue() {
		return DURATION_VALUE;
	}

	public static String getAmountValue() {
		return AMOUNT_VALUE;
	}

	public static String getRateValue() {
		return RATE_VALUE;
	}
}

