Feature: Ozon testing


  Scenario: Ozon find item, add to cart,remove from cart
    Given open browser on ozon.ru
    When Find item by name
    When Add first item to cart
    Then Added item in cart exists
    When Remove Item from cart
    Then Cart is empty