package org.dineshv.pomdsl.unittests

import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.LoginPage
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class CookieTests {
   private WebDriver driver
   LoginPage lp
   String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      lp = new LoginPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         sleep 2000
         driver.quit()
      }
   }


   @Test
   void add_cookie_test() {
      lp.navigate url

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
      lp.navigate url

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
