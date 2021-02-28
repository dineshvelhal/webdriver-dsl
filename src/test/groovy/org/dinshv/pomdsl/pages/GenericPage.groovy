package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

@Log4j2
class GenericPage extends Page {

   public GenericPage(WebDriver driver) {
      super(driver)
   }


   public navigationFlow() {
      navigate'https://dineshvelhal.github.io/testautomation-playground/expected_conditions.html'
      assert title == 'Wait Conditions'

      navigate'https://dineshvelhal.github.io/testautomation-playground/index.html'
      assert title == 'The Test Automation Playground'

      navigate BACK
      assert title == 'Wait Conditions'

      navigate FORWARD
      assert title == 'The Test Automation Playground'
   }





   // dropdowns and lists
   public selectFlow() {
      By skillDropdown = byId('select_tool')
      By languageList = byId('select_lang')

      WebElement el

      navigate 'https://dineshvelhal.github.io/testautomation-playground/forms.html'


      List<WebElement> skills = get options: ALL from skillDropdown
      assert skills.size() == 3

      Thread.sleep 5000

      select index: 2 from skillDropdown
      el = get options: FIRST_SELECTED from skillDropdown
      assert el.getText() == 'Cypress'

      select value: "sel" from skillDropdown
      el = get options: FIRST_SELECTED from skillDropdown
      assert el.getText() == 'Selenium'

      select visibleText: 'Protractor' from skillDropdown
      el = get options: FIRST_SELECTED from skillDropdown
      assert el.getText() == 'Protractor'

      select index: [1, 2] from skillDropdown
      el = get options: FIRST_SELECTED from skillDropdown
      assert el.text == 'Cypress'

      println "is skillDropdown multi-select? - ${skillDropdown.multiple}"

      List<WebElement> languages

      select value: ['java', 'javascript'] from languageList
      languages = get options: ALL_SELECTED from languageList
      assert languages.get(0).text == 'Java'
      assert languages.get(1).text == 'JavaScript'

      select index: [1] from languageList
      select visibleText: ['TypeScript'] from languageList
      languages = get options: ALL_SELECTED from languageList
      assert languages.get(0).text == 'Java'
      assert languages.get(1).text == 'Python'
      assert languages.get(2).text == 'JavaScript'
      assert languages.get(3).text == 'TypeScript'

      deselect ALL from languageList
      languages = get options: ALL_SELECTED from languageList
      assert languages.size() == 0

      select value: ['java', 'javascript', 'python', 'typescript'] from languageList

      deselect value: 'java' from languageList
      deselect index: 1 from languageList
      deselect visibleText: ['JavaScript'] from languageList

      languages = get options: ALL_SELECTED from languageList
      assert languages.get(0).text == 'TypeScript'
      assert languages.size() == 1


      println "is languageList multi-select? - ${languageList.multiple}"
   }

   def checkboxRadioButtonFlow() {
      By javaCheckBox = byId("check_java")
      By pythonCheckBox = byId("check_python")
      By javascriptCheckBox = byId("check_javascript")

      By seleniumRadioButton = byId("rad_selenium")
      By protractorRadioButton = byId("rad_protractor")

      navigate 'https://dineshvelhal.github.io/testautomation-playground/forms.html'

      uncheck javaCheckBox
      assert javaCheckBox.selected == false

      check javaCheckBox
      assert javaCheckBox.selected == true

      check pythonCheckBox
      assert pythonCheckBox.selected == true

      check javaCheckBox
      assert javaCheckBox.selected == true

      check javascriptCheckBox
      assert javascriptCheckBox.selected == true

      uncheck javaCheckBox
      assert javaCheckBox.selected == false

      uncheck pythonCheckBox
      assert pythonCheckBox.selected == false

      uncheck javascriptCheckBox
      assert javascriptCheckBox.selected == false

      check seleniumRadioButton
      assert seleniumRadioButton.selected == true

      check protractorRadioButton
      assert protractorRadioButton.selected == true

      check protractorRadioButton
      assert protractorRadioButton.selected == true

      uncheck protractorRadioButton
      assert protractorRadioButton.selected == true
   }

}
