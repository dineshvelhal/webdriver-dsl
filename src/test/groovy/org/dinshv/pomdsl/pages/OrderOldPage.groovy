package org.dinshv.pomdsl.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select

class OrderOldPage {
   WebDriver driver
   By largeSize = By.id('rad_large')
   By veggieDelightFlavor = By.id('select_flavor')
   By barbequeSauce = By.id('rad_barbeque')
   By onionTopping = By.id('onions')
   By greenOliveTopping = By.id('green_olive')
   By tomatoesTopping = By.id('tomoto')
   By quantityField = By.id('quantity')
   By addToCartButton = By.id('submit_button')

   OrderOldPage(WebDriver driver) {
      this.driver = driver
   }

   def addToCart() {
      driver.findElement(largeSize).click()
      Select selectFlavor = new Select(driver.findElement(veggieDelightFlavor))
      selectFlavor.selectByVisibleText('Supreme')
      driver.findElement(barbequeSauce).click()
      driver.findElement(onionTopping).click()
      driver.findElement(greenOliveTopping).click()
      driver.findElement(tomatoesTopping).click()
      driver.findElement(quantityField).sendKeys("2")

      sleep 5000

      driver.findElement(addToCartButton).click()
   }
}
