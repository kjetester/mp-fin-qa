package com.moex.mpfin.utils;

import com.moex.mpfin.businessobjects.Contract;
import com.moex.mpfin.businessobjects.user.BasicUser;
import com.moex.mpfin.businessobjects.user.FlexibleUser;
import de.sstoehr.harreader.model.HttpStatus;
import org.apache.commons.lang3.StringUtils;

import static com.moex.mpfin.utils.EnvironmentProperties.getEnvProps;
import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static io.restassured.RestAssured.given;

/**
 * Interaction with Camunda BPM.
 */
public class CamundaWorker {

	private static final String CAMUNDA_TOOL_URL = getEnvProps().getProperty("CAMUNDA_TOOL_URL");
	private static final String CAMUNDA_URL = getEnvProps().getProperty("CAMUNDA_URL");
	private static final String SKIP_EBS = "skip-ebs";
	private static final String SKIP_ACCOUNT_OPENING = "skip-account-opening";
	private static final String SKIP_ACCOUNT_INIT_REPLENISHMENT = "skip-account-deposit";
	private String BUSINESS_KEY = "";

	/**
	 * Skipping EBS process.
	 */
	public void skipEbsProcessAndSetUserId(FlexibleUser user) {
		user.setUserId(
		given()
				.queryParam("businessKey", BUSINESS_KEY)
				.queryParam("moexUserId", user.getUserId())
				.contentType("application/json")
		.when()
				.get(CAMUNDA_TOOL_URL + SKIP_EBS)
		.then()
				.assertThat()
				.statusCode(HttpStatus.OK.getCode())
				.extract()
				.path("moexUserId"));
	}

	/**
	 * Skipping account opening process (status from 3 to 6).
	 */
	public void skipAccountOpeningAndSetContractId(FlexibleUser user) {
		Contract.setContractId(
				given()
						.queryParam("businessKey", BUSINESS_KEY)
						.queryParam("moexUserId", user.getUserId())
						.contentType("application/json")
						.when()
						.get(CAMUNDA_TOOL_URL + SKIP_ACCOUNT_OPENING)
						.then()
						.assertThat()
						.statusCode(HttpStatus.OK.getCode())
						.assertThat()
						.extract()
						.path("contractId"));
	}

	/**
	 * Skipping account initial replenishment process (status from 6 to 7).
	 */
	public void skipAccountDeposit(FlexibleUser user) {
		given()
				.queryParam("businessKey", BUSINESS_KEY)
				.queryParam("moexUserId", user.getUserId())
				.contentType("application/json")
				.when()
				.get(CAMUNDA_TOOL_URL + SKIP_ACCOUNT_INIT_REPLENISHMENT)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.getCode());
	}

	/**
	 * Setting current business key.
	 * @return CamundaWorker
	 */
	public CamundaWorker setBusinessKey() {
		BUSINESS_KEY = StringUtils.substringAfter(getDriver().getCurrentUrl(), "&businessKey=");
		return this;
	}
}
