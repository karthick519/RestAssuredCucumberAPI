Feature: API Testing with Rest-Assured and Azure Key Vault

  Scenario: Get user details securely
    Given I retrieve "baseUri" from Azure Key Vault
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should match the details stored in Azure Key Vault
