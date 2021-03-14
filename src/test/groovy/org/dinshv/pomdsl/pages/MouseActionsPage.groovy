package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

@Log4j2
class MouseActionsPage extends Page {
   By area = byId('click_area')
   By clickType = byId('click_type')
   By languageDropDown = byCssSelector('body > div > div:nth-child(4) > div.card-body > div > div:nth-child(1) > div > button')
   By pythonOption = byId('dd_python')
   By validationText = byId('hover_validate')
   By source = byId('drag_source')
   By target = byId('drop_target')

   public MouseActionsPage(WebDriver driver) {
      super(driver)
   }

   def clickFlow() {
      click area

      assert clickType.text == 'Click'
   }

   def doubleClickFlow() {
      doubleClick area

      assert clickType.text == 'Double-Click'
   }

   def rightClickFlow() {
      rightClick area

      assert clickType.text == 'Right-Click'
   }

   def hoverFlow() {
      hover MOUSE over languageDropDown
      click pythonOption

      assert validationText.text == 'Python'
   }

   def dragAndDropFlow() {
      drag source over target
   }
}
