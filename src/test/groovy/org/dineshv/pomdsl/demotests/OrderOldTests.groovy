package org.dineshv.pomdsl.demotests

import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.OrderOldPage
import org.testng.annotations.Test

class OrderOldTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/order_submit.html"
   OrderOldPage orderOldPage

   @Test
   void successful_login_test() {
      driver.get(url)
      orderOldPage = new OrderOldPage(driver)

      orderOldPage.addToCart()
   }
}
