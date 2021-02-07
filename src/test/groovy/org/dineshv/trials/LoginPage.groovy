package org.dineshv.trials

import org.dineshv.groovypages.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoginPage extends Page {
  By userNameField = By.id('user')
  By passwordField = By.id('password')
  By loginButton = By.id('login')


  public LoginPage(WebDriver driver) {
    super(driver)

    initialize(this)
  }

  public login(userName, password) {
    type userName into userNameField
    type password into passwordField

    println "userName = ${userNameField.value}"
    println "userName placeholder = ${userNameField.placeholder}"
    println "password = ${passwordField.value}"
    println "button = ${loginButton.text}"

    click loginButton
  }

  def dynamicMethod() {
    userNameField.$
  }
}
