package org.dineshv.pomdsl

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoginPage extends Page {
  By userNameField = By.id('user')
  By passwordField = By.id('password')
  By loginButton = By.id('login')


  public LoginPage(WebDriver driver) {
    super(driver)
  }

  public login(userName, password) {
    type userName into userNameField
    type password into passwordField
    click loginButton
  }
}
