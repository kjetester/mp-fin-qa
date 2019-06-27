package com.moex.mpfin.qa.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      WebDriverManager.chromedriver().version("74").setup();
      driver = new ChromeDriver();
    }
    return driver;
  }

  public static void removeDriver() {
    driver.quit();
    driver = null;
  }
}
