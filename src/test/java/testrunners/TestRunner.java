package testrunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "json:target/cucumber.json"  // This is required for the HTML report
    },
    features = "src/test/resources/features",
    glue = "stepdefinitions"
)
public class TestRunner {
}

