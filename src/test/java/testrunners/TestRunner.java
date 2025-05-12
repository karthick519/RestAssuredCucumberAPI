@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinations"},
    plugin = {"pretty", "json:target/cucumber.json"},  // Needed for HTML report
    monochrome = true
)
