package org.dineshv.pomdsl.unittests

import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.AlertsPage
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.UnhandledAlertException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class AlertTests {
   private WebDriver driver
   AlertsPage alertsPage
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url
      driver.manage().window().maximize()
      alertsPage = new AlertsPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         Thread.sleep(5000)
         driver.quit()
      }
   }


   @Test
   void alert_test() {
      alertsPage.acceptAlert()
   }

   @Test
   void accept_prompt_test() {
      alertsPage.acceptPrompt()
   }

   @Test
   void dismiss_prompt_test() {
      alertsPage.dismissPrompt()
   }

   @Test()
   void alert_timeout_test() {
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
