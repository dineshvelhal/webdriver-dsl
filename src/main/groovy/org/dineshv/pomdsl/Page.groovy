package org.dineshv.pomdsl

import groovy.transform.ToString
import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.exceptions.InvalidStateException
import org.dineshv.pomdsl.exceptions.UnknownDestinationException
import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait

@Log4j2
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
      log.info "Page constructed [driver=${driver}], [default wait=${wait}], [staleElementRetry=${staleElementRetry}]"
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
      [into: { locator ->
         log.info "typing [${input}] into [${locator}] "
         sendKeys(locator, input)
      }]
   }

   def move(Map<String, By> m) {
      log.info("move into frame ${m.get('into')}")

      defaultWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(m.get('into')))

   }

   /**
    *
    * @param a
    * @return
    */
   def backTo(int a) {
      if (a == parentFrame) {
         log.info("move back to parent frame")

         driver.switchTo().parentFrame()
      } else if (a == mainPage) {
         log.info("move back to main page (default content)")

         driver.switchTo().defaultContent()
      }

   }

   // TODO - select from dropdown
   // syntax select index:1 from dropdown
   def select(Map<String, Object> item) {
      [from : { locator ->
         Select se = new Select(findElement(locator))

         if (item.containsKey('index')) {
            int index = item.get('index')

            log.info("Selecting index ${index} from ${locator}")

            se.selectByIndex(index)
         } else if (item.containsKey('value')) {
            String value = item.get('value')

            log.info("Selecting value ${value} from ${locator}")

            se.selectByValue(value)
         } else if (item.containsKey('visibleText')) {
            String visibleText = item.get('visibleText')

            log.info("Selecting value ${visibleText} from ${locator}")

            se.selectByVisibleText(visibleText)
         }
      }]
   }

   // TODO - select by index
   // TODO - select by value
   // TODO - select by visible text
   // TODO - select multiple items by index, value or visible text
   // TODO - get all items
   // TODO - get first selected item
   // TODO - get all selected items
   // TODO - unselect all
   // TODO - unselect by index, value or visible text
   // https://www.toolsqa.com/selenium-webdriver/dropdown-in-selenium/

   // TODO - select from list
   // TODO - select check boxes
   // TODO - select radio buttons


   /**
    *
    * @param by
    * @return
    */
   def waitFor(By by) {
      [toBe: { state ->

         log.info "Waiting for the state [${state}] of [${by}]"

         waitForState(by, state)
      }]
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
      log.info "findElement([${by})]"

      return defaultWait.until(ExpectedConditions.elementToBeClickable(by))
   }

   /**
    *
    * @param by
    */
   def click(By by) {
      log.info "click(${by})"

      for (int i in 1..staleElementRetry) {
         try {
            log.info "click retry count: [${i}]"

            findElement(by).click()
            break
         } catch (StaleElementReferenceException e) {
            // Do nothing. just make the loop to continue to iterate

            log.error "Element [${by}] is stale. Retrying the click..."
         }
      }
   }

   /**
    *
    * @param by
    * @param input
    */
   def sendKeys(By by, String input) {
      log.info "sendKeys(${by}, ${input})"

      for (int i in 1..staleElementRetry) {
         try {

            log.info "sendKeys retry count: [${i}]"

            findElement(by).sendKeys(input)
            break
         } catch (StaleElementReferenceException e) {
            // Do nothing. just make the loop to continue to iterate

            log.error "Element [${by}] is stale. Retrying the sendKeys..."
         }
      }
   }


   /**
    *
    * @param url
    * @return
    */
   def navigateTo(String url) {
      log.info "navigateTo(${url})"

      driver.navigate().to(url)
   }

   /**
    *
    * @param dir
    * @return
    */
   def navigate(String dir) {
      log.info "navigate(${dir})"

      if (dir == 'back') {
         log.info "Navigating back..."

         driver.navigate().back()
      } else if (dir == 'forward') {
         log.info "Navigating forward..."

         driver.navigate().forward()
      } else {
         log.error "Unknown navigation direction"

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

   /**
    * Adds the extra properties to all locators (By) present as fields in the Page
    * This method will be called from the child classes of the Page class
    * @param page
    */
   void initialize(Page page) {

      log.info "initialize(${page})"

      WebDriver driver = page.driver
      MetaClass mc = page.metaClass

      mc.properties.each {
         if (it.type.name == 'org.openqa.selenium.By') {

            log.info "Adding extra properties for [${page[it.name]}]"

            page[it.name].metaClass.propertyMissing = { String property ->
               /*if (property == '$') {
                 return findElement(page[it.name])
               } else if (property == 'text') {
                 return findElement(page[it.name]).getText()
               } else {
                 return findElement(page[it.name]).getAttribute(property)
               }*/

               def webElem = findElement(page[it.name])

               log.info "Missing Locator Property = ${property}"


               switch (property) {
                  case '$':
                     log.info "Property returns WebElement ${webElem}"
                     return webElem
                  case 'text':
                     log.info "Property returns WebElement.getText()"
                     return webElem.getText()
                  case 'tag':
                     log.info "Property returns WebElement.getTagName()"
                     return webElem.getTagName()
                  case 'location':
                     log.info "Property returns WebElement.getLocation()"
                     return webElem.getLocation()
                  case 'size':
                     log.info "Property returns WebElement.getSize()"
                     return webElem.getSize()
                  case 'rectangle':
                     log.info "Property returns WebElement.getRect()"
                     return webElem.getRect()
                  default: // treat every other missing property as attribute
                     log.info "Property returns WebElement.getAttribute(\"${property}\")"
                     return webElem.getAttribute(property)
               }
            }
         }
      }
   }

   /**
    * Provides a clean way to switch into a frame and out of it using closure
    * @param frame
    * @param body
    * @return
    */
   def frame(By frame, Closure body) {
      move into: frame

      try {
         body.call()
      } finally {
         // to ensure control goes back to parent frame irrespective of exceptions
         backTo parentFrame
      }
   }
}
