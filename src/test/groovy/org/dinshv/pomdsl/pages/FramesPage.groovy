package org.dinshv.pomdsl.pages

import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class FramesPage extends Page {
   By framesButton = byCssSelector("body > div.container.shadow-lg > div:nth-child(8) > div:nth-child(2) > div > div.card-body.shadow > a")
   By frame1 = byId('frame1')
   By frame2 = byId('frame2')
   By frame3 = byId('frame3')
   By frame4 = byId('frame4')
   By clickMeButton = byId('button')

   FramesPage(WebDriver driver) {
      super(driver)
   }

   def framesFlowByLocator() {
      refreshPage()
      assert title == 'Frame Interactions'

      frame(frame1) {
         // Now all interactions will occur inside frame1
         click clickMeButton
         assert clickMeButton.text == 'CLICKED'

         frame(frame3) {
            // Now all interactions will occur inside frame3
            frame(frame4) {
               // Now all interactions will occur inside frame4
               click clickMeButton
               assert clickMeButton.text == 'CLICKED'
            }
         }

         frame(frame2) {
            // Now all interactions will occur inside frame2
            click clickMeButton
            assert clickMeButton.text == 'CLICKED'
         }
      }
   }

   def framesFlowByIndex() {
      refreshPage()
      assert title == 'Frame Interactions'

      frame(0) {
         // Now all interactions will occur inside frame1
         click clickMeButton
         assert clickMeButton.text == 'CLICKED'

         frame(1) {
            // Now all interactions will occur inside frame3
            frame(0) {
               // Now all interactions will occur inside frame4
               click clickMeButton
               assert clickMeButton.text == 'CLICKED'
            }
         }

         frame(0) {
            // Now all interactions will occur inside frame2
            click clickMeButton
            assert clickMeButton.text == 'CLICKED'
         }
      }
   }
}
