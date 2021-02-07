package org.dineshv.pomdsl

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class ClickTypeTests {
  private WebDriver driver
  LoginPage loginPage
  String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"

  @BeforeClass
  void setupClass() {
    println 'Before Class -------------------------------'
    WebDriverManager.chromedriver().setup()

    driver = new ChromeDriver()
    driver.get url

    loginPage = new LoginPage(driver)
  }

  @AfterClass
  void tearDown() {
    println 'After Class -------------------------------'
    if (driver != null) {
      Thread.sleep(3000)
      driver.quit()
    }
  }


  @Test
  void click_test() {
    loginPage.login('admin', 'admin')
  }
}
