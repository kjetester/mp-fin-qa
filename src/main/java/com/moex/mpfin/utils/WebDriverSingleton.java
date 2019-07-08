package com.moex.mpfin.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

  private static WebDriver driver;
  private static Logger logger = LogManager.getLogger(WebDriverManager.class);

  private final static int IMPLICITLY_WAIT_TIMEOUT = 2;

  public static WebDriver getDriver() {
    if (driver == null) {
      logger.log(Level.DEBUG, "Initializing WebDriver instance.");
      WebDriverManager.chromedriver().version("74").setup();
      driver = new ChromeDriver();
      logger.log(Level.DEBUG,
          String.format("Setting implicitlyWait to %s %s", IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS));
      getDriver().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
      logger.log(Level.DEBUG, "Positioning the window to upper left corner.");
      driver.manage().window().setPosition(new Point(0, 0));
    } else {
      logger.log(Level.DEBUG, "Getting  WebDriver instance.");
    }
    return driver;
  }

  public static void removeDriver() {
    logger.log(Level.DEBUG, "Destroying WebDriver instance.");
    driver.quit();
    driver = null;
  }
}
