package com.moex.mpfin.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties Singleton.
 */
public class EnvironmentProperties {

	private static Logger logger = LogManager.getLogger(EnvironmentProperties.class);
	private static Properties envPropsInstance;

	public static Properties getEnvProps() {
		logger.log(Level.DEBUG, "Setting up environment props.");
		if (envPropsInstance == null) {
			try {
				 envPropsInstance = new Properties();
				 envPropsInstance.load(new FileInputStream(new File("src/main/resources/config.properties")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return envPropsInstance;
	}
}
