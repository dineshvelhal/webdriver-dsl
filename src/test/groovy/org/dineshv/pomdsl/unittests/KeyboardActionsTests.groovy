package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.KeyboardActionsPage
import org.dinshv.pomdsl.pages.MouseActionsPage
import org.testng.annotations.Test

@Log4j2
class KeyboardActionsTests extends BaseTest{
  String url = "https://dineshvelhal.github.io/testautomation-playground/keyboard_events.html"
  KeyboardActionsPage keyboardActionsPage

  @Test(enabled = true)
  void modifier_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.modifierKeysFlow()
  }

  @Test(enabled = true)
  void special_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.specialKeysFlow()
  }

  @Test(enabled = true)
  void keys_and_strings_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.keysAndStringCombinationFlow()
  }

  @Test(enabled = true)
  void keys_combination_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.keysCombinationFlow()
  }

  @Test(enabled = true)
  void clear_and_type_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.clearAndTypeFlow()
  }

  @Test(enabled = true)
  void numeric_data_entry_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.numericDataEntryFlow()
  }
}
