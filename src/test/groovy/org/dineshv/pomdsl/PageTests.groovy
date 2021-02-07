package org.dineshv.pomdsl

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class PageTests {
    private WebDriver driver
    Page page
    String url = "https://dineshvelhal.github.io/testautomation-playground/"

    @BeforeClass
    void setupClass() {
        println 'Before Class -------------------------------'
        WebDriverManager.chromedriver().setup()

        driver = new ChromeDriver()
        driver.get url

        page = new Page(driver)
    }

    @AfterClass
    void tearDown() {
        println 'After Class -------------------------------'
        if (driver != null) {
            driver.quit()
        }
    }


    @Test
    void page_title_test() {
        def title = page.title()

        println "title:${title}, window_handle:${page.windowHandle()}"

        assert title == 'The Test Automation Playground'
    }

    @Test
    void page_url_test() {
        def url = page.url()

        println "url:${url}"

        assert url == 'https://dineshvelhal.github.io/testautomation-playground/'
    }


}
