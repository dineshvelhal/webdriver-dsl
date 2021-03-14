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

  @Test(enabled = false)
  void modifier_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.modifierKeysFlow()
  }

  @Test(enabled = false)
  void special_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.specialKeysFlow()
  }

  @Test(enabled = false)
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

  @Test(enabled = false)
  void clear_and_type_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.clearAndTypeFlow()
  }
}
