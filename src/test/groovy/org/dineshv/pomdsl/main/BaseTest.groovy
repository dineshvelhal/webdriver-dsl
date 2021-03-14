package org.dineshv.pomdsl.main

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class BaseTest {
   protected WebDriver driver

   protected void open(String url) {
      driver.get url
   }

   @BeforeClass
   void setupClass() {
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.manage().window().maximize()
   }

   @AfterClass
   void tearDown() {
      Thread.sleep(1000)

      if (driver != null) {
         driver.quit()
      }
   }
}
