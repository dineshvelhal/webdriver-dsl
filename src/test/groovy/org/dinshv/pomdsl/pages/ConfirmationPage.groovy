package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

@Log4j2
class ConfirmationPage extends Page {
  By confirmMessage = byCssSelector('body > div > h1')

   ConfirmationPage(WebDriver driver) {
    super(driver)
  }
}
