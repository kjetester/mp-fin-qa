package com.moex.mpfin.qa.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

public class DriverFactory {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      WebDriverManager.chromedriver().version("74").setup();
      ChromeOptions options = new ChromeOptions();
      LoggingPreferences logPrefs = new LoggingPreferences();
      logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
      options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//      options.addArguments("--proxy-server=" + getSeleniumProxy().getHttpProxy());
      driver = new ChromeDriver(options);
    }
    return driver;
  }

  public static void removeDriver() {
    driver.quit();
    driver = null;
  }
}
