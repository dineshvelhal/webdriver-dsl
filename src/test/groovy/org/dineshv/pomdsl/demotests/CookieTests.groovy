package org.dineshv.pomdsl.demotests

import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.LoginPage
import org.openqa.selenium.Cookie
import org.testng.annotations.Test

class CookieTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"
   LoginPage lp

   @Test
   void add_cookie_test() {
      open url
      lp = new LoginPage(driver)

      lp.deleteAllCookies()

      lp.addCookie('cookie1', 'value1')
      lp.addCookie('cookie2', 'value2')

      Set<Cookie> cookies = lp.getAllCookies()
      assert cookies.size() == 2

      assert lp.getCookieValue('cookie1') == 'value1'
      assert lp.getCookieValue('cookie2') == 'value2'
   }

   @Test
   void delete_cookie_test() {
      open url
      lp = new LoginPage(driver)

      lp.deleteAllCookies()

      lp.addCookie('cookie1', 'value1')
      lp.addCookie('cookie2', 'value2')

      Set<Cookie> cookies = lp.getAllCookies()
      assert cookies.size() == 2

      lp.deleteCookie('cookie2')
      cookies = lp.getAllCookies()
      assert cookies.size() == 1

      lp.deleteCookie(lp.getCookie('cookie1'))
      cookies = lp.getAllCookies()
      assert cookies.size() == 0
   }
}
