package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.AttributesPage
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
  String url = "https://dineshvelhal.github.io/testautomation-playground/forms.html"

  AttributesPage ap

  @BeforeClass
  void setupClass() {
    println 'Before Class -------------------------------'
    WebDriverManager.chromedriver().setup()

    driver = new ChromeDriver()
    driver.manage().window().maximize()
    driver.get url

    ap = new AttributesPage(driver)
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
    ap.type '5' into ap.experienceTextBox
    assert ap.experienceTextBox.value == '5'

    assert ap.javaCheckBox.value == 'JAVA'
    ap.checkJavaCheckBox()
    assert ap.javaCheckBox.selected

    assert ap.pythonOption.text == 'Python'

    assert ap.languageList.tag == 'select'
    assert ap.languageList.location != null
    println "location = ${ap.languageList.location}"

    assert ap.languageList.size != null
    println "size = ${ap.languageList.size}"

    assert ap.languageList.rectangle != null
    println "rect = ${ap.languageList.rectangle}"

    assert ap.disabledTextBox.enabled == false

    assert ap.readOnlyTextBox.readonly == 'true'
    assert ap.readOnlyTextBox.enabled == true
    assert ap.readOnlyTextBox.displayed == true

    assert ap.languageList.multiple == 'true'

    assert ap.invisibleElement.displayed == false

    assert ap.invisibleElement.abcdef == null

    assert ap.languageList.$.class.name == 'org.openqa.selenium.remote.RemoteWebElement'

    println("Language List display = ${ap.languageList.css('display')}")

  }

}
