package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.GenericPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

@Log4j2
class DropdownListTests {
   private WebDriver driver
   GenericPage genericPage
   String url = "https://dineshvelhal.github.io/testautomation-playground"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      driver.manage().window().maximize()

      genericPage = new GenericPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         // Thread.sleep(5000)
         driver.quit()
      }
   }


   @Test(enabled = true)
   void select_test() {
      genericPage.selectFlow()
   }
}
