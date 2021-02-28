package org.dineshv.pomdsl

import groovy.util.logging.Log4j2
import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.ExpectedConditionsPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

@Log4j2
class ExpectedConditionsTests {
   private WebDriver driver
   ExpectedConditionsPage expectedConditionsPage
   String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      driver.manage().window().maximize()

      expectedConditionsPage = new ExpectedConditionsPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         Thread.sleep(2000)
         driver.quit()
      }
   }

   @Test
   void visibility_test() {
      expectedConditionsPage.visibilityFlow()
   }

   @Test
   void invisibility_test() {
      expectedConditionsPage.invisibilityFlow()
   }

   @Test
   void enabled_test() {
      expectedConditionsPage.enabledFlow()
   }
}
