package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

@Log4j2
class LoginPage extends Page {
  By userNameField = byId('user')
  By passwordField = byId('password')
  By loginButton = byId('login')


  LoginPage(WebDriver driver) {
    super(driver)

    // initialize(this)
  }



  def login(userName, password) {

    type userName into userNameField
    type password into passwordField

    click loginButton
  }
}
