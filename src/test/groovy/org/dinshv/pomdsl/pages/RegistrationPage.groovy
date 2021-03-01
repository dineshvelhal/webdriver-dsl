package org.dinshv.pomdsl.pages

import groovy.util.logging.Log4j2
import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

@Log4j2
class RegistrationPage extends Page {
  By firstNameField = byName('first_name')
  By lastNameField = byCssSelector('input[name="last_name"]')
  By emailField = byXpath("//input[@name='email']")
  By password1Field = By.id('pwd1')
  By password2Field = By.id('pwd2')
  By termsConditionsCheckBox = byName('terms')
  By registerButton = byId('submit_button')

   RegistrationPage(WebDriver driver) {
    super(driver)
  }

  def register(String firstName, String lastName, String email, String password1, String password2, boolean acceptTerms) {
    type firstName into firstNameField
    type lastName into lastNameField
    type email into emailField
    type password1 into password1Field
    type password2 into password2Field

    if(acceptTerms) {
      check termsConditionsCheckBox
    } else {
      uncheck termsConditionsCheckBox
    }

    click registerButton

    return new ConfirmationPage(driver)
  }
}
