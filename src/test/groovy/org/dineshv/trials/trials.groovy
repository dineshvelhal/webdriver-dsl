package org.dineshv.trials

import org.dineshv.pomdsl.FramesPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager


WebDriverManager.chromedriver().setup()

WebDriver driver

try {

  def a = [1, 2, 3]

  println "a is list is ${a instanceof  List}"



} finally {
  driver.quit()
}



