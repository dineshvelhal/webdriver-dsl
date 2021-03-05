package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

@Log4j2
class AttributesPage extends Page {
   By languageList = byId('select_lang')
   By javaCheckBox = byCssSelector('#check_java')
   By disabledTextBox = byId('salary')
   By readOnlyTextBox = byId('common_sense')
   By experienceTextBox = byId('exp')
   By pythonOption = byXpath('//*[@id="select_lang"]/option[2]')
   By invisibleElement = byId('invisible')

   public AttributesPage(WebDriver driver) {
      super(driver)
   }

   def checkJavaCheckBox() {
      check javaCheckBox
   }
}
