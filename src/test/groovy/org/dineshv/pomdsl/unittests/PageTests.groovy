package org.dineshv.pomdsl.unittests

import org.dineshv.pomdsl.Page
import org.dineshv.pomdsl.main.BaseTest
import org.testng.annotations.Test

class PageTests extends BaseTest{
   String url = "https://dineshvelhal.github.io/testautomation-playground/"
   Page page

   @Test
   void page_title_test() {
      open url
      page = new Page(driver)

      def title = page.title

      println "title:${title}"

      assert title == 'The Test Automation Playground'
   }

   @Test
   void page_url_test() {
      open url
      page = new Page(driver)

      def url = page.url

      println "url:${url}"

      assert url == 'https://dineshvelhal.github.io/testautomation-playground/'
   }

   @Test
   void page_source_test() {
      open url
      page = new Page(driver)

      def source = page.source

      println "source:${source}"

      assert source != ''
   }

   @Test
   void window_handle_test() {
      open url
      page = new Page(driver)

      def handle = page.windowHandle

      println "window handle:[${handle}]"

      assert handle != ''
   }
}
