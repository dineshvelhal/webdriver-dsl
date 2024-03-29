package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.GenericPage
import org.testng.annotations.Test

@Log4j2
class DropdownListTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground"
   GenericPage genericPage

   @Test(enabled = true)
   void select_test() {
      open url
      genericPage = new GenericPage(driver)

      genericPage.selectFlow()
   }
}
