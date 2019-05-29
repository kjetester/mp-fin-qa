package com.moex.mpfin.qa.pages;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static com.moex.mpfin.qa.utils.DriverFactory.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class AbstractPage {

  private static JavascriptExecutor jsExecutor = (JavascriptExecutor)getDriver();
  protected static Actions actions = new Actions(getDriver());
  protected static SoftAssertions softly = new SoftAssertions();

  private static final Integer TIMEOUT = 30;

  public abstract Object checkIfPageOpens();

  protected void checkIfPageOpens(String pageUniqueText) {
    Assertions.assertThat(getDriver().getPageSource().contains(pageUniqueText))
        .as("Checking presence of the text: '" + pageUniqueText + "'.").isTrue();
  }

  protected static List<WebElement> waitForAllElementsAreVisible(List<WebElement> elements) {
    new WebDriverWait(getDriver(), TIMEOUT).until(visibilityOfAllElements(elements));
    return elements;
  }

  protected static WebElement waitForElementIsVisible(WebElement element) {
    new WebDriverWait(getDriver(), TIMEOUT).until(visibilityOf(element));
    return element;
  }

  protected static WebElement waitForElementNotVisible(WebElement element) {
    new WebDriverWait(getDriver(), TIMEOUT).until(invisibilityOf(element));
    return element;
  }

  protected static WebElement waitForElementToBeClickable(WebElement element) {
    new WebDriverWait(getDriver(), TIMEOUT).until(elementToBeClickable(element));
    return element;
  }

  protected static WebElement scrollTo(WebElement element) {
    try {
      jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
      Thread.sleep(500);
//      takeScreenshotWithFrame(element);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return element;
  }

  protected WebElement clickViaJavaScriptExecutor(WebElement element) {
    scrollTo(element);
    jsExecutor.executeScript("arguments[0].click();", element);
    return element;
  }
}
