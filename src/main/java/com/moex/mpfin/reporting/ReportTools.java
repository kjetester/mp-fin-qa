package com.moex.mpfin.reporting;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;

public class ReportTools {

  private static Logger logger = LogManager.getLogger(ReportTools.class.getSimpleName());
  /**
   * Takes a screenshot with a frame.
   * @param element an element to highlight
   */
  public static WebElement takeScreenshotWithFrame(WebElement element) {
    try {
      ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
      takeScreenshot();
    } catch (Exception e) {
      logger.log(Level.WARN, "Couldn't make a screenshot.\n" + e.getMessage());
    } finally {
      ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border=''", element);
    }
    return element;
  }

  /**
   * Takes a screenshot.
   */
  public static void takeScreenshot() {
    try {
      File scr = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
      String filename =  new SimpleDateFormat("yyyyMMddHHmmssSSS'.png'").format(new Date());
      File destination = new File("target/screenshots/" + filename);
      FileUtils.copyFile(scr, destination);
    } catch (IOException e) {
      logger.log(Level.WARN, "Couldn't make a screenshot.\n" + e.getMessage());
    }
  }
}
