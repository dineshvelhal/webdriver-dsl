package org.dineshv.pomdsl.unittests


import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.ExpectedConditionsPage
import org.testng.annotations.Test

class BlockTimeoutTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"
   ExpectedConditionsPage page

   @Test
   void block_timeout_test() {
      open url
      page = new ExpectedConditionsPage(driver)

      page.timeoutBlockFlow()
   }
}
