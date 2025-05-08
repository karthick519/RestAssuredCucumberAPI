package stepdefinations;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class ApiSteps {
    private Response response;

    private String getSecretFromKeyVault(String vaultUrl, String secretName) {
        SecretClient secretClient = new SecretClientBuilder()
            .vaultUrl(vaultUrl)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
        return secretClient.getSecret(secretName).getValue();
    }

    @Given("I set the base URI from Azure Key Vault {string} using secret {string}")
    public void setBaseUriFromKeyVault(String keyVaultUrl, String secretName) {
        String baseUri = getSecretFromKeyVault(keyVaultUrl, secretName);
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI from Key Vault: " + baseUri);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured.get(endpoint);
        response.prettyPrint();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the user's details")
    public void verifyUserDetails() {
        response.then().body("data.id", equalTo(2));
        response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
        response.then().body("data.first_name", equalTo("Janet"));
        response.then().body("data.last_name", equalTo("Weaver"));
    }
}
