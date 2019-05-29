package com.moex.mpfin.qa.utils;

import com.moex.mpfin.qa.businessobjects.Contract;
import com.moex.mpfin.qa.businessobjects.User;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

import static com.moex.mpfin.qa.tests.BaseTest.*;
import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

public class CamundaWorker {

	private static String endpoint = NEW_DEV_IP + NEW_DEV_CAMUNDA + "/rest/task/";

	public static void grabAndSetUserId() {
		List<LogEntry> entries = getDriver().manage().logs().get(LogType.PERFORMANCE).getAll();
		String registrationTaskId = StringUtils.substringBetween(entries.stream().filter(e ->
			e.getMessage().toLowerCase().contains("registration-4?taskId"))
				.findFirst().toString(),"registration-4?taskId=", "&businessKey=");
		String userId = RestAssured.get(endpoint + registrationTaskId + "/variables?deserializeValues=false")
				.jsonPath().get("moexUserId.value");
		User.setUserId(userId);
	}

	public static void grabAndSetContractId() {
		List<LogEntry> entries = getDriver().manage().logs().get(LogType.PERFORMANCE).getAll();
		String depositOpeningTaskId = StringUtils.substringBetween(entries.stream().filter(e ->
				e.getMessage().toLowerCase().contains("conditions?taskId"))
				.findFirst().toString(),"conditions?taskId=", "&businessKey=");
		String contractId = RestAssured.get(endpoint + depositOpeningTaskId + "/variables?deserializeValues=false")
				.jsonPath().get("contractId.value");
		Contract.setContractId(contractId);
	}
}
