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
    private String expectedEmail;
    private String expectedFirstName;
    private String expectedLastName;

    private final SecretClient secretClient = new SecretClientBuilder()
            .vaultUrl("https://<your-keyvault-name>.vault.azure.net/")
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();

    @Given("I retrieve {string} from Azure Key Vault")
    public void getSecretFromKeyVault(String secretName) {
        String baseUri = secretClient.getSecret(secretName).getValue();
        RestAssured.baseURI = baseUri;

        // Load expected values too (optional: cache to avoid repeat calls)
        expectedEmail = secretClient.getSecret("expectedEmail").getValue();
        expectedFirstName = secretClient.getSecret("expectedFirstName").getValue();
        expectedLastName = secretClient.getSecret("expectedLastName").getValue();
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

    @Then("the response should match the details stored in Azure Key Vault")
    public void verifyUserDetailsFromKeyVault() {
        response.then().body("data.email", equalTo(expectedEmail));
        response.then().body("data.first_name", equalTo(expectedFirstName));
        response.then().body("data.last_name", equalTo(expectedLastName));
    }
}
