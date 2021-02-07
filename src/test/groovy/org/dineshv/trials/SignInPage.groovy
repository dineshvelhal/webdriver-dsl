package org.dineshv.trials

import org.dineshv.pomdsl.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class SignInPage extends Page {
   By userNameField = By.id("user");
   By passwordField = By.id("password");
   By loginButton = By.id("login");


   public void login(String user, String password) {
      type user into userNameField

      // read web element's HTML attribute directly from By object
      println "Placeholder - ${userNameField.placeholder}"

      // read element properties directly from By object
      println "Location - ${userNameField.location}"

      type password into passwordField

      click loginButton


   }

   public SignInPage(WebDriver driver) {
      super(driver);
      initialize(this);
   }
}
