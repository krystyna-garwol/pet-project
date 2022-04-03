Feature: Product Endpoints

  Scenario: check stock endpoint returns relevant response
    Given "inventory" downstream returns 200 for product id 3
    When "POST" request is made to the "products/stock" endpoint with body "{\"productId\": \"3\",\"basket\": 5}"
    Then should return a status code of 200
    And should return response body containing "{\"currentStock\":35}"