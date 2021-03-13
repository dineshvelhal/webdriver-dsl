package org.dineshv.pomdsl.unittests


import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.AlertsPage
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.UnhandledAlertException
import org.testng.annotations.Test

class AlertTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"
   AlertsPage alertsPage

   @Test
   void alert_test() {
      open url
      alertsPage = new AlertsPage(driver)

      alertsPage.acceptAlert()
   }

   @Test
   void accept_prompt_test() {
      open url
      alertsPage = new AlertsPage(driver)

      alertsPage.acceptPrompt()
   }

   @Test
   void dismiss_prompt_test() {
      open url
      alertsPage = new AlertsPage(driver)

      alertsPage.dismissPrompt()
   }

   @Test()
   void alert_timeout_test() {
      open url
      alertsPage = new AlertsPage(driver)

      boolean exceptionOccured = false

      try {
         alertsPage.alertTimeOut()
      } catch (TimeoutException e) {
         exceptionOccured = true
      } catch (UnhandledAlertException e) {
         exceptionOccured = true
      }

      assert exceptionOccured == true
   }
}
