package org.dineshv.pomdsl.trials

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.dinshv.pomdsl.pages.ConfirmationPage
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

@Log4j2
class LoginPage extends Page {
   By userNameField = byId('user')
   By passwordField = byId('password')
   By loginButton = byId('login')

   By languageList = byId('abcd')


   LoginPage(WebDriver driver) {
      super(driver)
   }

   def login(userName, password) {
      WebElement uname = driver.findElement(userNameField);
      WebElement pwd = driver.findElement(passwordField);
      WebElement login = driver.findElement(loginButton)
      uname.sendKeys(userName)
      pwd.sendKeys(password)
      login.click()
   }

//   public void selectLanguage() {
//      // Create object of the Select class
//      Select se = new Select(driver.findElement(By.xpath(languageList)));
//
//      // Select any language from the list
//      se.selectByValue("Python");
//      se.selectByIndex(0); // will select first item in the list (Java in this case)
//   }

   public void selectLanguage() {
      select value: 'Python' from languageList
      select index: 0 from languageList
   }
}