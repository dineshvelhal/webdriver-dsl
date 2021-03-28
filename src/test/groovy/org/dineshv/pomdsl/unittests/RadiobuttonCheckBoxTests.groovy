package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.GenericPage
import org.testng.annotations.Test

@Log4j2
class RadiobuttonCheckBoxTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground"
   GenericPage genericPage

   @Test(enabled = true)
   void checkbox_radiobutton_test() {
      open url
      genericPage = new GenericPage(driver)

      genericPage.checkboxRadioButtonFlow()
   }

   @Test(enabled = true)
   void find_locator_by_unique_name_test() {
      open url
      genericPage = new GenericPage(driver)

      genericPage.findLocatorByUniqueNameFlow()
   }

}
