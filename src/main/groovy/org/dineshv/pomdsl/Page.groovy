package org.dineshv.pomdsl


import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.exceptions.InvalidOptionException
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
   final PARENT_FRAME = 2
   final MAIN_PAGE = 3
   final BACK = 'back'
   final FORWARD = 'forward'

   final VISIBLE = 0
   final INVISIBLE = 1
   final CLICKABLE = 2
   final SELECTED = 3

   // dropdown / list
   final ALL = 'all'
   final FIRST_SELECTED = 'first_selected'
   final ALL_SELECTED = 'all_selected'
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
      log.info("move into frame [${m.get('into')}]")

      defaultWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(m.get('into')))
   }

   /**
    *
    * @param a
    * @return
    */
   def backTo(int a) {
      if (a == PARENT_FRAME) {
         log.info("move back to parent frame")

         driver.switchTo().parentFrame()
      } else if (a == MAIN_PAGE) {
         log.info("move back to main page (default content)")

         driver.switchTo().defaultContent()
      }

   }

   // https://www.toolsqa.com/selenium-webdriver/dropdown-in-selenium/
   def select(Map<String, Object> item) {
      Map.Entry<String, Object> entry = item.entrySet().iterator().next()

      String key = entry.key
      Object value = entry.value

      [from: { locator ->
         Select se = new Select(findElement(locator))

         log.info("Selecting [${key}=${value}] from dropdown/list [${locator}]")

         if(value instanceof List) {
            try {
               if (locator.multiple) {
                  log.info "Dropdown/list [${locator}] is a multi-select list"
               } else {
                  log.warn "You are trying to select multiple values from single-select list/dropdown [${locator}]. Only last option will be finally selected"
               }
            } catch(MissingPropertyException e) {
               log.warn "You are trying to select multiple values from single-select list/dropdown [${locator}]. Only last option will be finally selected"
            }

            switch (key) {
               case 'value':
                  //se.selectByValue(value)
                  value.each {se.selectByValue(it)}
                  break
               case 'visibleText':
                  value.each {se.selectByVisibleText(it)}
                  //se.selectByVisibleText(value)
                  break
               case 'index':
                  value.each {se.selectByIndex(it)}
                  // se.selectByIndex(value)
                  break
               default:
                  throw new InvalidOptionException("Invalid option [${key}] when selecting value from dropdown [${locator}]")
            }
         } else {
            // single select
            switch (key) {
               case 'value':
                  se.selectByValue(value)
                  break
               case 'visibleText':
                  se.selectByVisibleText(value)
                  break
               case 'index':
                  se.selectByIndex(value)
                  break
               default:
                  throw new InvalidOptionException("Invalid option [${key}] when selecting value from dropdown/list [${locator}]")
            }
         }
      }]
   }

   def deselect(Map<String, Object> option) {
      Map.Entry<String, Object> entry = option.entrySet().iterator().next()

      String key = entry.key
      Object value = entry.value

      [from: { locator ->
         Select se = new Select(findElement(locator))

         log.info("Deselecting [${key}=${value}] from dropdown/list [${locator}]")

         if(value instanceof List) {

            switch (key) {
               case 'value':
                  //se.selectByValue(value)
                  value.each {se.deselectByValue(it)}
                  break
               case 'visibleText':
                  value.each {se.deselectByVisibleText(it)}
                  //se.selectByVisibleText(value)
                  break
               case 'index':
                  value.each {se.deselectByIndex(it)}
                  // se.selectByIndex(value)
                  break
               default:
                  throw new InvalidOptionException("Invalid option [${key}] when deselecting value from list [${locator}]")
            }
         } else {
            // single select
            switch (key) {
               case 'value':
                  se.deselectByValue(value)
                  break
               case 'visibleText':
                  se.deselectByVisibleText(value)
                  break
               case 'index':
                  se.deselectByIndex(value)
                  break
               default:
                  throw new InvalidOptionException("Invalid option [${key}] when deselecting value from list [${locator}]")
            }
         }
      }]
   }


   def deselect(String all) {
      if(all == 'all') {
         [from: { locator ->
            log.info("De-selecting all values from list [${locator}]")

            Select se = new Select(findElement(locator))
            se.deselectAll()
         }]
      } else {
         // invalid option
         throw new InvalidOptionException("Invalid option [${all}] while de-selecting values from the list")
      }
   }

   // TODO - get all items
   // TODO - get first selected item
   // TODO - get all selected items
   def get(Map<String, String> options) {
      Map.Entry<String, Object> entry = options.entrySet().iterator().next()

      String key = entry.key
      Object value = entry.value

      if(key != "options") {
         throw new InvalidOptionException("Invalid option [$key] while getting the options from list/dropdown")
      }

      [from: {locator ->
         Select se = new Select(findElement(locator))

         log.info("Returning [${value}] options from dropdown/list [${locator}]")

         switch (value) {
            case ALL:
               return se.options
               break
            case FIRST_SELECTED:
               return se.firstSelectedOption
               break
            case ALL_SELECTED:
               return se.allSelectedOptions
               break
         }
      }]
   }



   // TODO - select check boxes
   // TODO - select radio buttons
   def check(By locator) {
      String type = locator.type

      log.info "Checking the $type [$locator]"

      if(locator.selected) {
         log.warn("$type [$locator] is already checked")
         //do nothing
      } else {
         click locator
      }
   }

   // TODO - Uncheck checkbox
   def uncheck(By locator) {
      String type = locator.type

      log.info "Unchecking the $type [$locator]"

      if(type == "checkbox") {
         if(locator.selected) {
            click locator
         } else {
            log.warn("$type [$locator] is already unchecked")
            //do nothing
         }
      } else {
         log.warn("$type cannot be unchecked")
         // TODO - need to decide if error must be thrown here or not
      }
   }

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
         case VISIBLE:
            defaultWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
            break
         case INVISIBLE:
            defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(by))
            break
         case CLICKABLE:
            defaultWait.until(ExpectedConditions.elementToBeClickable(by))
            break
         case SELECTED:
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
      log.info "findElement [${by}]"

      return defaultWait.until(ExpectedConditions.elementToBeClickable(by))
   }

   /**
    *
    * @param by
    */
   def click(By by) {
      log.info "click [${by}]"

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
      log.info "sendKeys [${by}], [${input}]"

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
      log.info "navigateTo [${url}]"

      driver.navigate().to(url)
   }

   /**
    *
    * @param dir
    * @return
    */
   def navigate(String dir) {
      log.info "navigate [${dir}]"

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

      log.info "initialize [${page}]"

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

               log.info "Missing property [${property}]"


               switch (property) {
                  case '$':
                     log.info "Missing property [${property}] returns [WebElement=${webElem}]"
                     return webElem
                  case 'text':
                     log.info "Missing property [${property}] returns WebElement.getText()"
                     return webElem.getText()
                  case 'tag':
                     log.info "Missing property [${property}] returns WebElement.getTagName()"
                     return webElem.getTagName()
                  case 'location':
                     log.info "Missing property [${property}] returns WebElement.getLocation()"
                     return webElem.getLocation()
                  case 'size':
                     log.info "Missing property [${property}] returns WebElement.getSize()"
                     return webElem.getSize()
                  case 'rectangle':
                     log.info "Missing property [${property}] returns WebElement.getRect()"
                     return webElem.getRect()
                  case 'selected':
                     log.info "Missing property [${property}] returns WebElement.selected"
                     return webElem.selected
                  case 'enabled':
                     log.info "Missing property [${property}] returns WebElement.enabled"
                     return webElem.enabled
                  case 'displayed':
                     log.info "Missing property [${property}] returns WebElement.visible"
                     return webElem.displayed
                  default: // treat every other missing property as attribute
                     log.info "Missing property [${property}] returns WebElement.getAttribute(\"${property}\")"

                     String attr = webElem.getAttribute(property)

                     if(attr == null) {
                        log.error "Most probably the attribute [${property}] is missing in the Web Element [${webElem}]"
                     }

                     return attr
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
         backTo PARENT_FRAME
      }
   }
}
