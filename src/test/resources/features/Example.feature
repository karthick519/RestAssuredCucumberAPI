Feature: API User Validation

  Scenario: Validate user details using API and Key Vault secrets
    Given I fetch the base URI and user details from Key Vault
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain the user's details from Key Vault
