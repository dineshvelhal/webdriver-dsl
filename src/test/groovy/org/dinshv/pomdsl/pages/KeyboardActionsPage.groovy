package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver

@Log4j2
class KeyboardActionsPage extends Page {
   By ctrlKeyIndicator = byId('ctrl')
   By altKeyIndicator = byId('alt')
   By shiftKeyIndicator = byId('shift')
   By metaKeyIndicator = byId('meta')
   By keyIndicator = byId('key')
   By keyCodeIndicator = byId('code')
   By textArea = byId('area')

   public KeyboardActionsPage(WebDriver driver) {
      super(driver)
   }

   def modifierKeysFlow() {
      refreshPage()

      // Ctrl
      type keys(Keys.CONTROL) into textArea
      assert ctrlKeyIndicator.displayed == true

      // Alt
      type keys(Keys.ALT) into textArea
      assert altKeyIndicator.displayed == true

      // Shift
      type keys(Keys.SHIFT) into textArea
      assert shiftKeyIndicator.displayed == true

      // Meta
      type keys(Keys.META) into textArea
      assert metaKeyIndicator.displayed == true
   }

   def combinationKeysFlow() {
      refreshPage()

      // Ctrl+A
      type keys(Keys.CONTROL, 'A') into textArea
      assert ctrlKeyIndicator.displayed == true
      assert keyIndicator.text == 'A'
   }

   def specialKeysFlow() {
      refreshPage()

      // Page Down
      type keys(Keys.PAGE_DOWN) into textArea
      assert keyIndicator.text == 'PageDown'
      assert keyCodeIndicator.text == 'PageDown'

      // Delete
      type keys(Keys.DELETE) into textArea
      assert keyIndicator.text == 'Delete'
      assert keyCodeIndicator.text == 'Delete'
   }

   def copyPasteFlow() {
      refreshPage()

      def string = 'selenium'

      // type and assert
      type string into textArea
      assert textArea.value == string

      // Copy text
      type keys(Keys.CONTROL, 'a') into textArea
      type keys(Keys.CONTROL, 'c') into textArea

      // Delete text
      type keys(Keys.DELETE) into textArea
      assert textArea.value == ''

      // Paste text
      type keys(Keys.CONTROL, 'v') into textArea
      assert textArea.value == string
   }

   def clearAndTypeFlow() {
      refreshPage()

      def firstName = 'Simon'
      def lastName = 'Stewart'

      type firstName into textArea
      assert textArea.value == firstName

      type lastName into textArea
      assert textArea.value == firstName + lastName

      clearAndType firstName into textArea
      assert textArea.value == firstName

      clearAndType lastName into textArea
      assert textArea.value == lastName
   }
}
