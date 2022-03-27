Feature: Application Latency metrics

  Scenario Outline: application latency metrics are created for all private endpoints
    When "GET" request is made to the "<endpoints>" endpoint

    Examples:
    | endpoints      |
    | private/status |
    | private/info   |

