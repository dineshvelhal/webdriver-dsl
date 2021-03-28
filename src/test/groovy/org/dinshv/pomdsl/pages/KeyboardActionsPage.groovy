package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import static org.openqa.selenium.Keys.*
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
      type CONTROL into textArea
      assert ctrlKeyIndicator.displayed == true

      // Alt
      type ALT into textArea
      assert altKeyIndicator.displayed == true

      // Shift
      type SHIFT into textArea
      assert shiftKeyIndicator.displayed == true

      // Meta
      type META into textArea
      assert metaKeyIndicator.displayed == true
   }

   def specialKeysFlow() {
      refreshPage()

      // Page Down
      type PAGE_DOWN into textArea
      assert keyIndicator.text == 'PageDown'
      assert keyCodeIndicator.text == 'PageDown'

      // Delete
      type DELETE into textArea
      assert keyIndicator.text == 'Delete'
      assert keyCodeIndicator.text == 'Delete'
   }

//   def copyPasteFlow() {
//      refreshPage()
//
//      def string = 'selenium'
//
//      // type and assert
//      type string into textArea
//      assert textArea.value == string
//
//      // Copy text
//      type keys(CONTROL, 'a') into textArea
//      type keys(CONTROL, 'c') into textArea
//
//      // Delete text
//      type keys(DELETE) into textArea
//      assert textArea.value == ''
//
//      // Paste text
//      type keys(CONTROL, 'v') into textArea
//      assert textArea.value == string
//   }

   def keysAndStringCombinationFlow() {
      refreshPage()

      def string = 'selenium'

      // type and assert
      type string + CONTROL + 'a' into textArea
      assert textArea.value == string

      // Copy text
      type CONTROL + 'c' into textArea

      // Delete text
      type DELETE into textArea
      assert textArea.value == ''

      // Paste text
      type CONTROL + 'v' into textArea
      assert textArea.value == string
   }

   def keysCombinationFlow() {
      refreshPage()

      type CONTROL + ALT + SHIFT + META into textArea
      assert ctrlKeyIndicator.displayed == true
      assert altKeyIndicator.displayed == true
      assert shiftKeyIndicator.displayed == true
      assert metaKeyIndicator.displayed == true

      sleep 3000
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

   def numericDataEntryFlow() {
      refreshPage()

      clearAndType 5 into textArea
      assert textArea.value == '5'

      refreshPage()
      type 10 into textArea
      assert textArea.value == '10'
   }
}
