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
  void combination_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.combinationKeysFlow()
  }

  @Test(enabled = true)
  void special_keys_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.specialKeysFlow()
  }

  @Test(enabled = true)
  void copy_paste_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.copyPasteFlow()
  }

  @Test(enabled = true)
  void clear_and_type_test() {
    open url

    keyboardActionsPage = new KeyboardActionsPage(driver)

    keyboardActionsPage.clearAndTypeFlow()
  }
}
