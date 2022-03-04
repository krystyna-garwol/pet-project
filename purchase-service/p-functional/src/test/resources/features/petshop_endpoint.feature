Feature: Pet Shop Endpoints

  Scenario: check Stock endpoint returns relevant response
    When "POST" request is made to the "pet/shop/basket/stock" endpoint with body "[{\"name\":\"Purina\",\"animal\":\"cat\"}]"
    Then should return a status code of 200
    And should return response body containing "Item in stock"