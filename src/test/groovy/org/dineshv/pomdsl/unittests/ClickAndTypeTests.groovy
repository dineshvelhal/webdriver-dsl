package org.dineshv.pomdsl.unittests


import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.ConfirmationPage
import org.dinshv.pomdsl.pages.LoginPage
import org.dinshv.pomdsl.pages.OrderPage
import org.testng.annotations.Test

class ClickAndTypeTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"
   LoginPage loginPage

   @Test
   void click_and_type_test() {
      open url
      loginPage = new LoginPage(driver)

      OrderPage orderPage = loginPage.login('admin', 'admin')

      assert orderPage.title == 'Order Pizza'
   }
}
