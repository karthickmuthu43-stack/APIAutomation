Feature: User Login and Token Retrieval
  This feature verifies user login via basic authentication and retrieves the logtoken
  required for all authorized API operations.

  @Login
  Scenario: Get User logtoken from login endpoint
    Given User adds required login headers
    When User adds basic authentication for login
    And User sends "POST" request to the login endpoint
    Then User should verify the status code is 200
    And User should verify the login response body contains firstName "Karthick" and save the logtoken
