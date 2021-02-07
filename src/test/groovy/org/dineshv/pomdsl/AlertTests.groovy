package org.dineshv.pomdsl

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class AlertTests {
  private WebDriver driver
  ConditionsPage conditionsPage
  String url = "https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html"

  @BeforeClass
  void setupClass() {
    println 'Before Class -------------------------------'
    WebDriverManager.chromedriver().setup()

    driver = new ChromeDriver()
    driver.get url

    conditionsPage = new ConditionsPage(driver)
  }

  @AfterClass
  void tearDown() {
    println 'After Class -------------------------------'
    if (driver != null) {
      Thread.sleep(5000)
      driver.quit()
    }
  }


  @Test
  void alert_test() {
    conditionsPage.acceptAlert()
  }

  @Test
  void accept_prompt_test() {
    conditionsPage.acceptPrompt()
  }

  @Test
  void dismiss_prompt_test() {
    conditionsPage.dismissPrompt()
  }
}
