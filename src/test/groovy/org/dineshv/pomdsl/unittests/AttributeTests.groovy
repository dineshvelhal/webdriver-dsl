package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.AttributesPage
import org.testng.annotations.Test

@Log4j2
class AttributeTests extends BaseTest{
  String url = "https://dineshvelhal.github.io/testautomation-playground/forms.html"
  AttributesPage ap

  @Test
  void attribute_test() {
    open url
    ap = new AttributesPage(driver)

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
