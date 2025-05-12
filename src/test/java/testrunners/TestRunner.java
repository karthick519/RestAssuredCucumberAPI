import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinations"},
    plugin = {
        "pretty",
        "json:target/cucumber.json"
    },
    monochrome = true
)
public class TestRunner {
}
