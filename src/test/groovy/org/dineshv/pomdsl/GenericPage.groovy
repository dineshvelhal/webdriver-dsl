package org.dineshv.pomdsl

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class GenericPage extends Page {

  public GenericPage(WebDriver driver) {
    super(driver)
  }


  public navigationFlow() {
    navigateTo 'https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html'

    navigate back

    Thread.sleep 3000

    navigate forward
  }

  public framesFlow() {
    By framesButton = By.cssSelector("body > div.container.shadow-lg > div:nth-child(8) > div:nth-child(2) > div > div.card-body.shadow > a")
    By frame1 = By.id('frame1')
    By frame2 = By.id('frame2')
    By frame3 = By.id('frame3')
    By frame4 = By.id('frame4')
    By clickMeButton = By.id('button')

    navigateTo 'https://dineshvelhal.github.io/testautomation-playground'

    click framesButton

    move into: frame1
    click clickMeButton

    move into: frame2
    click clickMeButton

    backTo parentFrame

    move into: frame3
    move into: frame4

    click clickMeButton

    backTo mainPage

    Thread.sleep 3000
  }

  public expectedConditionsFlow() {
    navigateTo 'https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html'

    By triggerButton = By.id('visibility_trigger')
    By clickMeButton = By.id('visibility_target')
    By spinner = By.id('invisibility_target')


    click triggerButton
    waitFor clickMeButton toBe visible
    click clickMeButton


    triggerButton = By.id('invisibility_trigger')


    click triggerButton
    waitFor spinner toBe invisible



  }

}
