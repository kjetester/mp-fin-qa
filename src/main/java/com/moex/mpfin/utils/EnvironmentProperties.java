package com.moex.mpfin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties Singleton.
 */
public class EnvironmentProperties {

	private static Properties envPropsInstance;

	public static Properties getEnvProps() {
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
