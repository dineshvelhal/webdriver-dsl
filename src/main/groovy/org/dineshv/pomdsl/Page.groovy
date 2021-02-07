package org.dineshv.pomdsl

import org.dineshv.pomdsl.exceptions.InvalidStateException
import org.dineshv.pomdsl.exceptions.UnknownDestinationException
import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Page {
  ////////////////////// CONSTANTS ////////////////////////////
  final inside = 0
  final until = 1
  final parentFrame = 2
  final mainPage = 3
  final back = 'back'
  final forward = 'forward'

  final visible = 0
  final invisible = 1
  final clickable = 2
  final selected = 3
  //final stale = 4


  /////////////////////////////////////////////////////////////

  /**
   *
   */
  def WebDriver driver

  /**
   *
   */
  def WebDriverWait defaultWait

  /**
   *
   */
  def staleElementRetry

  /**
   *
   * @param driver
   * @param wait
   * @param staleElementRetry
   */
  Page(WebDriver driver, wait = Constants.DEFAULT_WAIT, staleElementRetry = Constants.STALE_ELEMENT_RETRY) {
    this.driver = driver
    this.defaultWait = new WebDriverWait(driver, wait)
    this.staleElementRetry = staleElementRetry
  }


  // fluent way to type something
  // example - type 'hello' into text field
  /**
   *
   * @param input
   * @return
   */
  def type(input) {
    [into: { locator -> sendKeys(locator, input) }]
  }

  def move(Map<String, By> m) {
    WebElement frame = findElement(m.get('into'))


    driver.switchTo().frame(frame)
  }

  /**
   *
   * @param a
   * @return
   */
  def backTo(int a) {
    if (a == parentFrame) {
      driver.switchTo().parentFrame()
    } else if (a == mainPage) {
      driver.switchTo().defaultContent()
    }

  }

  // TODO - select from dropdown
  // TODO - select from list
  // TODO - select check boxes
  // TODO - select radio buttons
  // TODO - switch to windows

  // TODO - wait for element clickability
  // TODO - wait for element invisibility
  // TODO - wait for element visibility


  /**
   *
   * @param by
   * @return
   */
  def waitFor(By by) {
    [toBe: { state -> waitForState(by, state) }]
  }

  /**
   *
   * @param by
   * @param state
   * @return
   */
  def waitForState(By by, int state) {
    switch (state) {
      case visible:
        defaultWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
        break
      case invisible:
        defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(by))
        break
      case clickable:
        defaultWait.until(ExpectedConditions.elementToBeClickable(by))
        break
      case selected:
        defaultWait.until(ExpectedConditions.elementToBeSelected(by))
        break
        /*case stale:
          defaultWait.until(ExpectedConditions.stalenessOf(findElement(by)))*/
        break
      default:
        throw new InvalidStateException("Invalid state [${state}]for the element: [${by}]")
    }
  }


  // TODO - direct attribute access through the By objects

  /**
   *
   */
  Closure alert = {
    boolean alertPresent = defaultWait.until(ExpectedConditions.alertIsPresent())

    if (alertPresent) {
      return driver.switchTo().alert()
    }
  }

  /**
   *
   * @param c
   * @return
   */
  def accept(Closure c) {
    c.call().accept()
  }

  /**
   *
   * @param c
   * @return
   */
  def dismiss(Closure c) {
    c.call().dismiss()
  }


  /**
   *
   * @return
   */
  String title() {
    driver.getTitle()
  }

  /**
   *
   * @return
   */
  String url() {
    driver.currentUrl
  }

  /**
   *
   * @return
   */
  String source() {
    driver.pageSource
  }

  /**
   *
   * @return
   */
  String windowHandle() {
    driver.windowHandle
  }

  /**
   *
   * @param by
   * @return
   */
  def WebElement findElement(By by) {
    return defaultWait.until(ExpectedConditions.elementToBeClickable(by))
  }

  /**
   *
   * @param by
   */
  def click(By by) {
    for (int i in 1..staleElementRetry) {
      try {
        findElement(by).click()
        break
      } catch (StaleElementReferenceException e) {
        // Do nothing. just make the loop to continue to iterate
        println e.getMessage()
      }
    }
  }

  /**
   *
   * @param by
   * @param input
   */
  def sendKeys(By by, String input) {
    for (int i in 1..staleElementRetry) {
      try {
        findElement(by).sendKeys(input)
        break
      } catch (StaleElementReferenceException e) {
        // Do nothing. just make the loop to continue to iterate
        println e.getMessage()
      }
    }
  }


  /**
   *
   * @param url
   * @return
   */
  def navigateTo(String url) {
    driver.navigate().to(url)
  }

  /**
   *
   * @param dir
   * @return
   */
  def navigate(String dir) {
    if (dir == 'back') {
      driver.navigate().back()
    } else if (dir == 'forward') {
      driver.navigate().forward()
    } else {
      throw new UnknownDestinationException('Unknown destination: ' + dir)
    }
  }

  /**
   * Adds the extra features to the By locator
   * @param webDriver
   * @param metaProperties
   */
  /*void addFeaturesToLocators(WebDriver webDriver, List<MetaProperty> metaProperties) {
    metaProperties.each {
      if(it.type.name == 'org.openqa.selenium.By') {
        println "${it.name}"
        println "The locator is ${this.loginButton}"
        this[it.name].metaClass.newProperty = 'newProperty'
      }
    }
  }*/

  void initialize(Page page) {
    WebDriver driver = page.driver
    MetaClass mc = page.metaClass

    mc.properties.each {
      if(it.type.name == 'org.openqa.selenium.By') {
        //println "${it.name}"

        page[it.name].metaClass.propertyMissing = {String property ->
          if (property == '$') {
            return findElement(page[it.name])
          } else if (property == 'text') {
            return findElement(page[it.name]).getText()
          } else {
            return findElement(page[it.name]).getAttribute(property)
          }

          def webElem = findElement(page[it.name])
          switch (property) {
            case '$':
              return webElem
            case 'text':
              return webElem.getText()
            case 'tag':
              return webElem.getTagName()
            case 'location':
              return webElem.getLocation()
            case 'size':
              return webElem.getSize()
            case 'rectangle':
              return webElem.getRect()
            default: // treat every other missing property as attribute
              return webElem.getAttribute(property)
          }
        }
      }
    }
  }
}
