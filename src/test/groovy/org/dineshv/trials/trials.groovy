package org.dineshv.trials

import org.dineshv.pomdsl.FramesPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager


WebDriverManager.chromedriver().setup()

WebDriver driver

try {
  driver = new ChromeDriver()

  driver.get 'https://dineshvelhal.github.io/testautomation-playground/frames.html'

  FramesPage fp = new FramesPage(driver)

  fp.clickButtons()

  Thread.sleep(5000)

} finally {
  driver.quit()
}



