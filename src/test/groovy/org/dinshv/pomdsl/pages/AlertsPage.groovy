package org.dinshv.pomdsl.pages

import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class AlertsPage extends Page {

   By triggerButton = byId('alert_trigger')
   By promptButton = byId('prompt_trigger')
   By alertAcceptedBadge = byId('alert_handled_badge')
   By confirmOkBadge = byId('confirm_ok_badge')
   By confirmCancelBadge = byId('confirm_cancelled_badge')
   By maxWaitField = byId('max_wait')

   AlertsPage(WebDriver driver) {
      super(driver, 10)
   }

   def acceptAlert() {
      refreshPage()
      setDefaultWait(5)
      click triggerButton
      accept alert

      assert alertAcceptedBadge.displayed == true
   }

   def acceptPrompt() {
      refreshPage()
      setDefaultWait(5)
      click promptButton
      accept alert

      assert confirmOkBadge.displayed == true
   }

   def dismissPrompt() {
      refreshPage()
      setDefaultWait(5)
      click promptButton
      dismiss alert

      assert confirmCancelBadge.displayed == true
   }

   def alertTimeOut() {
      println "Start Time Out"

      refreshPage()

      click promptButton

      setDefaultWait(1)

      accept alert

      // assert confirmOkBadge.displayed == true
   }
}
