package org.dineshv.pomdsl.demotests

import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.OrderOldPage
import org.dinshv.pomdsl.pages.OrderPage
import org.testng.annotations.Test

class OrderTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/order_submit.html"
   OrderPage orderPage

   @Test
   void successful_login_test() {
      driver.get(url)
      orderPage = new OrderPage(driver)

      orderPage.addToCart()
   }
}
