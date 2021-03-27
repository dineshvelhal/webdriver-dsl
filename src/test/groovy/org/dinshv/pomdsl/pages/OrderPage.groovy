package org.dinshv.pomdsl.pages

import org.dineshv.pomdsl.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class OrderPage extends Page{
   By large_size = byId('rad_large')
   By flavors = byId('select_flavor')
   By barbeque_sauce = byId('rad_barbeque')
   By onion_topping = byId('onions')
   By green_olive_topping = byId('green_olive')
   By tomatoes_topping = byId('tomoto')
   By quantity_field = byId('quantity')
   By add_to_cart_button = byId('submit_button')

   OrderPage(WebDriver driver) {
      super(driver)
   }

   def addToCart() {
      choose large_size
      select value: 'Supreme' from flavors
      choose barbeque_sauce
      choose onion_topping
      choose green_olive_topping
      choose tomatoes_topping
      type "2" into quantity_field

      sleep 5000

      click add_to_cart_button
   }
}
