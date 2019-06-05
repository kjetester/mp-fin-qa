package com.moex.mpfin.qa.businessobjects;

public class Product {

	private static final String PRODUCT_ID = "54";
	private static final String OPTION_ID = "6382";
	private static final String PRODUCT_NAME = "Очень удобный тестовый депозит";
	private static final String PROVIDER_NAME = "ПАО БАНК ЗЕНИТ";
	private static final String DURATION_TYPE = "1";
	private static final String DURATION_VALUE = "30";
	private static final String AMOUNT_VALUE = "1000";
	private static String percentageValue;

	public static String getProductId() {
		return PRODUCT_ID;
	}

	public static String getOptionId() {
		return OPTION_ID;
	}

	public static String getProductName() {
		return PRODUCT_NAME;
	}

	public static String getProviderName() {
		return PROVIDER_NAME;
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

	public static String getPercentage() {
		return percentageValue;
	}

	public static void setPercentage(String percentage) {
		percentageValue = percentage;
	}
}

