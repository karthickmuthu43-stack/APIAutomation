Feature: User API Automation for OMR Branch Hotel Application

  This feature verifies user login, state list, city list, and address 
  management through API automation.

   @Loginv
  Scenario: Get User logtoken from login endpoint
    Given User adds required login headers
    When User adds basic authentication for login
    And User sends "POST" request to the login endpoint
    Then User should verify the status code is 200
    And User should verify the login response body contains firstName "Karthick" and save the logtoken

  @StateListv
  Scenario: Verify User Get StateList through API
    Given User adds headers for StateList
    When User sends "GET" request to StateList endpoint
    Then User should verify the status code is 200
    And User should verify the stateList response message matches "Tamil Nadu" and save the state id

  @CityListv
  Scenario: Verify User Get City list through API
    Given User adds headers for CityList
    When User adds request body with state id for city list
    And User sends "POST" request to CityList endpoint
    Then User should verify the status code is 200
    And User should verify the cityList response message matches "Yercaud" and save the city id

  @AddAddress
  Scenario Outline: Verify add user address through API
    Given User adds headers and bearer authorization for accessing address endpoints
    When User adds request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    Then User should verify the status code is 200
    And User sends "POST" request to addUserAddress endpoint
    And User should verify the addUserAddress response message matches "Address added successfully" and save the address id

    Examples:
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address          | address_type |
      | Karthick   | Muthu         | 9500888546 | Apt-123   | 35    | 4440 | 101     | 600100  | 123, OMR Street  | Home         |

  @UpdateAddress
  Scenario Outline: Verify update user address through API
    Given User adds headers and bearer authorization for accessing address endpoints
    When User adds request body to update address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    And User sends "PUT" request to updateUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the updateUserAddress response message matches "Address updated successfully"

    Examples:
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address             | address_type |
      | Greens     | Techie    | 9944152058 | Apt-456   | 35    | 4440 | 101     | 600101  | 456, OMR Main Road | Office       |

  @GetAddress
  Scenario: Verify Get User Address through API
    Given User adds headers and bearer authorization for accessing get address endpoints
    When User sends "GET" request to GetUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the GetUserAddress response message matches "OK"

  @DeleteAddress
  Scenario: Verify Delete User Address through API
    Given User adds headers and bearer authorization for accessing address endpoints
    When User adds request body with address id
    And User sends "DELETE" request to DeleteAddress endpoint
    Then User should verify the status code is 200
    And User should verify the DeleteAddress response message matches "Address deleted successfully"