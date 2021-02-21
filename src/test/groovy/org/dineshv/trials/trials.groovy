package org.dineshv.trials

import org.dineshv.pomdsl.FramesPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager


WebDriverManager.chromedriver().setup()

WebDriver driver

try {

  driver.get ''


} finally {
  driver.quit()
}



