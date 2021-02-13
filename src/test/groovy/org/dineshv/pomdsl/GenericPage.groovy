package org.dineshv.pomdsl

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class GenericPage extends Page {

   By skillDropdown = By.id('select_tool')
   By languageList = By.id('select_lang')

   By javaCheckBox = By.id("check_java")
   By pythonCheckBox = By.id("check_python")
   By javascriptCheckBox = By.id("check_javascript")

   By seleniumRadioButton = By.id("rad_selenium")
   By protractorRadioButton = By.id("rad_protractor")

   public GenericPage(WebDriver driver) {
      super(driver)

      initialize(this)
   }


   public navigationFlow() {
      navigateTo 'https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html'

      navigate BACK

      Thread.sleep 3000

      navigate FORWARD
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

      backTo PARENT_FRAME

      move into: frame3
      move into: frame4

      click clickMeButton

      backTo MAIN_PAGE

      Thread.sleep 3000
   }

   public expectedConditionsFlow() {
      navigateTo 'https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html'

      By triggerButton = By.id('visibility_trigger')
      By clickMeButton = By.id('visibility_target')
      By spinner = By.id('invisibility_target')


      click triggerButton
      waitFor clickMeButton toBe VISIBLE
      click clickMeButton


      triggerButton = By.id('invisibility_trigger')


      click triggerButton
      waitFor spinner toBe INVISIBLE
   }

   // dropdowns and lists
   public selectFlow() {
      navigateTo 'https://dineshvelhal.github.io/testautomation-playground/forms.html'


      List<WebElement> webElements = get options: ALL from skillDropdown

      webElements.each {print(it.getText() + " | ")}
      println ""

      Thread.sleep 5000

      select index: 2 from skillDropdown
      select value: "sel" from skillDropdown
      select visibleText: "Protractor" from skillDropdown
      select index: [1, 2] from skillDropdown

      println "is skillDropdown multi-select? - ${skillDropdown.multiple}"

      Thread.sleep(5000)
      select value: ['java', 'javascript'] from languageList
      select index: [1, 3] from languageList
      select visibleText: ['Java', 'Python'] from languageList

      Thread.sleep(5000)
      deselect ALL from languageList

      Thread.sleep(5000)
      select value: ['java', 'javascript'] from languageList
      select index: [1, 3] from languageList
      select visibleText: ['Java', 'Python'] from languageList

      Thread.sleep(5000)
      deselect value: 'java' from languageList
      deselect index: 1 from languageList

      webElements = get  options: ALL_SELECTED from languageList

      webElements.each {print(it.getText() + " | ")}
      println ""

      WebElement first = get options: FIRST_SELECTED from languageList
      println first.getText()

      deselect visibleText: 'JavaScript' from languageList
      deselect index: [2, 3] from languageList

      println "is languageList multi-select? - ${languageList.multiple}"
   }

   def checkboxRadioButtonFlow() {
      navigateTo 'https://dineshvelhal.github.io/testautomation-playground/forms.html'

      uncheck javaCheckBox

      check javaCheckBox
      check pythonCheckBox
      check javaCheckBox
      check javascriptCheckBox

      uncheck javaCheckBox
      uncheck pythonCheckBox
      uncheck javascriptCheckBox

      check seleniumRadioButton
      check protractorRadioButton
      check protractorRadioButton

      uncheck protractorRadioButton
   }

}
