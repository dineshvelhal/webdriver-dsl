package org.dineshv.pomdsl.unittests

import io.github.bonigarcia.wdm.WebDriverManager
import org.dineshv.pomdsl.Page
import org.dinshv.pomdsl.pages.ExpectedConditionsPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class BlockTimeoutTests {
   private WebDriver driver
   ExpectedConditionsPage page
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      page = new ExpectedConditionsPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         driver.quit()
      }
   }


   @Test
   void block_timeout_test() {
      page.timeoutBlockFlow()
   }
}
