Feature: Private Endpoints

  Scenario: status endpoint returns ok
    When "GET" request is made to the "private/status" endpoint
    Then should return a status code of 200
    And should return response body containing "ok"