Feature: Application Latency metrics

  Scenario Outline: application latency metrics are created for all endpoints
    Given "<metricsName>" with "<resourceName>" and "<status>" has value of 0.0
    When "GET" request is made to the "<endpoint>" endpoint
    Then should increase "<metricsName>" with "<resourceName>" and "<status>" by 1.0

    Examples:
      | endpoint       | metricsName                       | resourceName        | status |
      | private/status | application_latency_seconds_count | GET-/private/status | 200    |
      | private/info   | application_latency_seconds_count | GET-/private/info   | 200    |

