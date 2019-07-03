package com.moex.mpfin.utils;

import com.moex.mpfin.businessobjects.Contract;
import com.moex.mpfin.businessobjects.user.BasicUser;
import com.moex.mpfin.utils.dto.ModificationDto;
import com.moex.mpfin.utils.dto.ProcessInstanceModificationInstructionDto;
import de.sstoehr.harreader.model.HttpStatus;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static com.moex.mpfin.utils.EnvironmentProperties.getEnvProps;
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
	 * Jump to step
	 * https://docs.camunda.org/manual/7.11/reference/rest/modification/post-modification-sync/
	 */
	public void jumpToPhysicalIdentification() {
		final ExtractableResponse<Response> response =
				given()
					.queryParam("businessKey", BUSINESS_KEY)
					.contentType("application/json")
				.when()
					.get(CAMUNDA_TOOL_URL + "get-task")
				.then()
					.assertThat()
					.statusCode(HttpStatus.OK.getCode())
					.extract();
		String processDefinitionId = response.path("task.processDefinitionId");
		String processInstanceId = response.path("task.processInstanceId");

		given()
			.contentType("application/json")
			.body(
				new ModificationDto()
					.processDefinitionId(processDefinitionId)
					.addProcessInstanceIdsItem(processInstanceId)
					.skipCustomListeners(false)
					.skipIoMappings(false)
					.addInstructionsItem(
						new ProcessInstanceModificationInstructionDto()
							.type("startBeforeActivity")
							.activityId("serviceTask_getIdpStatusEBS")
					)
					.addInstructionsItem(
						new ProcessInstanceModificationInstructionDto()
							.type("cancel")
							.activityId("onboardingPersonalData")
							.cancelCurrentActiveActivityInstances(true)
					)
					.addInstructionsItem(
						new ProcessInstanceModificationInstructionDto()
							.type("cancel")
							.activityId("onboardingAccessDenied")
							.cancelCurrentActiveActivityInstances(true)
					)
					.addInstructionsItem(
						new ProcessInstanceModificationInstructionDto()
							.type("cancel")
							.activityId("onboardingBankSelection")
							.cancelCurrentActiveActivityInstances(true)
					)
					.addInstructionsItem(
						new ProcessInstanceModificationInstructionDto()
							.type("cancel")
							.activityId("onboardingPhoneVerificationNumber")
							.cancelCurrentActiveActivityInstances(true)
					)
			)
		.when()
			.post(CAMUNDA_URL + "/rest/modification/execute")
		.then()
			.assertThat()
			.statusCode(HttpStatus.NO_CONTENT.getCode());
	}

	/**
	 * Skipping EBS process.
	 */
	public void skipEbsProcessAndSetUserId(BasicUser user) {
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
	public void skipAccountOpeningAndSetContractId(BasicUser user) {
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
	public void skipAccountDeposit(BasicUser user) {
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
