package org.dineshv.pomdsl.demotests

import org.dineshv.pomdsl.main.BaseTest
import org.dinshv.pomdsl.pages.ConfirmationPage
import org.dinshv.pomdsl.pages.LoginPage
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class LoginTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"
   LoginPage loginPage

   @Test
   void successful_login_test() {
      open url
      loginPage = new LoginPage(driver)

      ConfirmationPage confirmationPage = loginPage.login('admin', 'admin')

      assert confirmationPage.confirmMessage.displayed == true
      assert confirmationPage.confirmMessage.text == 'Confirmation'
      assert confirmationPage.title == 'Confirmation!'
   }

   @Test(dataProvider = 'incorrect-user-data')
   void failed_login_test(String userName, String password) {
      open url
      loginPage = new LoginPage(driver)

      ConfirmationPage confirmationPage = loginPage.login(userName, password)

      assert confirmationPage.title == 'Login'
   }


   @DataProvider(name = 'incorrect-user-data')
   Object[][] incorrect_login_data() {
      return [
            ['', 'admin'],
            ['admin', ''],
            ['admin', 'incorrect_password'],
            ['incorrect_user', 'admin']]
   }
}
