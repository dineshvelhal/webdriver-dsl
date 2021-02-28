package org.dineshv.pomdsl


import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.exceptions.InvalidOptionException
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

   final VISIBLE = 'VISIBLE'
   final INVISIBLE = 'INVISIBLE'
   final CLICKABLE = 'CLICKABLE'
   final SELECTED = 'SELECTED'

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

   def setDefaultWait(int waitInSeconds) {
      log.info("Setting defaultWait to [$waitInSeconds] seconds")
      defaultWait = new WebDriverWait(driver, waitInSeconds)
   }

   def refreshPage() {
      log.info("Refreshing page")

      driver.navigate().refresh()
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
         log.info "typing [${input}] into [${locator}]"

         writeText(locator, input)
      }]
   }



   // https://www.toolsqa.com/selenium-webdriver/dropdown-in-selenium/
   def select(Map<String, Object> item) {
      Map.Entry<String, Object> entry = item.entrySet().iterator().next()

      String key = entry.key
      Object value = entry.value

      [from: { locator ->
         Select se = new Select(findElement(locator))

         log.info("Selecting [${key}=${value}] from dropdown/list [${locator}]")

         if (value instanceof List) {
            try {
               if (locator.multiple) {
                  log.info "Dropdown/list [${locator}] is a multi-select list"
               } else {
                  log.warn "You are trying to select multiple values from single-select list/dropdown [${locator}]. Only last option will be finally selected"
               }
            } catch (MissingPropertyException e) {
               log.warn "You are trying to select multiple values from single-select list/dropdown [${locator}]. Only last option will be finally selected"
            }

            switch (key) {
               case 'value':
                  //se.selectByValue(value)
                  value.each { se.selectByValue(it) }
                  break
               case 'visibleText':
                  value.each { se.selectByVisibleText(it) }
                  //se.selectByVisibleText(value)
                  break
               case 'index':
                  value.each { se.selectByIndex(it) }
                  // se.selectByIndex(value)
                  break
               default:
                  throw new org.dineshv.pomdsl.exceptions.InvalidOptionException("Invalid option [${key}] when selecting value from dropdown [${locator}]")
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
                  throw new org.dineshv.pomdsl.exceptions.InvalidOptionException("Invalid option [${key}] when selecting value from dropdown/list [${locator}]")
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

         if (value instanceof List) {

            switch (key) {
               case 'value':
                  //se.selectByValue(value)
                  value.each { se.deselectByValue(it) }
                  break
               case 'visibleText':
                  value.each { se.deselectByVisibleText(it) }
                  //se.selectByVisibleText(value)
                  break
               case 'index':
                  value.each { se.deselectByIndex(it) }
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
      if (all == 'all') {
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

      if (key != "options") {
         throw new InvalidOptionException("Invalid option [$key] while getting the options from list/dropdown")
      }

      [from: { locator ->
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

      if (locator.selected) {
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

      if (type == "checkbox") {
         if (locator.selected) {
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
   def waitForState(By by, String state) {
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
            throw new org.dineshv.pomdsl.exceptions.InvalidStateException("Invalid state [${state}]for the element: [${by}]")
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
      log.info 'Accepting alert'
      c.call().accept()
   }

   /**
    *
    * @param c
    * @return
    */
   def dismiss(Closure c) {
      log.info 'Dismissing alert'
      c.call().dismiss()
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
   def writeText(By by, String input) {
      log.info "sendKeys [locator=${by}], text=[${input}]"

      for (int i in 1..staleElementRetry) {
         try {
            log.info "sendKeys retry count: [${i}]"

            WebElement txt = findElement(by)
            txt.sendKeys('')
            txt.sendKeys(input)
            break
         } catch (StaleElementReferenceException e) {
            // Do nothing. just make the loop to continue to iterate

            log.error "Element [${by}] is stale. Retrying the sendKeys..."
         }
      }
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
         log.error "Navigating to url [$dir]"
         driver.navigate().to(dir)
         // throw new UnknownDestinationException('Unknown destination: ' + dir)
      }
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
   def back_to(int a) {
      if (a == PARENT_FRAME) {
         log.info("move back to parent frame")

         driver.switchTo().parentFrame()
      } else if (a == MAIN_PAGE) {
         log.info("move back to main page (default content)")

         driver.switchTo().defaultContent()
      }

   }

   /**
    * Provides a clean way to switch into a frame and out of it using closure
    * @param frame
    * @param body
    * @return
    */
   def frame(def frame, Closure body) {
      move into: frame

      try {
         body.call()
      } finally {
         // to ensure control goes back to parent frame irrespective of exceptions
         back_to PARENT_FRAME
      }
   }


   /////////// Locator wrapper methods //////////////////////////////
   By byClassName(String className) {
      log.info("returning locator for className=[$className]")

      By by = By.className(className)
      addProperties(by)
      return by
   }

   By byCssSelector(String cssSelector) {
      log.info("returning locator for cssSelector=[$cssSelector]")

      By by = By.cssSelector(cssSelector)
      addProperties(by)
      return by
   }

   By byId(String id) {
      log.info("returning locator for Id=[$id]")

      By by = By.id(id)
      addProperties(by)
      return by
   }

   By byLinkText(String linkText) {
      log.info("returning locator for linkText=[$linkText]")

      By by = By.linkText(linkText)
      addProperties(by)
      return by
   }

   By byName(String name) {
      log.info("returning locator for name=[$name]")

      By by = By.name(name)
      addProperties(by)
      return by
   }

   By byPartialLinkText(String partialLinkText) {
      log.info("returning locator for partialLinkText=[$partialLinkText]")

      By by = By.partialLinkText(partialLinkText)
      addProperties(by)
      return by
   }

   By byTagName(String tagName) {
      log.info("returning locator for tagName=[$tagName]")

      By by = By.tagName(tagName)
      addProperties(by)
      return by
   }

   By byXpath(String xpath) {
      log.info("returning locator for xpath=[$xpath]")

      By by = By.xpath(xpath)
      addProperties(by)
      return by
   }

   def addProperties(By by) {
      log.info("Adding new properties to the locator [$by]")

      by.metaClass.propertyMissing = { String property ->

         def webElem

         log.info "Missing property [${property}]"

         switch (property) {
            case '$':
               log.info "Missing property [${property}] returns [WebElement=${webElem}]"
               webElem = findElement(by)
               return webElem
            case 'text':
               log.info "Missing property [${property}] returns WebElement.getText()"
               webElem = driver.findElement(by)
               return webElem.getText()
            case 'tag':
               log.info "Missing property [${property}] returns WebElement.getTagName()"
               webElem = driver.findElement(by)
               return webElem.getTagName()
            case 'location':
               log.info "Missing property [${property}] returns WebElement.getLocation()"
               webElem = driver.findElement(by)
               return webElem.getLocation()
            case 'size':
               log.info "Missing property [${property}] returns WebElement.getSize()"
               webElem = driver.findElement(by)
               return webElem.getSize()
            case 'rectangle':
               log.info "Missing property [${property}] returns WebElement.getRect()"
               webElem = driver.findElement(by)
               return webElem.getRect()
            case 'selected':
               log.info "Missing property [${property}] returns WebElement.selected"
               webElem = driver.findElement(by)
               return webElem.selected
            case 'enabled':
               log.info "Missing property [${property}] returns WebElement.enabled"
               webElem = driver.findElement(by)
               return webElem.enabled
            case 'displayed':
               log.info "Missing property [${property}] returns WebElement.visible"
               webElem = driver.findElement(by)
               return webElem.displayed
            default: // treat every other missing property as attribute
               log.info "Missing property [${property}] returns WebElement.getAttribute(\"${property}\")"
               webElem = driver.findElement(by)
               String attr = webElem.getAttribute(property)

               if (attr == null) {
                  log.error "Most probably the attribute [${property}] is missing in the Web Element [${webElem}]"
               }
               return attr
         }
      }
   }

   def propertyMissing(String name) {
      log.info "Missing property [$name] of ${this.class.name} being accessed"

      String property;
      switch (name) {
         case 'title':
            property = driver.title
            break
         case 'url':
            property = driver.currentUrl
            break
         case 'source':
            property = driver.pageSource
            break
         case 'windowHandle':
            property = driver.windowHandle
            break
         case 'windowHandles':
            property = driver.windowHandles
            break
         default:
            throw new org.dineshv.pomdsl.exceptions.InvalidPropertyException("property [$name] is not supported by ${this.class.name}")
      }

      log.info "Property [$name] = [$property]"
      return property
   }
}
