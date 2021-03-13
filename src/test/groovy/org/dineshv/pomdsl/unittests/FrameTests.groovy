package org.dineshv.pomdsl.unittests

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.FramesPage
import org.testng.annotations.Test

@Log4j2
class FrameTests extends BaseTest{
   String url = 'https://dineshvelhal.github.io/testautomation-playground/frames.html'
   FramesPage framesPage

   @Test(enabled = true)
   void frames_flow_by_locator_test() {
      open url
      framesPage = new FramesPage(driver)

      framesPage.framesFlowByLocator()
   }

   @Test
   void frames_flow_by_index_test() {
      open url
      framesPage = new FramesPage(driver)

      framesPage.framesFlowByIndex()
   }
}
