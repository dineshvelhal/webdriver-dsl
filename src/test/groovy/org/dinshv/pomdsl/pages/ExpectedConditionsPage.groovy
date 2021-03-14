package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.apache.commons.compress.archivers.sevenz.CLI
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

@Log4j2
class ExpectedConditionsPage extends Page {
   By minWait = byId('min_wait')
   By maxWait = byId('max_wait')

   public ExpectedConditionsPage(WebDriver driver) {
      super(driver)
   }

   def setMinMaxWait(int min, int max) {
      clearAndType min.toString() into minWait
      clearAndType max.toString() into maxWait
   }

   def visibilityFlow() {
      By triggerButton = byId('visibility_trigger')
      By clickMeButton = byId('visibility_target')

      refreshPage()

      setMinMaxWait(2, 5)

      click triggerButton
      waitFor clickMeButton toBe VISIBLE
      click clickMeButton

      assert clickMeButton.displayed == true
   }

   def invisibilityFlow() {
      By spinner = byId('invisibility_target')
      By triggerButton = byId('invisibility_trigger')
      By message = byId('spinner_gone')

      refreshPage()
      setMinMaxWait(2, 5)

      click triggerButton
      waitFor spinner toBe INVISIBLE

      assert message.displayed == true
      assert spinner.displayed == false
   }

   def enabledFlow() {
      By triggerButton = byId('enabled_trigger')
      By disabledButton = byId('enabled_target')

      refreshPage()
      setMinMaxWait(2, 5)

      assert disabledButton.enabled == false

      click triggerButton
      waitFor disabledButton toBe CLICKABLE
      click disabledButton

      assert disabledButton.enabled == true
   }

   def timeoutBlockFlow() {
      By triggerButton = byId('enabled_trigger')
      By disabledButton = byId('enabled_target')

      refreshPage()
      setMinMaxWait(2, 5)

      // Following code passes with default timeOut of 5 seconds
      assert disabledButton.enabled == false
      click triggerButton
      waitFor disabledButton toBe CLICKABLE
      click disabledButton
      assert disabledButton.enabled == true

      refreshPage()
      setMinMaxWait(4, 7)

      // Following code should pass with the block-level timeOut of 15 seconds
      timeOut(7.seconds) {
         assert disabledButton.enabled == false
         click triggerButton
         waitFor disabledButton toBe CLICKABLE
         click disabledButton
         assert disabledButton.enabled == true
      }

      // Following code should now fail because timeOut value is again back to original value of 5 seconds
      boolean exceptionOccurred = false

      try {
         refreshPage()
         setMinMaxWait(10, 15)

         assert disabledButton.enabled == false
         click triggerButton
         waitFor disabledButton toBe CLICKABLE
         click disabledButton
         assert disabledButton.enabled == true
      } catch(TimeoutException e) {
         exceptionOccurred = true
      }

      assert exceptionOccurred
   }
}
