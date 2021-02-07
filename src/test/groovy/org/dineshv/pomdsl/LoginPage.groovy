package org.dineshv.pomdsl

import groovy.util.logging.Log4j2
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

@Log4j2
class LoginPage extends Page {
  By userNameField = By.id('user')
  By passwordField = By.id('password')
  By loginButton = By.id('login')


  public LoginPage(WebDriver driver) {
    super(driver)

    initialize(this)
  }

  public login(userName, password) {
    // Missing Property assertions


    type userName into userNameField
    type password into passwordField

    log.info "userNameField.value = ${userNameField.value}"
    log.info "userNameField.text = ${userNameField.text}"
    log.info "userNameField.\$ = ${userNameField.$}"
    log.info "userNameField.tag = ${userNameField.tag}"
    log.info "userNameField.location = ${userNameField.location}"
    log.info "userNameField.size = ${userNameField.size}"
    log.info "userNameField.rectangle = ${userNameField.rectangle}"

    click loginButton
  }
}
