package com.moex.mpfin.qa.reporting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;

public class ReportTools {

  private static final Logger LOGGER = Logger.getLogger(ReportTools.class.getSimpleName());
  /**
   * Takes a screenshot.
   * @param element an element to highlight
   */
  public static WebElement takeScreenshotWithFrame(WebElement element) {
    try {
      ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
      takeScreenshot();
    } catch (Exception e) {
      LOGGER.warning(e.getMessage());
    } finally {
      ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border=''", element);
    }
    return element;
  }

  public static void takeScreenshot() throws Exception {
    File scr = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
    String filename =  new SimpleDateFormat("yyyyMMddHHmmssSSS'.png'").format(new Date());
    File destination = new File("target/screenshots/" + filename);
    FileUtils.copyFile(scr, destination);
  }


}
