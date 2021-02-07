package org.dineshv.trials

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager


WebDriverManager.chromedriver().setup()

WebDriver driver

try {
  driver = new ChromeDriver()

  driver.get 'https://dineshvelhal.github.io/testautomation-playground/login.html'

  LoginPage lp = new LoginPage(driver)

  lp.login'admin', 'admin'

} finally {
  driver.quit()
}



