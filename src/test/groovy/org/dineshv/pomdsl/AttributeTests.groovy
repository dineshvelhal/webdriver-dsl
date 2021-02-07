package org.dineshv.pomdsl

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class AttributeTests {
  private WebDriver driver
  String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"

  LoginPage lp

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

    Thread.sleep(5000)

    if (driver != null) {
      driver.quit()
    }
  }


  @Test
  void attribute_test() {
    lp.login("admin", "admin")
  }

}
