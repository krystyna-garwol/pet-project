Feature: Application Latency metrics

  Scenario Outline: application latency metrics are created for all private endpoints
    When "GET" request is made to the "<endpoints>" endpoint
    Then should return a status code of 200
    When "GET" request is made to the "private/metrics" endpoint
    Then should return response body containing "application_latency_seconds_count{resourceName=\"GET-/<endpoints>\",status=\"200\",} 1.0"

    Examples:
    | endpoints      |
    | private/status |
    | private/info   |

