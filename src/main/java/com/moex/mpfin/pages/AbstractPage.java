package com.moex.mpfin.pages;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.moex.mpfin.utils.WebDriverSingleton.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class AbstractPage {

  private Logger logger = LogManager.getLogger(AbstractPage.class.getSimpleName());

  private static final Integer TIMEOUT = 30;

  public abstract Object checkIfPageOpens();

  protected void checkIfPageOpens(String pageUniqueText) {
    Assertions.assertThat(getDriver().getPageSource().contains(pageUniqueText))
        .as("Checking presence of the text: '" + pageUniqueText + "'.").isTrue();
  }

  protected List<WebElement> waitForAllElementsAreVisible(List<WebElement> elements) {
    logger.log(Level.DEBUG, "Waiting for the entire list of the WebElements to be visible.");
    new WebDriverWait(getDriver(), TIMEOUT).until(visibilityOfAllElements(elements));
    return elements;
  }

  protected WebElement waitForElementIsVisible(WebElement element) {
    logger.log(Level.DEBUG, "Waiting for the WebElement to be visible.");
    new WebDriverWait(getDriver(), TIMEOUT).until(visibilityOf(element));
    return element;
  }

  protected WebElement waitForElementNotVisible(WebElement element) {
    logger.log(Level.DEBUG, "Waiting for the WebElement to be invisible.");
    new WebDriverWait(getDriver(), TIMEOUT).until(invisibilityOf(element));
    return element;
  }

  protected WebElement waitForElementToBeClickable(WebElement element) {
    logger.log(Level.DEBUG, "Waiting for the WebElement to be clickable.");
    new WebDriverWait(getDriver(), TIMEOUT).until(elementToBeClickable(element));
    return element;
  }

  protected WebElement scrollTo(WebElement element) {
    try {
      logger.log(Level.DEBUG, "Scrolling to the WebElement with the JavaScriptExecutor.");
      ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
      Thread.sleep(500);
//      takeScreenshotWithFrame(element);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return element;
  }

  protected WebElement clickViaJavaScriptExecutor(WebElement element) {
    scrollTo(element);
    logger.log(Level.DEBUG, "Clicking to WebElement with JavaScriptExecutor.");
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    return element;
  }
}
