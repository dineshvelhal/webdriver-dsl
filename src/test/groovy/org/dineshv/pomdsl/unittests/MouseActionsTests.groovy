package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.MouseActionsPage
import org.testng.annotations.Test

@Log4j2
class MouseActionsTests extends BaseTest{
  String url = "https://dineshvelhal.github.io/testautomation-playground/mouse_events.html"
  MouseActionsPage mouseActionsPage

  @Test
  void click_test() {
    open url

    mouseActionsPage = new MouseActionsPage(driver)

    mouseActionsPage.clickFlow()
  }

  @Test
  void double_click_test() {
    open url

    mouseActionsPage = new MouseActionsPage(driver)

    mouseActionsPage.doubleClickFlow()
  }

  @Test
  void right_click_test() {
    open url

    mouseActionsPage = new MouseActionsPage(driver)

    mouseActionsPage.rightClickFlow()
  }

  @Test
  void mouse_hover_test() {
    open url

    mouseActionsPage = new MouseActionsPage(driver)

    mouseActionsPage.hoverFlow()
  }

  @Test(enabled=false)
  void drag_and_drop_test() {
    open url

    mouseActionsPage = new MouseActionsPage(driver)

    mouseActionsPage.dragAndDropFlow()
  }
}
