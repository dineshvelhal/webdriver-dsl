package org.dineshv.pomdsl.unittests

import io.github.bonigarcia.wdm.WebDriverManager
import org.dinshv.pomdsl.pages.ConfirmationPage
import org.dinshv.pomdsl.pages.LoginPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class ClickAndTypeTests {
   private WebDriver driver
   LoginPage loginPage
   String url = "https://dineshvelhal.github.io/testautomation-playground/login.html"

   @BeforeClass
   void setupClass() {
      println 'Before Class -------------------------------'
      WebDriverManager.chromedriver().setup()

      driver = new ChromeDriver()
      driver.get url

      loginPage = new LoginPage(driver)
   }

   @AfterClass
   void tearDown() {
      println 'After Class -------------------------------'
      if (driver != null) {
         sleep 2000
         driver.quit()
      }
   }


   @Test
   void successful_login_test() {
      loginPage.navigate url

      ConfirmationPage confirmationPage = loginPage.login('admin', 'admin')

      assert confirmationPage.confirmMessage.displayed == true
      assert confirmationPage.confirmMessage.text == 'Confirmation'
      assert confirmationPage.title == 'Confirmation!'
   }

   @Test(dataProvider = 'incorrect-user-data')
   void failed_login_test(String userName, String password) {
      loginPage.navigate url

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
