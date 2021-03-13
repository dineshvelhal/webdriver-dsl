package org.dineshv.pomdsl.demotests

import io.github.bonigarcia.wdm.WebDriverManager
import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.ConfirmationPage
import org.dinshv.pomdsl.pages.RegistrationPage
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class RegistrationTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/register.html"
   RegistrationPage registrationPage
   ConfirmationPage confirmationPage

   @Test
   void successful_registration_test() {
      open url
      registrationPage = new RegistrationPage(driver)

      confirmationPage = registrationPage.register(
            'dinesh',
            'velhal',
            'dinesh@velhal.com',
            'abc',
            'abc',
            true)

      assert confirmationPage.confirmMessage.displayed == true
      assert confirmationPage.confirmMessage.text == 'Confirmation'
      assert confirmationPage.title == 'Confirmation!'
   }

   @Test(dataProvider = 'incorrect-registration-data')
   void incorrect_registration_test(String firstName, String lastName, String email, String password1, String password2, boolean acceptTerms) {
      open url
      registrationPage = new RegistrationPage(driver)

      confirmationPage = registrationPage.register(firstName, lastName, email, password1, password2, acceptTerms)

      assert confirmationPage.title == 'Register!'
   }


   @DataProvider(name = 'incorrect-registration-data')
   Object[][] incorrect_registration_data() {
      return [
            ['', 'velhal', 'dinesh@velhal.com', 'abc', 'abc', true],
            ['dinesh', '', 'dinesh@velhal.com', 'abc', 'abc', true],
            ['dinesh', 'velhal', '', 'abc', 'abc', true],
            ['dinesh', 'velhal', 'dinesh@velhal.com', '', 'abc', true],
            ['dinesh', 'velhal', 'dinesh@velhal.com', 'abc', '', true],
            ['dinesh', 'velhal', 'dinesh@velhal.com', 'abc', 'abc', false]]
   }
}
