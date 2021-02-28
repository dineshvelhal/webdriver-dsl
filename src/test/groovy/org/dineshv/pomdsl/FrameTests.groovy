package org.dineshv.pomdsl

import groovy.util.logging.Log4j2
import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.FramesPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

@Log4j2
class FrameTests {
   private WebDriver driver
   FramesPage framesPage
   String url = 'https://dineshvelhal.github.io/testautomation-playground/frames.html'

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      driver.manage().window().maximize()

      framesPage = new FramesPage(driver)
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
   void frames_flow_by_locator_test() {
      framesPage.framesFlowByLocator()
   }

   @Test
   void frames_flow_by_index_test() {
      framesPage.framesFlowByIndex()
   }
}
