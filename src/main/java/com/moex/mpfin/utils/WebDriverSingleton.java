package com.moex.mpfin.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {

  private static WebDriver driver;
  private static Logger logger = LogManager.getLogger(WebDriverManager.class.getSimpleName());

  public static WebDriver getDriver() {
    if (driver == null) {
      logger.log(Level.DEBUG, "Initializing WebDriver instance.");
      WebDriverManager.chromedriver().version("74").setup();
      driver = new ChromeDriver();
    }
    return driver;
  }

  public static void removeDriver() {
    logger.log(Level.DEBUG, "Destroying WebDriver instance.");
    driver.quit();
    driver = null;
  }
}
