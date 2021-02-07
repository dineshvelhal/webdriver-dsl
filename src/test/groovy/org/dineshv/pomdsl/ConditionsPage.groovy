package org.dineshv.pomdsl

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class ConditionsPage extends Page{

  By triggerButton = By.id('alert_trigger')
  By promptButton = By.id('prompt_trigger')

  def ConditionsPage(WebDriver driver) {
    super(driver)
  }

  public acceptAlert() {
    click triggerButton

    accept alert
  }

  public acceptPrompt() {
    click promptButton

    accept alert
  }

  public dismissPrompt() {
    click promptButton

    dismiss alert
  }
}
