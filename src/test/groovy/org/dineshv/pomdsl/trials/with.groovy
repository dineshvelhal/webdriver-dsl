package org.dineshv.pomdsl.trials

import io.github.bonigarcia.wdm.WebDriverManager
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

class GenericPage extends Page {
   By javaCheckBox = byId("check_java", 'JAVA_CHECKBOX')
   By pythonCheckBox = byId("check_python", 'PYTHON_CHECKBOX')
   By javascriptCheckBox = byId("check_javascript", 'JAVASCRIPT_CHECKBOX')

   By seleniumRadioButton = byId("rad_selenium", 'SELENIUM_RADIOBUTTON')
   By protractorRadioButton = byId("rad_protractor", 'PROTRACTOR_RADIOBUTTON')

   public GenericPage(WebDriver driver) {
      super(driver)
   }

   def checkboxRadioButtonFlow() {
      navigate 'https://dineshvelhal.github.io/testautomation-playground/forms.html'

      choose 'JAVA_CHECKBOX'
      choose 'PYTHON_CHECKBOX'
      choose 'JAVASCRIPT_CHECKBOX'

      choose 'SELENIUM_RADIOBUTTON'

   }
}

WebDriverManager.chromedriver().setup()

driver = new ChromeDriver()
driver.manage().window().maximize()

try {
   GenericPage genericPage = new GenericPage(driver)

   genericPage.checkboxRadioButtonFlow()
} finally {
   sleep 5000

   driver.quit()
}



