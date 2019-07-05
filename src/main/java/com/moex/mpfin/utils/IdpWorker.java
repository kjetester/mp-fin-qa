package com.moex.mpfin.utils;

import com.moex.mpfin.businessobjects.user.FlexibleUser;
import de.sstoehr.harreader.model.HttpStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.moex.mpfin.utils.EnvironmentProperties.getEnvProps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Interaction with IDP.
 */
public class IdpWorker {

	private static final String IDP_URL = getEnvProps().getProperty("IDP_URL");

	private Logger logger = LogManager.getLogger(IdpWorker.class.getSimpleName());

	/**
	 * Deleting the User.
	 * @param user User
	 */
	public void deleteUser(FlexibleUser user) {
		logger.log(Level.INFO, String.format("Deleting user with E-mail: '%s'.", user.getEmailAddress()));
		given()
				.header("Authorization", "Basic bXBfYWRtaW46UlQ2P34xV1E=")
				.contentType("application/json")
				.when()
				.get(IDP_URL + user.getEmailAddress() + "/delete")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.getCode());
	}

	/**
	 * Checking User's full name.
	 * @param user User
	 */
	public void checkUserFullName(FlexibleUser user) {
		logger.log(Level.INFO, "Checking user's full name.");
		given()
				.header("Authorization", "Basic bXBfYWRtaW46UlQ2P34xV1E=")
				.contentType("application/json")
				.when()
				.get(IDP_URL + user.getUserId())
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.getCode())
				.body("lastName", equalTo(user.getLastName()))
				.body("firstName", equalTo(user.getFirstName()))
				.body("middleName", equalTo(user.getPatronymicName()))
		.body("doesNotHaveMiddleName", equalTo(user.isNoPatronymic()));
	}


}
