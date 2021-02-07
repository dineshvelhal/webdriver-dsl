package org.dineshv.pomdsl

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class FramesPage extends Page {
   By frame1 = By.id("frame1")
   By frame2 = By.id("frame2")
   By frame3 = By.id("frame3")
   By frame4 = By.id("frame4")

   By clickMeButton1 = By.id("button")
   By clickMeButton2 = By.id("button")
   By clickMeButton3 = By.id("button")

   FramesPage(WebDriver driver) {
      super(driver)

      initialize(this)
   }

   public void clickButtons() {
      frame(frame1) {

         click clickMeButton1;

         frame(frame2) {

            click clickMeButton2;
         }

         frame(frame3) {

            frame(frame4) {

               click clickMeButton3;

            }
         }
      }
   }
}
