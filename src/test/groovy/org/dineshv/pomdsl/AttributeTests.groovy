package org.dineshv.pomdsl

import groovy.util.logging.Log4j2
import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.LoginPage
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

@Log4j2
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
    By userNameField = lp.userNameField

    log.info "userNameField.value = ${userNameField.value}"
    log.info "userNameField.text = ${userNameField.text}"
    log.info "userNameField.\$ = ${userNameField.$}"
    log.info "userNameField.tag = ${userNameField.tag}"
    log.info "userNameField.location = ${userNameField.location}"
    log.info "userNameField.size = ${userNameField.size}"
    log.info "userNameField.rectangle = ${userNameField.rectangle}"
  }

}
