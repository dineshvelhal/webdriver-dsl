package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.ExpectedConditionsPage
import org.testng.annotations.Test

@Log4j2
class ExpectedConditionsTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"
   ExpectedConditionsPage expectedConditionsPage

   @Test
   void visibility_test() {
      open url
      expectedConditionsPage = new ExpectedConditionsPage(driver)

      expectedConditionsPage.visibilityFlow()
   }

   @Test
   void invisibility_test() {
      open url
      expectedConditionsPage = new ExpectedConditionsPage(driver)

      expectedConditionsPage.invisibilityFlow()
   }

   @Test
   void enabled_test() {
      open url
      expectedConditionsPage = new ExpectedConditionsPage(driver)

      expectedConditionsPage.enabledFlow()
   }
}
