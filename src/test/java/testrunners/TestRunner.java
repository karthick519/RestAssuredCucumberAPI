import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",                 // Correct way to refer to feature files
    glue = {"stepdefinations"},                      // Your package is named stepdefinations
    plugin = {
        "pretty",
        "json:target/cucumber.json"
    },
    monochrome = true
)

public class TestRunner {
}
