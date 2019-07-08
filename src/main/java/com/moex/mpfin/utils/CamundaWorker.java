package com.moex.mpfin.utils;

import com.moex.mpfin.businessobjects.Contract;
import com.moex.mpfin.businessobjects.user.FlexibleUser;
import de.sstoehr.harreader.model.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.moex.mpfin.utils.EnvironmentProperties.getEnvProps;
import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static io.restassured.RestAssured.given;

/**
 * Interaction with the Camunda BPM.
 */
public class CamundaWorker {

	private Logger logger = LogManager.getLogger(this);

	private static final String CAMUNDA_TOOL_URL = getEnvProps().getProperty("CAMUNDA_TOOL_URL");
	private static final String CAMUNDA_URL = getEnvProps().getProperty("CAMUNDA_URL");
	private static final String SKIP_EBS = "skip-ebs";
	private static final String SKIP_ACCOUNT_OPENING = "skip-account-opening";
	private static final String SKIP_ACCOUNT_INIT_REPLENISHMENT = "skip-account-deposit";
	private String BUSINESS_KEY = "";

	/**
	 * Skipping the EBS process.
	 */
	public void skipEbsProcessAndSetUserId(FlexibleUser user) {
		logger.log(Level.DEBUG, String.format(
				"Skipping 'The Physical Identification Business Process' for the process with business key '%s'", BUSINESS_KEY));
		String userId =
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
						.path("moexUserId");
		user.setUserId(userId);
	}

	/**
	 * Skipping an account opening process (status from 3 to 6).
	 */
	public void skipAccountOpeningAndSetContractId(FlexibleUser user) {
		logger.log(Level.DEBUG, String.format(
				"Skipping 'The Account Opening Business Process' for the process with business key '%s'", BUSINESS_KEY));
		String contractId =
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
						.path("contractId");
		Contract.setContractId(contractId);
	}

	/**
	 * Skipping an account initial replenishment process and opening a deposit (status from 6 to 7).
	 */
	public void skipDepositOpening(FlexibleUser user) {
		logger.log(Level.DEBUG, String.format(
				"Skipping 'The Deposit Opening Business Process' for the process with business key '%s'", BUSINESS_KEY));
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
		logger.log(Level.DEBUG, String.format(
				"The Business key '%s' has been set.", BUSINESS_KEY));
		return this;
	}
}
