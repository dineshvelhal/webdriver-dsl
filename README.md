# pom-dsl
# Re-imagining the Page Objects

## Traditional Page Objects
Let's take example of a Login Page. We want to automate the login flow.

![Login Page](/article/Login.JPG)

We want to automate the login flow. How do we construct the Page Class?

Before we begin, we need to look at how the page is composed. The Page has many elements but for our test we need only 3 viz. user name field, password field and the login button. The page and page elements have a One-To-Many relationship. One way of modelling this relationship is

```java
class LoginPage {
   WebElement userNameField = driver.findElement(By.id("user")); // Assuming there is ID attribute to username input field
   WebElement userNameField = driver.findElement(By.id("password")); // Assuming there is ID attribute to password input field
   WebElement loginButton = driver.findElement(By.id("login")); // Assuming there is ID attribute to password input field
}
```
This captures the One-To-Many or Has-A relationship between page and the page elments. This may work in some cases but it has few limitations. For one, the web elements are initialized once at the beginning of creating the page instance. This may not be desirable if page is dynamic and loads/refreshes fully or parts of the page during its lifetime.

Another approach is to use ```By``` locator objects as fields in the page class
```java

```






 