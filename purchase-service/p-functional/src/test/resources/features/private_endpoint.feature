Feature: Private Endpoints

  Scenario: status endpoint returns ok
    When "GET" request is made to the "private/status" endpoint
    Then should return a status code of 200
    And should return response body containing "ok"

  Scenario: metrics endpoint returns metrics
    When "GET" request is made to the "private/metrics" endpoint
    Then should return a status code of 200
    And should return response body containing "jvm_memory_committed_bytes"
    And should return response body containing "jvm_memory_used_bytes"

  Scenario: info endpoint returns app info
    When "GET" request is made to the "private/info" endpoint
    Then should return a status code of 200
    And should return response body containing "Pet Project Purchase Service"
    And should return response body containing "11"
    And should return response body containing "p-service"

  Scenario Outline: healthcheck endpoint returns appropriate response based on downstream status
    Given "<downstream1>" downstream returns a status code of <status1>
    And "<downstream2>" downstream returns a status code of <status2>
    When "GET" request is made to the "private/healthcheck" endpoint
    Then should return a status code of 200
    And should return response body containing "\"status\":\"<response>\""

    Examples:
    | downstream1 | status1 | downstream2 | status2 | response |
    | inventory   | 200     | order       | 200     | UP       |
    | order       | 500     | inventory   | 500     | DOWN     |
    | inventory   | 200     | order       | 500     | DOWN     |
    | order       | 500     | inventory   | 200     | DOWN     |