package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.apache.commons.compress.archivers.sevenz.CLI
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

@Log4j2
class ExpectedConditionsPage extends Page {

   public ExpectedConditionsPage(WebDriver driver) {
      super(driver)
   }


   def visibilityFlow() {
      By triggerButton = byId('visibility_trigger')
      By clickMeButton = byId('visibility_target')

      refreshPage()

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

      click triggerButton
      waitFor spinner toBe INVISIBLE

      assert message.displayed == true
      assert spinner.displayed == false
   }

   def enabledFlow() {
      By triggerButton = byId('enabled_trigger')
      By disabledButton = byId('enabled_target')

      refreshPage()

      assert disabledButton.enabled == false

      click triggerButton
      waitFor disabledButton toBe CLICKABLE
      click disabledButton

      assert disabledButton.enabled == true
   }
}
