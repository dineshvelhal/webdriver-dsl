package org.dineshv.pomdsl

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class GenericTests {
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
         Thread.sleep(5000)
         driver.quit()
      }
   }

   @Test(enabled = false)
   void frames_flow_test() {
      genericPage.framesFlow()
   }

   @Test(enabled = false)
   void navigation_flow_test() {
      genericPage.navigationFlow()
   }

   @Test(enabled = false)
   void expected_conditions_test() {
      genericPage.expectedConditionsFlow()
   }

   @Test(enabled = true)
   void select_test() {
      genericPage.selectFlow()
   }

   @Test(enabled = true)
   void checkbox_radiobutton_test() {
      genericPage.checkboxRadioButtonFlow()
   }

}
